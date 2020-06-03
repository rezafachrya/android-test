package com.example.bnilist.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import com.example.bnilist.R;
import com.example.bnilist.adapter.SlideShowUrlAdapter;

import com.example.bnilist.model.TassetModel;
import com.example.bnilist.utils.UtilHelper;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

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
    @BindView(R.id.tvRevalTanah)
    TextView tvRevalTanah;
    @BindView(R.id.tvPerolehanTanah)
    TextView tvPerolehanTanah;
    @BindView(R.id.tvNilaibukuTanah)
    TextView tvNilaibukuTanah;
    @BindView(R.id.tvRevalBangunan)
    TextView tvRevalBangunan;
    @BindView(R.id.tvPerolehanBangunan)
    TextView tvPerolehanBangunan;
    @BindView(R.id.tvNilaibukuBangunan)
    TextView tvNilaibukuBangunan;
    @BindView(R.id.tvRevalTotal)
    TextView tvRevalTotal;
    @BindView(R.id.tvPerolehanTotal)
    TextView tvPerolehanTotal;
    @BindView(R.id.tvNilaibukuTotal)
    TextView tvNilaibukuTotal;
    @BindView(R.id.toolBar)
    Toolbar toolBar;
    @BindView(R.id.pager)
    ViewPager viewPager;
    @BindView(R.id.circleIndicator_ID)
    CircleIndicator indicator3;
    @BindView(R.id.fabMaps)
    FloatingActionButton fabMaps;
    //tambahan
    private Handler handler;
    private Runnable runnable;
    private Timer timer;
    private SlideShowUrlAdapter slideShowUrlAdapter;
    private float revalTanah;
    private float perolehanTanah;
    private float nilaibukuTanah;
    private float revalBangunan;
    private float perolehanBangunan;
    private float nilaiBukuBangunan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);
        setSupportActionBar(toolBar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
        );

        initComponent();
    }

    protected void initComponent() {
        toolBar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp);
        toolBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // back button pressed
                finish();
            }
        });
        //RECEIVE OUR DATA
        Intent i = getIntent();
        TassetModel tassetModel = (TassetModel) Objects.requireNonNull(i.getExtras()).getSerializable("data");
        float totalReval = Float.parseFloat(tassetModel.getDetaildata().get(0).getHarga()) + Float.parseFloat(tassetModel.getDetaildata().get(1).getHarga());
        float totalPerolehan = Float.parseFloat(tassetModel.getDetaildata().get(0).getPerolehan()) + Float.parseFloat(tassetModel.getDetaildata().get(1).getPerolehan());
        float totalNilaiBuku = Float.parseFloat(tassetModel.getDetaildata().get(0).getNilaibuku()) + Float.parseFloat(tassetModel.getDetaildata().get(1).getNilaibuku());
        for (int j = 0; j < tassetModel.getDetaildata().size(); j++) {
            if (tassetModel.getDetaildata().get(j).getMassetcompfk().equals("0")) {
                revalTanah = Float.parseFloat(tassetModel.getDetaildata().get(j).getHarga());
                perolehanTanah = Float.parseFloat(tassetModel.getDetaildata().get(j).getPerolehan());
                nilaibukuTanah = Float.parseFloat(tassetModel.getDetaildata().get(j).getNilaibuku());
            } else {
                revalBangunan = Float.parseFloat(tassetModel.getDetaildata().get(j).getHarga());
                perolehanBangunan = Float.parseFloat(tassetModel.getDetaildata().get(j).getPerolehan());
                nilaiBukuBangunan = Float.parseFloat(tassetModel.getDetaildata().get(j).getNilaibuku());
            }
        }
        String[] imgs = {
                tassetModel.getUrlimage1(),
                tassetModel.getUrlimage2(),
                tassetModel.getUrlimage3(),
                tassetModel.getUrlimage4()
        };

        String strTotalReval = UtilHelper.thousandFormatNumber(String.format("%.0f", totalReval));
        String strTotalPerolehan = UtilHelper.thousandFormatNumber(String.format("%.0f", totalPerolehan));
        String strTotalNilaiBuku = UtilHelper.thousandFormatNumber(String.format("%.0f", totalNilaiBuku));
        String strRevalTanah = UtilHelper.thousandFormatNumber(String.format("%.0f", revalTanah));
        String strPerolehanTanah = UtilHelper.thousandFormatNumber(String.format("%.0f", perolehanTanah));
        String strNilaiBukuTanah = UtilHelper.thousandFormatNumber(String.format("%.0f", nilaibukuTanah));
        String strRevalBangunan = UtilHelper.thousandFormatNumber(String.format("%.0f", revalBangunan));
        String strPerolehanBangunan = UtilHelper.thousandFormatNumber(String.format("%.0f", perolehanBangunan));
        String strNilaiBukuBangungan = UtilHelper.thousandFormatNumber(String.format("%.0f", nilaiBukuBangunan));

//        //ASSIGN DATA TO THOSE VIEWS
        Objects.requireNonNull(getSupportActionBar()).setTitle(tassetModel.getNama());
        tvKota.setText(tassetModel.getKota());
        tvAddress.setText(tassetModel.getAlamat());
        tvNpa.setText(tassetModel.getNpa());
        tvNib.setText(tassetModel.getNib());
        tvKpa.setText(tassetModel.getKpa());
        tvKelurahan.setText(tassetModel.getKelurahan());
        tvKecamatan.setText(tassetModel.getKecamatan());
        tvProvinsi.setText(tassetModel.getProvinsi());
        tvKodepos.setText(tassetModel.getKodepos());
        tvLuastanah.setText(tassetModel.getLuastanah());
        tvLuasBangunan.setText(tassetModel.getLuasbangunan());
        tvJmlantai.setText(tassetModel.getJmllantai());
        tvDoklegal.setText(tassetModel.getDoclegal());
        tvNoimb.setText(tassetModel.getNoimb());
        tvNmrpjk.setText(tassetModel.getNop());
        tvRevalTotal.setText(strTotalReval);
        tvPerolehanTotal.setText(strTotalPerolehan);
        tvNilaibukuTotal.setText(strTotalNilaiBuku);
        tvRevalTanah.setText(strRevalTanah);
        tvPerolehanTanah.setText(strPerolehanTanah);
        tvNilaibukuTanah.setText(strNilaiBukuTanah);
        tvRevalBangunan.setText(strRevalBangunan);
        tvPerolehanBangunan.setText(strPerolehanBangunan);
        tvNilaibukuBangunan.setText(strNilaiBukuBangungan);

        slideShowUrlAdapter = new SlideShowUrlAdapter(this, imgs);
        viewPager.setAdapter(slideShowUrlAdapter);
        indicator3.setViewPager(viewPager);

        fabMaps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String uri = String.format("geo:0,0?q=%s,%s", tassetModel.getLatitude(), tassetModel.getLongitude());
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
                intent.setPackage("com.google.android.apps.maps");
                startActivity(intent);
            }
        });
    }
}
