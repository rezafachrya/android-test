package com.example.bnilist.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;

import com.example.bnilist.R;
import com.example.bnilist.adapter.SlideShowUrlAdapter;
import com.example.bnilist.model.TassetDetailModel;
import com.example.bnilist.model.TassetModel;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.relex.circleindicator.CircleIndicator;

public class DetailActivity extends AppCompatActivity {

    @BindView(R.id.tvKota)
    TextView tvKota;
    @BindView(R.id.tvAddress)
    TextView tvAddress;
    @BindView(R.id.tvNpa)
    TextView tvNpa;
    @BindView(R.id.tvNib)
    TextView tvNib;
    @BindView(R.id.tvKpa)
    TextView tvKpa;
    @BindView(R.id.tvKelurahan)
    TextView tvKelurahan;
    @BindView(R.id.tvKecamatan)
    TextView tvKecamatan;
    @BindView(R.id.tvProvinsi)
    TextView tvProvinsi;
    @BindView(R.id.tvKodepos)
    TextView tvKodepos;
    @BindView(R.id.tvLuastanah)
    TextView tvLuastanah;
    @BindView(R.id.tvLuasbangunan)
    TextView tvLuasBangunan;
    @BindView(R.id.tvJmlantai)
    TextView tvJmlantai;
    @BindView(R.id.tvDoklegal)
    TextView tvDoklegal;
    @BindView(R.id.tvNoimb)
    TextView tvNoimb;
    @BindView(R.id.tvNmrpjk)
    TextView tvNmrpjk;
    @BindView(R.id.perolehan1)
    TextView perolehan1;
    @BindView(R.id.toolBar)
    Toolbar toolBar;
    @BindView(R.id.pager)
    ViewPager viewPager;
    @BindView(R.id.circleIndicator_ID)
    CircleIndicator indicator3;
    //tambahan
    private Handler handler;
    private Runnable runnable;
    private Timer timer;
    private SlideShowUrlAdapter slideShowUrlAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);
        setSupportActionBar(toolBar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        initComponent();
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
        //images
        String[] imgs = {
                "https://cdn.idntimes.com/content-images/duniaku/post/20200121/digimon-psi-5a13dc74a5c35dc9538a647bb6696116.jpg",
                "https://images-na.ssl-images-amazon.com/images/I/61S51R1F1AL._SX258_BO1,204,203,200_.jpg",
                "https://punishedhag.files.wordpress.com/2017/06/l-subs-digimon-universe-appli-monsters-31-e04515b3720p-mkv_snapshot_14-03_2017-05-19_13-20-16.jpg?w=1088",
        };
        slideShowUrlAdapter = new SlideShowUrlAdapter(this, imgs);
        viewPager.setAdapter(slideShowUrlAdapter);
        indicator3.setViewPager(viewPager);
        handler = new Handler();

        //RECEIVE OUR DATA
        Intent i=getIntent();
        //cast to get extra
//        TassetModel tassetModel = (TassetModel) i.getExtras().getSerializable("data");
        TassetModel tassetModel = (TassetModel) Objects.requireNonNull(i.getExtras()).getSerializable("data");
//        //ASSIGN DATA TO THOSE VIEWS
        getSupportActionBar().setTitle(tassetModel != null ? tassetModel.getNama() : "");
        tvKota.setText(tassetModel != null ? tassetModel.getKota() : "");
        tvAddress.setText(tassetModel != null ? tassetModel.getAlamat() : "");
        tvNpa.setText(tassetModel != null ? tassetModel.getNpa() : "");
        tvNib.setText(tassetModel != null ? tassetModel.getNib() : "");
        tvKpa.setText(tassetModel != null ? tassetModel.getKpa() : "");
        tvKelurahan.setText(tassetModel != null ? tassetModel.getKelurahan() : "");
        tvKecamatan.setText(tassetModel != null ? tassetModel.getKecamatan() : "");
        tvProvinsi.setText(tassetModel != null ? tassetModel.getProvinsi() : "");
        tvKodepos.setText(tassetModel != null ? tassetModel.getKodepos() : "");
        tvLuastanah.setText(tassetModel != null ? tassetModel.getLuastanah() : "");
        tvLuasBangunan.setText(tassetModel != null ? tassetModel.getLuasbangunan() : "");
        tvJmlantai.setText(tassetModel != null ? tassetModel.getJmllantai() : "");
        tvDoklegal.setText(tassetModel != null ? tassetModel.getDoclegal() : "");
        tvNoimb.setText(tassetModel != null ? tassetModel.getNoimb() : "");
        tvNmrpjk.setText(tassetModel != null ? tassetModel.getNop() : "");
        perolehan1.setText(tassetModel.getDetaildata().get(0).getPerolehan());
//        btnMaps.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Uri gmmIntentUri = Uri.parse("geo:0,0?q=-6.295635,106.665860");
//                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
//                mapIntent.setPackage("com.google.android.apps.maps");
//                startActivity(mapIntent);
//
//            }
//        });

        runnable = () -> {
            int i1 = viewPager.getCurrentItem();
            if(i1 == slideShowUrlAdapter.images.length -1) {
                i1 = 0;
                viewPager.setCurrentItem(i1, true);
            } else {
                i1++;
                viewPager.setCurrentItem(i1, true);
            }
        };

        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(runnable);
            }
        }, 4000, 4000);
    }
}
