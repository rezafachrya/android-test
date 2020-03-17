package com.example.bnilist.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.bnilist.R;
import com.example.bnilist.adapter.RegionAdapter;
import com.example.bnilist.api.ApiCall;
import com.example.bnilist.helper.ConfigHelper;
import com.example.bnilist.model.RegionModel;
import com.example.bnilist.model.RegionResponseModel;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.ontbee.legacyforks.cn.pedant.SweetAlert.SweetAlertDialog;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.OkHttpClient;

public class WilayahKantorActivity extends AppCompatActivity {
    @BindView(R.id.toolBar)
    Toolbar toolBar;
    @BindView(R.id.rcWilayahKantor)
    RecyclerView rcWilayahKantor;
    private SweetAlertDialog pDialog;
    private String path, response;
    private OkHttpClient client;
    private RegionResponseModel regionResponseModel;
    private ApiCall apiCall;
    private ArrayList<RegionModel> data;
    private RegionAdapter regionAdapter;
    private RecyclerView.LayoutManager layoutManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wilayah_kantor);
        ButterKnife.bind(this);
        initComponent();
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

        pDialog = new SweetAlertDialog(this, SweetAlertDialog.SUCCESS_TYPE);
        apiCall = new ApiCall();
        data = new ArrayList<>();
        layoutManager = new LinearLayoutManager(this);
        rcWilayahKantor.setLayoutManager(layoutManager);
        rcWilayahKantor.setHasFixedSize(true);
        try {
            path = ConfigHelper.BASEURL_REGION;
            new GetDataFromServer().execute();
        } catch (Exception e) {

        }
    }

    private class GetDataFromServer extends AsyncTask<Void, Void, Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            try {
                pDialog.getProgressHelper().setBarColor(Color
                        .parseColor("#26A65B"));
                pDialog.setTitleText("Loading");
                pDialog.setCancelable(false);
                pDialog.show();
            } catch (Exception e) {
            }
        }
        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            if(pDialog.isShowing()) {
                pDialog.dismiss();
            }
            regionAdapter = new RegionAdapter(WilayahKantorActivity.this,
                    data);
            rcWilayahKantor.setAdapter(regionAdapter);
        }
        @Override
        protected Void doInBackground(Void... voids) {
            try {
                client = new OkHttpClient();
                response = apiCall.GET(client, path);
                Log.e("##JSON:", response);
                Gson gson = new Gson();
                Type type = new TypeToken<Collection<RegionModel>>() {
                }.getType();
                regionResponseModel = gson.fromJson(response, RegionResponseModel.class);
                if(data.isEmpty()) {
                    for(int i = 0; i < regionResponseModel.getData().size(); i++) {
                        data.add(regionResponseModel.getData().get(i));
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
    }

}
