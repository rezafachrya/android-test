package com.example.bnilist.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chivorn.smartmaterialspinner.SmartMaterialSpinner;
import com.example.bnilist.R;
import com.example.bnilist.adapter.AssetAdapter;
import com.example.bnilist.adapter.TassetAdapter;
import com.example.bnilist.model.TassetDetailModel;
import com.example.bnilist.model.TassetModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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

public class BangunanActivity extends AppCompatActivity {
    @BindView(R.id.rcKp)
    RecyclerView rcKp;
    @BindView(R.id.toolBar)
    Toolbar toolBar;
    @BindView(R.id.spBangunan)
    SmartMaterialSpinner spBangunan;

    private AssetAdapter adapter;
    private List<String> jnsList;
    private String jnsSelected;

    private ArrayList<TassetModel> data = new ArrayList<>();
    private ArrayList<TassetModel> tempJenisList;
    private TassetAdapter tassetAdapter;

    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    OkHttpClient client = new OkHttpClient();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bangunan);
        ButterKnife.bind(this);
        String koderegion = getIntent().getStringExtra("id");
        getBangunanList(BASEURL_ASSET, koderegion);
        initComponent();
    }

    protected void initComponent() {
        setSupportActionBar(toolBar);
        getSupportActionBar().setTitle("List Aset");
        toolBar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp);
        toolBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // back button pressed
                finish();
            }
        });
        //SET ITS PROPERTIES
        rcKp.setLayoutManager(new LinearLayoutManager(this));
        rcKp.setItemAnimator(new DefaultItemAnimator());
        //ADAPTER
//        adapter= new AssetAdapter(this,getBuildings());
//        rcKp.setAdapter(adapter);

        jnsList = new ArrayList<>();
        jnsList.add("Semua Jenis");
        jnsList.add("Kantor");
        jnsList.add("Rumah Dinas");
        jnsList.add("Villa");
        jnsList.add("Gudang");

        spBangunan.setItem(jnsList);
        spBangunan.setSelection(0);

        spBangunan.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                jnsSelected = jnsList.get(position);
                tempJenisList = new ArrayList<>();
                if (jnsSelected.toLowerCase().equals("Semua Jenis".toLowerCase())) {
//                    tempJenisList.addAll(data);
                    for (TassetModel assetModel : data) {
                        tempJenisList.add(assetModel);
                    }
                    tassetAdapter = new TassetAdapter(getApplicationContext(), tempJenisList);
                    rcKp.setAdapter(tassetAdapter);
                    spBangunan.setSelection(position);
                }
                else {
                    for (TassetModel assetModel : data) {
                        if (assetModel.getAssettype() != null) {
                            if (jnsSelected.toLowerCase().equals(assetModel.getAssettype().toLowerCase())) {
                                tempJenisList.add(assetModel);
                            }
                        }
                    }
                    tassetAdapter = new TassetAdapter(getApplicationContext(), tempJenisList);
                    rcKp.setAdapter(tassetAdapter);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void getBangunanList(String postUrl, String koderegion) {
        JSONObject jsonReq = new JSONObject();
        try {
            jsonReq.put("koderegion", koderegion);
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
                    BangunanActivity.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                JSONObject jsonObject = new JSONObject(strJson);
                                JSONArray jsonArray = jsonObject.getJSONArray("data");
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                                    Integer id = jsonObject1.getInt("id");
                                    String nama = jsonObject1.getString("nama");
                                    String kpa = jsonObject1.getString("kpa");
                                    String npa = jsonObject1.getString("npa");
                                    String alamat = jsonObject1.getString("alamat");
                                    String provinsi = jsonObject1.getString("provinsi");
                                    String kota = jsonObject1.getString("kota");
                                    String kelurahan = jsonObject1.getString("kelurahan");
                                    String kecamatan = jsonObject1.getString("kecamatan");
                                    String kodepos = jsonObject1.getString("kodepos");
                                    String jmllantai = jsonObject1.getString("jmllantai");
                                    String luasbangunan = jsonObject1.getString("luasbangunan");
                                    String nop = jsonObject1.getString("nop");
                                    String doclegal = jsonObject1.getString("doclegal");
                                    String luastanah = jsonObject1.getString("luastanah");
                                    String noimb = jsonObject1.getString("noimb");
                                    String nib = jsonObject1.getString("nib");
                                    String thnrevaluasi = jsonObject1.getString("thnrevaluasi");
                                    String urlimage1 = jsonObject1.getString("urlimage1");
                                    String urlimage2 = jsonObject1.getString("urlimage2");
                                    String urlimage3 = jsonObject1.getString("urlimage3");
                                    String urlimage4 = jsonObject1.getString("urlimage4");
                                    String longitude = jsonObject1.getString("longitude");
                                    String latitude = jsonObject1.getString("latitude");
                                    String assettype = jsonObject1.getString("assettype");
//                                    assetdetail = new ArrayList<>();
//                                    assetdetail.clear();
                                    ArrayList<TassetDetailModel> assetdetail = new ArrayList<>();
                                    JSONArray detaildataArray = jsonObject1.getJSONArray("detaildata");
                                    for (int j = 0; j < detaildataArray.length(); j++) {
                                        JSONObject detaildata = detaildataArray.getJSONObject(j);
                                        Integer detaildatapk = detaildata.getInt("id");
                                        String perolehan = detaildata.getString("perolehan");
                                        String harga = detaildata.getString("harga");
                                        String nilaibuku = detaildata.getString("nilaibuku");
                                        String massetcompfk = detaildata.getString("massetcompfk");

                                        TassetDetailModel tsd = new TassetDetailModel();
                                        tsd.setId(detaildatapk);
                                        tsd.setPerolehan(perolehan);
                                        tsd.setHarga(harga);
                                        tsd.setNilaibuku(nilaibuku);
                                        tsd.setMassetcompfk(massetcompfk);
                                        assetdetail.add(tsd);
                                    }
                                    TassetModel ts = new TassetModel();
                                    ts.setId(id);
                                    ts.setNama(nama);
                                    ts.setKpa(kpa);
                                    ts.setNpa(npa);
                                    ts.setAlamat(alamat);
                                    ts.setProvinsi(provinsi);
                                    ts.setKota(kota);
                                    ts.setKecamatan(kecamatan);
                                    ts.setKelurahan(kelurahan);
                                    ts.setKodepos(kodepos);
                                    ts.setJmllantai(jmllantai);
                                    ts.setLuasbangunan(luasbangunan);
                                    ts.setNop(nop);
                                    ts.setDoclegal(doclegal);
                                    ts.setLuastanah(luastanah);
                                    ts.setNoimb(noimb);
                                    ts.setNib(nib);
                                    ts.setThnrevaluasi(thnrevaluasi);
                                    ts.setUrlimage1(urlimage1);
                                    ts.setUrlimage2(urlimage2);
                                    ts.setUrlimage3(urlimage3);
                                    ts.setUrlimage4(urlimage4);
                                    ts.setLongitude(longitude);
                                    ts.setLatitude(latitude);
                                    ts.setAssettype(assettype);
                                    ts.setDetaildata(assetdetail);
                                    if (kpa.toLowerCase().equals("Bangunan".toLowerCase())) {
                                        data.add(ts);
                                    }
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
