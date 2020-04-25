package com.example.bnilist.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.example.bnilist.ItemClickListener;
import com.example.bnilist.R;
import com.example.bnilist.activity.KantorActivity;
import com.example.bnilist.model.RegionModel;

import java.util.ArrayList;

public class RegionAdapter extends RecyclerView.Adapter<RegionAdapter.RegionHolder> {

    Context context;
    public ArrayList<RegionModel> data;

    public RegionAdapter(Context context, ArrayList<RegionModel> data){
        super();
        this.context = context;
        this.data= data;
    }
    @Override
    public RegionHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.activity_wilayah_item,parent,false);
        RegionHolder holder = new RegionHolder(itemView);
        return holder;
    }

    //DATA BOUND TO VIEWS
    @Override
    public void onBindViewHolder(RegionHolder holder, int position) {
        //BIND DATA
        holder.tvCode.setText(data.get(position).getCode());
        Animation animation = AnimationUtils.loadAnimation(context, android.R.anim.slide_in_left);
        holder.itemView.startAnimation(animation);

        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onItemClick(View v, int pos) {
//                Toast.makeText(context, data.get(pos).getId(),Toast.LENGTH_LONG).show();
                Intent i = new Intent(context, KantorActivity.class);

                //Add data
                i.putExtra("id",data.get(pos).getId());
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(i);
            }
        });
    }
    //GET TOTAL NUM OF data
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
