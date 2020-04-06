package com.example.bnilist.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bnilist.R;
import com.example.bnilist.adapter.RegionAdapter;
import com.example.bnilist.adapter.TassetAdapter;
import com.example.bnilist.model.RegionModel;
import com.example.bnilist.model.TassetModel;
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

import static com.example.bnilist.helper.ConfigHelper.BASEURL_ASSET;

public class KantorActivity extends AppCompatActivity {

    @BindView(R.id.toolBar)
    Toolbar toolBar;
    @BindView(R.id.rcKantor)
    RecyclerView rcKantor;
    private ArrayList<TassetModel> data = new ArrayList<>();
    private TassetAdapter tassetAdapter;

    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    OkHttpClient client = new OkHttpClient();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kantor);
        ButterKnife.bind(this);
        setSupportActionBar(toolBar);
        getSupportActionBar().setTitle("BNI PFA");
        initComponent();
        String koderegion = getIntent().getStringExtra("id");
        getKantorList(BASEURL_ASSET, koderegion);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        rcKantor.setLayoutManager(layoutManager);
        rcKantor.setHasFixedSize(true);
        tassetAdapter = new TassetAdapter(this,data);
        rcKantor.setAdapter(tassetAdapter);
    }

    protected void initComponent(){
        toolBar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp);
        toolBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // back button pressed
                finish();
            }
        });

    }

    private void getKantorList(String postUrl, String koderegion){
        JSONObject jsonReq = new JSONObject();
        try {
            jsonReq.put("koderegion",koderegion);
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
                    KantorActivity.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                JSONObject jsonObject = new JSONObject(strJson);
                                JSONArray jsonArray = jsonObject.getJSONArray("data");
                                for(int i = 0; i < jsonArray.length(); i++) {
                                    TassetModel ts = new TassetModel();
                                    JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                                    Integer id = jsonObject1.getInt("id");
                                    String nama = jsonObject1.getString("nama");
                                    ts.setId(id);
                                    ts.setNama(nama);
                                    tassetAdapter.notifyDataSetChanged();
                                    data.add(ts);
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
