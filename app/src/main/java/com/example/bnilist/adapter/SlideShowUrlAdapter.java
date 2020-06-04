package com.example.bnilist.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityOptionsCompat;
import androidx.core.view.ViewCompat;
import androidx.viewpager.widget.PagerAdapter;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.bnilist.R;
import com.example.bnilist.activity.DetailActivity;
import com.example.bnilist.activity.DetailsImageActivity;
import com.google.android.material.snackbar.Snackbar;

public class SlideShowUrlAdapter extends PagerAdapter {
    private Context context;
    private LayoutInflater inflater;
    public String[] images;

    public SlideShowUrlAdapter(Context context, String[] images) {
        this.context = context;
        this.images = images;
    }

    @Override
    public int getCount() {
        return images.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return (view==(LinearLayout)object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, final int position) {
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.login_slider_image, container, false);
        ImageView img = view.findViewById(R.id.imageview_ID);
//        RequestOptions requestOptions = new RequestOptions();
//        requestOptions.placeholder(R.mipmap.ic_launcher);
//        requestOptions.error(R.mipmap.ic_launcher);
        //img.setImageResource(images[position]);
        Glide.with(context).load(images[position]).into(img);
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation((Activity) context, img, "imageShare");
                Intent intent = new Intent(context, DetailsImageActivity.class);
                intent.putExtra("urlimage", images[position]);
                context.startActivity(intent, options.toBundle());

//                Snackbar.make(view, "Image" + images[position] + (position + 1), Snackbar.LENGTH_LONG).show();
            }
        });
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((LinearLayout)object);
    }
}
