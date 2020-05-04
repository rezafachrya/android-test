package com.example.bnilist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import com.example.bnilist.activity.InfoActivity;
import com.example.bnilist.activity.WilayahKantorActivity;
import com.example.bnilist.activity.WilayahBangunanActivity;
import com.example.bnilist.utils.SharedPrefManager;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.kantorButton)
    RelativeLayout kantorButton;
    @BindView(R.id.bangunanButton)
    RelativeLayout bangunanButton;
    @BindView(R.id.infoButton)
    RelativeLayout infoButton;

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

        String phonenumber = getIntent().getStringExtra("phonenumber");

        kantorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent kantorActivityIntent = new Intent(getApplicationContext(), WilayahKantorActivity.class);
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

    }
}
