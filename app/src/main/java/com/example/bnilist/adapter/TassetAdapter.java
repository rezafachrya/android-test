package com.example.bnilist.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.bnilist.ItemClickListener;
import com.example.bnilist.R;
import com.example.bnilist.activity.DetailActivity;
import com.example.bnilist.model.TassetModel;

import java.util.ArrayList;

public class TassetAdapter extends RecyclerView.Adapter<TassetAdapter.TassetHolder> {
    Context context;
    public ArrayList<TassetModel> data;

    public TassetAdapter(Context context, ArrayList<TassetModel> data){
        super();
        this.context = context;
        this.data = data;
    }

    @Override
    public TassetAdapter.TassetHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.activity_list_item,parent,false);
        TassetAdapter.TassetHolder holder = new TassetAdapter.TassetHolder(itemView);
        return holder;
    }

    //DATA BOUND TO VIEWS
    @Override
    public void onBindViewHolder(TassetAdapter.TassetHolder holder, int position) {
        //BIND DATA
        holder.tvName.setText(data.get(position).getNama());
        Animation animation = AnimationUtils.loadAnimation(context, android.R.anim.slide_in_left);
        holder.itemView.startAnimation(animation);


        //IMPLEMENT CLICK LISTENER
        holder.setItemClickListener(new com.example.bnilist.ItemClickListener() {
            @Override
            public void onItemClick(View v, int pos) {
//                Toast.makeText(context, data.get(pos).getId(),Toast.LENGTH_LONG).show();
                Intent i = new Intent(context, DetailActivity.class);
                //Add data
                i.putExtra("data", data.get(pos));
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

    class TassetHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView tvName;
        ItemClickListener itemClickListener;

        public TassetHolder(View itemView){
            super(itemView);

            tvName = itemView.findViewById(R.id.tvName);
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
