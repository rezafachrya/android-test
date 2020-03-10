package com.example.bnilist.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.bnilist.R;
import com.google.android.material.button.MaterialButton;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WilayahBangunanActivity extends AppCompatActivity {
    @BindView(R.id.toolBar)
    Toolbar toolBar;
    @BindView(R.id.buttonKp)
    MaterialButton buttonKp;
    @BindView(R.id.buttonWpad)
    MaterialButton buttonWpad;
//    @BindView(R.id.buttonWpd)
//    MaterialButton buttonWpd;
//    @BindView(R.id.buttonWpl)
//    MaterialButton buttonWpl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wilayah_bangunan);
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

        buttonKp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent KpIntentActivity = new Intent(getApplicationContext(), BangunanKpActivity.class);
                startActivity(KpIntentActivity);
            }
        });

        buttonWpad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent WpadIntentActivity = new Intent(getApplicationContext(), BangunanWpadActivity.class);
                startActivity(WpadIntentActivity);
            }
        });
    }
}
