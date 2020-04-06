package com.example.bnilist.holder;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.bnilist.ItemClickListener;
import com.example.bnilist.R;

import org.w3c.dom.Text;

public class AssetHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    public TextView tvName;
    ItemClickListener itemClickListener;

    public AssetHolder(View itemView) {
        super(itemView);
        this.tvName = (TextView) itemView.findViewById(R.id.tvName);


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
