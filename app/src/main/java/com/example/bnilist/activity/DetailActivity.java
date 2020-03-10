package com.example.bnilist.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.balysv.materialripple.MaterialRippleLayout;
import com.example.bnilist.R;
import com.example.bnilist.Tools;
import com.example.bnilist.model.AssetModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailActivity extends AppCompatActivity {

    //VIEWS

    @BindView(R.id.tvNama)
    TextView tvNama;
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
    @BindView(R.id.btnMaps)
    Button btnMaps;
    @BindView(R.id.toolBar)
    Toolbar toolBar;
    //tambahan
    private ViewPager viewPager;
    private LinearLayout layout_dots;
    private Runnable runnable = null;
    private Handler handler = new Handler();
    private AdapterImageSlider adapterImageSlider;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);
        setSupportActionBar(toolBar);
        getSupportActionBar().setTitle("BNI PFA");
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

        //RECEIVE OUR DATA
        Intent i=getIntent();
        final int[] arrImage = i.getExtras().getIntArray("Image");
        final String name = i.getExtras().getString("Nama");
        final String kota = i.getExtras().getString("Kota");
        final String address = i.getExtras().getString("Address");
        final String noparentasset = i.getExtras().getString("Npa");
        final String nibparentasset = i.getExtras().getString("Nib");
        final String kelasparentasset = i.getExtras().getString("Kpa");
        final String kelurahan = i.getExtras().getString("Kelurahan");
        final String kecamatan = i.getExtras().getString("Kecamatan");
        final String provinsi = i.getExtras().getString("Provinsi");
        final String kodepos = i.getExtras().getString("Kodepos");
        final String lt = i.getExtras().getString("Luastanah");
        final String lb = i.getExtras().getString("Luasbangunan");
        final String jumlahlantai = i.getExtras().getString("Jumlahlantai");
        final String legalitas = i.getExtras().getString("Legalitas");
        final String noimb = i.getExtras().getString("Noimb");
        final String nopajak = i.getExtras().getString("Nopajak");
        final String ls = i.getExtras().getString("Latitude");
        final String bt = i.getExtras().getString("Longitude");

        //tambahan
        layout_dots = (LinearLayout) findViewById(R.id.layout_dots);
        viewPager = (ViewPager) findViewById(R.id.pager);
        adapterImageSlider = new AdapterImageSlider(this, new ArrayList<AssetModel>());
        final List<AssetModel> items = new ArrayList<>();
        for (int j = 0; j < arrImage.length; j++) {
            AssetModel obj = new AssetModel();
            obj.image = arrImage[j];
            obj.imageDrw = getResources().getDrawable(obj.image);
            items.add(obj);
        }
        adapterImageSlider.setItems(items);
        viewPager.setAdapter(adapterImageSlider);
        // displaying selected image first
        viewPager.setCurrentItem(0);
        addBottomDots(layout_dots, adapterImageSlider.getCount(), 0);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int pos, float positionOffset, int positionOffsetPixels) {
            }
            @Override
            public void onPageSelected(int pos) {
                addBottomDots(layout_dots, adapterImageSlider.getCount(), pos);
            }
            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });

        //ASSIGN DATA TO THOSE VIEWS
//        img.setImageResource(arrImage[0]);
        tvNama.setText(name);
        tvKota.setText(kota);
        tvAddress.setText(address);
        tvNpa.setText(noparentasset);
        tvNib.setText(nibparentasset);
        tvKpa.setText(kelasparentasset);
        tvKelurahan.setText(kelurahan);
        tvKecamatan.setText(kecamatan);
        tvProvinsi.setText(provinsi);
        tvKodepos.setText(kodepos);
        tvLuastanah.setText(lt);
        tvLuasBangunan.setText(lb);
        tvJmlantai.setText(jumlahlantai);
        tvDoklegal.setText(legalitas);
        tvNoimb.setText(noimb);
        tvNmrpjk.setText(nopajak);
        btnMaps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri gmmIntentUri = Uri.parse("geo:0,0?q=-6.295635,106.665860");
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                mapIntent.setPackage("com.google.android.apps.maps");
                startActivity(mapIntent);

            }
        });
    }

    private void addBottomDots(LinearLayout layout_dots, int size, int current) {
        ImageView[] dots = new ImageView[size];
        layout_dots.removeAllViews();
        for (int i = 0; i < dots.length; i++) {
            dots[i] = new ImageView(this);
            int width_height = 15;
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(new ViewGroup.LayoutParams(width_height, width_height));
            params.setMargins(10, 0, 10, 0);
            dots[i].setLayoutParams(params);
            dots[i].setImageResource(R.drawable.shape_circle_outline);
            dots[i].setColorFilter(ContextCompat.getColor(this, R.color.grey_40), PorterDuff.Mode.SRC_ATOP);
            layout_dots.addView(dots[i]);
        }
        if (dots.length > 0) {
            dots[current].setImageResource(R.drawable.shape_circle);
            dots[current].setColorFilter(ContextCompat.getColor(this, R.color.grey_40), PorterDuff.Mode.SRC_ATOP);
        }
    }

    private static class AdapterImageSlider extends PagerAdapter {
        private Activity act;
        private List<AssetModel> items;
        private AdapterImageSlider.OnItemClickListener onItemClickListener;
        private interface OnItemClickListener {
            void onItemClick(View view, AssetModel obj);
        }
        public void setOnItemClickListener(AdapterImageSlider.OnItemClickListener onItemClickListener) {
            this.onItemClickListener = onItemClickListener;
        }
        // constructor
        private AdapterImageSlider(Activity activity, List<AssetModel> items) {
            this.act = activity;
            this.items = items;
        }
        @Override
        public int getCount() {
            return this.items.size();
        }
        public AssetModel getItem(int pos) {
            return items.get(pos);
        }
        public void setItems(List<AssetModel> items) {
            this.items = items;
            notifyDataSetChanged();
        }
        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == ((RelativeLayout) object);
        }
        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            final AssetModel o = items.get(position);
            LayoutInflater inflater = (LayoutInflater) act.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View v = inflater.inflate(R.layout.login_slider_image, container, false);
            ImageView image = (ImageView) v.findViewById(R.id.image);
            MaterialRippleLayout lyt_parent = (MaterialRippleLayout) v.findViewById(R.id.lyt_parent);
            Tools.displayImageOriginal(act, image, o.image);
            lyt_parent.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(final View v) {
                    if (onItemClickListener != null) {
                        onItemClickListener.onItemClick(v, o);
                    }
                }
            });
            ((ViewPager) container).addView(v);
            return v;
        }
        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            ((ViewPager) container).removeView((RelativeLayout) object);
        }
    }

    @Override
    public void onDestroy() {
        if (runnable != null) handler.removeCallbacks(runnable);
        super.onDestroy();
    }
}
