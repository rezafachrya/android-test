package com.example.bnilist;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bnilist.activity.InfoActivity;
import com.example.bnilist.activity.LoginActivity;
import com.example.bnilist.activity.WilayahTanahActivity;
import com.example.bnilist.activity.WilayahBangunanActivity;
import com.example.bnilist.utils.SharedPrefManager;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.kantorButton)
    RelativeLayout kantorButton;
    @BindView(R.id.bangunanButton)
    RelativeLayout bangunanButton;
    @BindView(R.id.infoButton)
    RelativeLayout infoButton;
    @BindView(R.id.tvName)
    TextView tvName;
    @BindView(R.id.logoutButton)
    Button logoutButton;
//    @BindView(R.id.bottomNavigation)
//    BottomNavigationView bottomNavigationView;

    boolean doubleBackToExitPressedOnce = false;
    SharedPrefManager sharedPrefManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        sharedPrefManager = new SharedPrefManager(this);

        initComponent();
    }

    protected void initComponent() {
        String username = getIntent().getStringExtra("username");
        String phonenumber = getIntent().getStringExtra("phonenumber");

        tvName.setText(username);

        kantorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent kantorActivityIntent = new Intent(getApplicationContext(), WilayahTanahActivity.class);
                kantorActivityIntent.putExtra("phonenumber", phonenumber);
                startActivity(kantorActivityIntent);
            }
        });

        bangunanButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent bangunanActivityIntent = new Intent(getApplicationContext(), WilayahBangunanActivity.class);
                bangunanActivityIntent.putExtra("phonenumber", phonenumber);
                startActivity(bangunanActivityIntent);
            }
        });

        infoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent infoActivityIntent = new Intent(getApplicationContext(), InfoActivity.class);
                startActivity(infoActivityIntent);
            }
        });

        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logout(MainActivity.this, "Apakah Anda Ingin Keluar ?");
            }
        });

//        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
//            @Override
//            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//                switch (item.getItemId()) {
//                    case R.id.home_menu:
//                        break;
//                    case R.id.bangunan_menu:
//                        Intent bangunanActivityIntent = new Intent(getApplicationContext(), WilayahBangunanActivity.class);
//                        bangunanActivityIntent.putExtra("phonenumber", phonenumber);
//                        startActivity(bangunanActivityIntent);
//                        break;
//                    case R.id.tanah_menu:
//                        Intent kantorActivityIntent = new Intent(getApplicationContext(), WilayahTanahActivity.class);
//                        kantorActivityIntent.putExtra("phonenumber", phonenumber);
//                        startActivity(kantorActivityIntent);
//                        break;
//                    case R.id.logout:
//                        sharedPrefManager.saveSPBoolean(SharedPrefManager.SP_HAS_LOGIN, false);
//                        startActivity(new Intent(MainActivity.this, LoginActivity.class)
//                                .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
//                        finish();
//                        break;
//                }
//                return true;
//            }
//        });
    }

    public void logout(Context context, String message) {
        new MaterialAlertDialogBuilder(context, R.style.ErrorDialogTheme_Center)
                .setTitle(message)
                .setIcon(R.drawable.ic_error_outline_white_24dp)
                .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        sharedPrefManager.saveSPBoolean(SharedPrefManager.SP_HAS_LOGIN, false);
                        startActivity(new Intent(MainActivity.this, LoginActivity.class)
                                .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
                        finish();
                    }
                })
                .setNegativeButton("Tidak", null)
                .setCancelable(false)
                .show();
    }

    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_HOME);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Tekan lagi untuk keluar", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce = false;
            }
        }, 2000);
    }
}
