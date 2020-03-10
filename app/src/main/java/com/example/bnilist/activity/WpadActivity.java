package com.example.bnilist.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import com.example.bnilist.R;
import com.example.bnilist.adapter.AssetAdapter;
import com.example.bnilist.model.AssetModel;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WpadActivity extends AppCompatActivity {

    @BindView(R.id.rcKp)
    RecyclerView rcKp;
    @BindView(R.id.toolBar)
    Toolbar toolBar;

    private AssetAdapter adapter;

    private static int[] arrImage = {
            R.drawable.plazadepan,
            R.drawable.plazasamping,
            R.drawable.plazatanah,
            R.drawable.plazatanah
    };

    private static int[] arrImage1 = {
            R.drawable.bnipejompongan,
            R.drawable.bnikc,
            R.drawable.bnibsd,
            R.drawable.bnikc
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kp);
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
        adapter= new AssetAdapter(this,getOffices());
        rcKp.setAdapter(adapter);
    }

    private ArrayList<AssetModel> getOffices() {

        ArrayList<AssetModel> offices = new ArrayList<>();
        AssetModel o=new AssetModel();
        o.setNpa("211849");
        o.setNib("2011-612-1-8-000001");
        o.setKpa("Gedung Kantor");
        o.setNama("Plaza BNI");
        o.setImg(arrImage);
        o.setAlamat("Jl. Pahlawan Seribu Kav. Sun Burst Lot.1 No 5 BSD City, Tangerang Banten");
        o.setKelurahan("Lengkong Gudang");
        o.setKecamatan("Serpong");
        o.setKota("Tangerang Selatan");
        o.setProvinsi("Banten");
        o.setKodepos("15310");
        o.setLuastanah("7289");
        o.setLuasbangunan("-");
        o.setJml_lantai("20 Lantai 3 Basement");
        o.setDoc_legal("SHGB No. 06198, SHGB No. 06177, SHGB No.06188");
        o.setNop("36.76.050.015.005-0212.0");
        o.setLongitude("-6.295635");
        o.setLatitude("106.665860");
        o.setWilayah("kp");
        o.setJenis("kantor");
//        p.setListImage(getImage());
        offices.add(o);

        o=new AssetModel();
        o.setNpa("110188");
        o.setNib("2003-723-1-8-000138");
        o.setKpa("Gedung Kantor");
        o.setNama("Gedung Menara Pejompongan");
        o.setImg(arrImage1);
        o.setAlamat("KOMPLEK PEJOMPONGAN No. 7-43");
        o.setKelurahan("Bendungan Hilir");
        o.setKecamatan("Tanah Abang");
        o.setKota("Jakarta Pusat");
        o.setProvinsi("Jakarta");
        o.setKodepos("10210");
        o.setLuastanah("-");
        o.setLuasbangunan("-");
        o.setJml_lantai("-");
        o.setDoc_legal("-");
        o.setNop("-");
        o.setLongitude("-6.203732");
        o.setLatitude("106.804083");
        o.setWilayah("wpd");
        o.setJenis("kantor");
        offices.add(o);

        return offices;
    }
}
