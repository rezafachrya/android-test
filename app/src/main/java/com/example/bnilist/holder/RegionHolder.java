package com.example.bnilist.holder;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.bnilist.ItemClickListener;
import com.example.bnilist.R;

public class RegionHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    public TextView tvCode;
    ItemClickListener itemClickListener;


    //constructor
    public RegionHolder (View itemView) {
        super(itemView);

        this.tvCode = (TextView) itemView.findViewById(R.id.tvCode);

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
