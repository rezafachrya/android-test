package com.example.bnilist.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.example.bnilist.MainActivity;
import com.example.bnilist.R;
import com.example.bnilist.adapter.SlideShowAdapter;
import com.example.bnilist.helper.DialogHelper;
import com.example.bnilist.model.LoginModel;
import com.example.bnilist.model.UserModel;
import com.example.bnilist.utils.SharedPrefManager;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.relex.circleindicator.CircleIndicator;
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
    @BindView(R.id.pager)
    ViewPager viewPager;
    @BindView(R.id.etPhone)
    TextInputEditText etPhone;
    @BindView(R.id.outlinedTextField)
    TextInputLayout outlinedTextField;
    @BindView(R.id.circleIndicator_ID)
    CircleIndicator indicator3;
    private SlideShowAdapter adapter;
    private Handler handler;
    private Runnable runnable;
    private Timer timer;
    Context mContext;

    private ArrayList<UserModel> data = new ArrayList<>();
    SharedPrefManager sharedPrefManager;

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

        sharedPrefManager = new SharedPrefManager(this);

        runnable = () -> {
            int i = viewPager.getCurrentItem();
            if (i == adapter.images.length - 1) {
                i = 0;
                viewPager.setCurrentItem(i, true);
            } else {
                i++;
                viewPager.setCurrentItem(i, true);
            }
        };

        initComponent();

        if (sharedPrefManager.getSPHasLogin()) {
            String phoneNumber = sharedPrefManager.getSpHandphone();
            String username = sharedPrefManager.getSpUsername();
            Intent intent = new Intent(LoginActivity.this, MainActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.putExtra("phonenumber", phoneNumber);
            intent.putExtra("username", username);
            startActivity(intent);
            finish();
        }
    }

    private void initComponent() {
        mContext = this;

        int[] arrImages = {
                R.drawable.bni_bg1,
                R.drawable.bni_bg2,
                R.drawable.bni_bg3,
                R.drawable.bni_bg4
        };

        adapter = new SlideShowAdapter(this, arrImages);
        viewPager.setAdapter(adapter);
        indicator3.setViewPager(viewPager);
        handler = new Handler();

        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(runnable);
            }
        }, 4000, 4000);

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
            jsonReq.put("phonenumber", phonenumber);
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
                LoginActivity.this.runOnUiThread(new Runnable() {
                    public void run() {
                        Toast.makeText(mContext, "Koneksi Internet Bermasalah", Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    try {
                        final String strJson = response.body().string();
                        JSONObject json = new JSONObject(strJson);
                        String code = json.getString("code");
                        String msg = json.getString("message");
                        String username = json.getJSONObject("data").getString("username");
                        if (code.equals("200")) {
                            LoginActivity.this.runOnUiThread(new Runnable() {
                                public void run() {
                                    DialogHelper.onClickedSuccessDialog(LoginActivity.this, "Berhasil Login");
                                    sharedPrefManager.SaveSPString(SharedPrefManager.SP_HANDPHONE, phonenumber);
                                    sharedPrefManager.SaveSPString(SharedPrefManager.SP_USERNAME, username);
                                    sharedPrefManager.saveSPBoolean(SharedPrefManager.SP_HAS_LOGIN, true);
                                    Intent intent = new Intent(mContext, MainActivity.class);
                                    intent.putExtra("phonenumber", phonenumber);
                                    intent.putExtra("username", username);
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



