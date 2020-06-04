package com.example.bnilist.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.bnilist.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailsImageActivity extends AppCompatActivity {
    @BindView(R.id.ivImage)
    ImageView ivImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_image);
        ButterKnife.bind(this);

        initComponent();
    }

    protected void initComponent() {
        String urlimage = getIntent().getStringExtra("urlimage");

        Glide.with(this).load(urlimage).into(ivImage);
    }
}
