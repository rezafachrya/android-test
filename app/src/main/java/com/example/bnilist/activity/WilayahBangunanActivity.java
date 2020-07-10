package com.example.bnilist.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.bnilist.R;
import com.example.bnilist.adapter.BangunanRegionAdapter;
import com.example.bnilist.adapter.KantorRegionAdapter;
import com.example.bnilist.model.RegionModel;
import com.example.bnilist.model.RegionResponseModel;
import com.google.android.material.button.MaterialButton;

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

public class WilayahBangunanActivity extends AppCompatActivity {
    @BindView(R.id.toolBar)
    Toolbar toolBar;
    @BindView(R.id.rcWilayah)
    RecyclerView rcWilayah;
    @BindView(R.id.relayWilayahProgressBar)
    RelativeLayout relayWilayahProgressBar;
    private RegionResponseModel regionResponseModel;
    private ArrayList<RegionModel> data = new ArrayList<>();
    private BangunanRegionAdapter bangunanRegionAdapter;

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
        bangunanRegionAdapter = new BangunanRegionAdapter(this,data);
        rcWilayah.setAdapter(bangunanRegionAdapter);
    }

    protected void initComponent(){
        setSupportActionBar(toolBar);
        getSupportActionBar().setTitle("Kelolaan");
        toolBar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp);
        toolBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // back button pressed
                finish();
            }
        });

        relayWilayahProgressBar.setVisibility(View.VISIBLE);

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
                WilayahBangunanActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        relayWilayahProgressBar.setVisibility(View.GONE);
                        Toast.makeText(getApplicationContext(), "Koneksi Internet Bermasalah", Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    final String strJson = response.body().string();
                    WilayahBangunanActivity.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                relayWilayahProgressBar.setVisibility(View.GONE);
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
                                    data.add(rg);
                                }
                                bangunanRegionAdapter.notifyDataSetChanged();

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
