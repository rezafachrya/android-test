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
import com.example.bnilist.model.AssetModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BangunanWpadActivity extends AppCompatActivity {
    @BindView(R.id.rcKp)
    RecyclerView rcKp;
    @BindView(R.id.toolBar)
    Toolbar toolBar;
    @BindView(R.id.spBangunan)
    SmartMaterialSpinner spBangunan;

    private AssetAdapter adapter;
    private List<String> jnsList;
    private String jnsSelected;
    private ArrayList<AssetModel> tempJenisList;

    private static int[] arrImage = {
            R.drawable.villadepan,
            R.drawable.villasamping,
            R.drawable.villatanah,
            R.drawable.villamaps
    };

    private static int[] arrImage1 = {
            R.drawable.tanahdepan,
            R.drawable.tanahsamping,
            R.drawable.tanahgambar,
            R.drawable.tanahmaps
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bangunan);
        ButterKnife.bind(this);
        initComponent();
    }

    protected void initComponent() {
        setSupportActionBar(toolBar);
        getSupportActionBar().setTitle("BNI PFA");
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
        adapter= new AssetAdapter(this,getBuildings());
        rcKp.setAdapter(adapter);

        jnsList = new ArrayList<>();
        jnsList.add("Semua Jenis");
        jnsList.add("Villa");
        jnsList.add("Rumah Dinas");
        jnsList.add("Tanah");
        jnsList.add("Gudang");

        spBangunan.setItem(jnsList);
        spBangunan.setSelection(0);

        spBangunan.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                jnsSelected = jnsList.get(position);
                tempJenisList = new ArrayList<>();
                if(jnsSelected.toLowerCase().equals("Semua Jenis".toLowerCase())){
                    for (AssetModel assetModel:getBuildings()){
                        tempJenisList.add(assetModel);
                    }
                    adapter=new AssetAdapter(getApplicationContext(),tempJenisList);
                    rcKp.setAdapter(adapter);
                    spBangunan.setSelection(position);
                }else{
                    for (AssetModel nonKantorModel:getBuildings()){
                        if (nonKantorModel.getProvinsi() != null){
                            if (jnsSelected.toLowerCase().equals(nonKantorModel.getJenis().toLowerCase())){
                                tempJenisList.add(nonKantorModel);
                            }
                        }
                    }
                    adapter=new AssetAdapter(getApplicationContext(),tempJenisList);
                    rcKp.setAdapter(adapter);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private ArrayList<AssetModel> getBuildings() {
        ArrayList<AssetModel> buildings= new ArrayList<>();
        AssetModel a=new AssetModel();
        a.setNpa("110249");
        a.setNib("2003-723-1-8-000227");
        a.setKpa("Bukan Kantor");
        a.setNama("Villa");
        a.setImg(arrImage);
        a.setAlamat("Jalan Cijayanti, Karet Villa Bukit Pelangi Sentul");
        a.setKelurahan("Cijayanti");
        a.setKecamatan("Babakan Madang");
        a.setKota("Bogor");
        a.setProvinsi("Jawa Barat");
        a.setKodepos("16810");
        a.setLuastanah("3.155");
        a.setLuasbangunan("1.621");
        a.setJml_lantai("1 Lantai");
        a.setDoc_legal("SHM 595");
        a.setNop("32.02.121.009.019-0007.0");
        a.setLongitude("-6.566282");
        a.setLatitude("106.844679");
        a.setJenis("Villa");
//        p.setListImage(getImage());
        buildings.add(a);

        return buildings;
    }
}
