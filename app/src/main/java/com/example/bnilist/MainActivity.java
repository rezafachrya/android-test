package com.example.bnilist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import com.example.bnilist.activity.InfoActivity;
import com.example.bnilist.activity.WilayahActivity;
import com.example.bnilist.activity.WilayahBangunanActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.kantorButton)
    RelativeLayout kantorButton;
    @BindView(R.id.bangunanButton)
    RelativeLayout bangunanButton;
    @BindView(R.id.infoButton)
    RelativeLayout infoButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initComponent();
    }

    protected void initComponent() {

        String phonenumber = getIntent().getStringExtra("phonenumber");

        kantorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent kantorActivityIntent = new Intent(getApplicationContext(), WilayahActivity.class);
                kantorActivityIntent.putExtra("phonenumber", phonenumber);
                startActivity(kantorActivityIntent);
            }
        });

        bangunanButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent bangunanActivityIntent = new Intent(getApplicationContext(), WilayahBangunanActivity.class);
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
