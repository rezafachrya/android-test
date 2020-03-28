package com.example.bnilist.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.balysv.materialripple.MaterialRippleLayout;
import com.dialog.plus.ui.DialogPlus;
import com.dialog.plus.ui.DialogPlusBuilder;
import com.example.bnilist.MainActivity;
import com.example.bnilist.R;
import com.example.bnilist.Tools;
import com.example.bnilist.helper.DialogHelper;
import com.example.bnilist.model.DashboardModel;
import com.example.bnilist.model.LoginModel;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Callback;
import okhttp3.Call;
import okhttp3.Response;


import static com.example.bnilist.helper.ConfigHelper.BASEURL;

public class LoginActivity extends AppCompatActivity {

    @BindView(R.id.btnMasuk)
    Button btnMasuk;
    @BindView(R.id.layout_dots)
    LinearLayout layout_dots;
    @BindView(R.id.pager)
    ViewPager viewPager;
    @BindView(R.id.etPhone)
    TextInputEditText etPhone;
    @BindView(R.id.outlinedTextField)
    TextInputLayout outlinedTextField;
    private AdapterImageSlider adapterImageSlider;
    private Runnable runnable = null;
    private Handler handler = new Handler();
    private static int[] array_image_place = {
            R.drawable.bnikc,
            R.drawable.bnipejompongan,
            R.drawable.bnibsd,
    };

    ProgressDialog loading;
    Context mContext;

    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    LoginModel loginModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
        );
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        initComponent();
    }

    private void initComponent() {
        mContext = this;
        adapterImageSlider = new AdapterImageSlider(this, new ArrayList<DashboardModel>());
        final List<DashboardModel> items = new ArrayList<>();
        for (int i = 0; i < array_image_place.length; i++) {
            DashboardModel obj = new DashboardModel();
            obj.image = array_image_place[i];
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
        startAutoSlider(adapterImageSlider.getCount());
        //button
        btnMasuk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String msg = validasi();
                if (msg != null) {
                    DialogHelper.onClickedErrorDialog(LoginActivity.this, msg);
                    return;
                }
                try {
                    reqLogin(BASEURL, etPhone.getText().toString().trim());
                } catch (Exception e) {
                    e.printStackTrace();
                }
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

    private void startAutoSlider(final int count) {
        runnable = new Runnable() {
            @Override
            public void run() {
                int pos = viewPager.getCurrentItem();
                pos = pos + 1;
                if (pos >= count) pos = 0;
                viewPager.setCurrentItem(pos);
                handler.postDelayed(runnable, 3000);
            }
        };
        handler.postDelayed(runnable, 3000);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        } else {
            Toast.makeText(getApplicationContext(), item.getTitle(), Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }

    private static class AdapterImageSlider extends PagerAdapter {
        private Activity act;
        private List<DashboardModel> items;
        private AdapterImageSlider.OnItemClickListener onItemClickListener;

        private interface OnItemClickListener {
            void onItemClick(View view, DashboardModel obj);
        }

        public void setOnItemClickListener(AdapterImageSlider.OnItemClickListener onItemClickListener) {
            this.onItemClickListener = onItemClickListener;
        }

        // constructor
        private AdapterImageSlider(Activity activity, List<DashboardModel> items) {
            this.act = activity;
            this.items = items;
        }

        @Override
        public int getCount() {
            return this.items.size();
        }

        public DashboardModel getItem(int pos) {
            return items.get(pos);
        }

        public void setItems(List<DashboardModel> items) {
            this.items = items;
            notifyDataSetChanged();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == ((RelativeLayout) object);
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            final DashboardModel o = items.get(position);
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


    private String validasi() {
        String msg = null;
        if (etPhone.getText().toString().equals("")) {
            msg = "Isikan Nomor Handphone Anda";
        }
        return msg;
    }


    private void reqLogin(String postUrl, String phonenumber) throws IOException {
        JSONObject jsonReq = new JSONObject();
        try {
            jsonReq.put("phonenumber",phonenumber);
        } catch (JSONException je) {
            je.printStackTrace();
        }

        OkHttpClient client = new OkHttpClient();
        RequestBody body = RequestBody.create(JSON, jsonReq.toString());//postBody.toString());
        Request request = new Request.Builder().url(postUrl).post(body).build();
            client.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    call.cancel();
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    if (response.isSuccessful()) {
                        try {
                            String strJson = response.body().string();
                            JSONObject json = new JSONObject(strJson);
                            String code = json.getString("code");
                            String msg = json.getString("message");
                            if (code.equals("200")) {
                                LoginActivity.this.runOnUiThread(new Runnable() {
                                    public void run() {
                                        DialogHelper.onClickedErrorDialog(LoginActivity.this, "Berhasil Login");
                                        Intent intent = new Intent(mContext,MainActivity.class);
                                        intent.putExtra("phonenumber", phonenumber);
                                        startActivity(intent);
                                    }
                                });
                            } else {
                                LoginActivity.this.runOnUiThread(new Runnable() {
                                    public void run() {
                                        DialogHelper.onClickedErrorDialog(LoginActivity.this, "PASSWORD ANDA SALAH");
                                    }
                                });
                            }
                        } catch (JSONException je) {
                            je.printStackTrace();
                        }
                    }

                }
            });
    }
}



