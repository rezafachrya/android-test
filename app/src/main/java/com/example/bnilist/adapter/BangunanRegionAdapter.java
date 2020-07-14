package com.example.bnilist.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bnilist.ItemClickListener;
import com.example.bnilist.R;
import com.example.bnilist.activity.BangunanActivity;
import com.example.bnilist.model.RegionModel;

import java.util.ArrayList;

public class BangunanRegionAdapter extends RecyclerView.Adapter<BangunanRegionAdapter.RegionHolder> {

    private Context context;
    public ArrayList<RegionModel> data;
    private String phonenumber;

    public BangunanRegionAdapter(Context context, ArrayList<RegionModel> data, String phonenumber) {
        super();
        this.context = context;
        this.data = data;
        this.phonenumber = phonenumber;
    }

    @NonNull
    @Override
    public RegionHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.activity_wilayah_item,parent,false);
        BangunanRegionAdapter.RegionHolder holder = new BangunanRegionAdapter.RegionHolder(itemView);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RegionHolder holder, int position) {
        //BIND DATA
        holder.tvCode.setText(data.get(position).getCode());
        Animation animation = AnimationUtils.loadAnimation(context, android.R.anim.slide_in_left);
        holder.itemView.startAnimation(animation);

        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onItemClick(View v, int pos) {
//                Toast.makeText(context, data.get(pos).getId(),Toast.LENGTH_LONG).show();
                Intent i = new Intent(context, BangunanActivity.class);

                //Add data
                i.putExtra("id",data.get(pos).getId());
                i.putExtra("phonenumber", phonenumber);
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }


    class RegionHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView tvCode;
        ItemClickListener itemClickListener;

        public RegionHolder(View itemView) {
            super(itemView);

            tvCode = itemView.findViewById(R.id.tvCode);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            this.itemClickListener.onItemClick(v,getLayoutPosition());
        }
        public void setItemClickListener(ItemClickListener ic)
        {
            this.itemClickListener=ic;
        }
    }
}
