package com.example.bnilist.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import com.example.bnilist.R;
import com.example.bnilist.adapter.RegionAdapter;
import com.example.bnilist.model.RegionModel;
import com.example.bnilist.model.RegionResponseModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import static com.example.bnilist.helper.ConfigHelper.BASEURL_REGION;

public class WilayahActivity extends AppCompatActivity {
    @BindView(R.id.toolBar)
    Toolbar toolBar;
    @BindView(R.id.rcWilayah)
    RecyclerView rcWilayah;
    private RegionResponseModel regionResponseModel;
    private ArrayList<RegionModel> data = new ArrayList<>();
    private RegionAdapter regionAdapter;

    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    OkHttpClient client = new OkHttpClient();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wilayah);
        ButterKnife.bind(this);
        initComponent();
        String phonenumber = getIntent().getStringExtra("phonenumber");
        getRegionList(BASEURL_REGION, phonenumber);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        rcWilayah.setLayoutManager(layoutManager);
        rcWilayah.setHasFixedSize(true);
        regionAdapter = new RegionAdapter(this,data);
        rcWilayah.setAdapter(regionAdapter);
    }

    protected void initComponent () {
        setSupportActionBar(toolBar);
        getSupportActionBar().setTitle("Wilayah");
        toolBar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp);
        toolBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // back button pressed
                finish();
            }
        });
    }

    private void getRegionList(String postUrl, String phonenumber){

        JSONObject jsonReq = new JSONObject();
        try {
            jsonReq.put("phonenumber",phonenumber);
        } catch (JSONException je) {
            je.printStackTrace();
        }
        RequestBody body = RequestBody.create(JSON, jsonReq.toString());
        Request request = new Request.Builder().url(postUrl).post(body).build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                call.cancel();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    final String strJson = response.body().string();
                    WilayahActivity.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                JSONObject jsonObject = new JSONObject(strJson);
                                JSONArray jsonArray = jsonObject.getJSONArray("data");
                                for(int i = 0; i < jsonArray.length(); i++) {
                                    RegionModel rg = new RegionModel();
                                    JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                                    String id = jsonObject1.getString("id");
                                    String code = jsonObject1.getString("code");
                                    String name = jsonObject1.getString("name");

                                    rg.setId(id);
                                    rg.setName(name);
                                    rg.setCode(code);
                                    regionAdapter.notifyDataSetChanged();
                                    data.add(rg);
                                }

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    });
                }

            }
        });
    }
}
