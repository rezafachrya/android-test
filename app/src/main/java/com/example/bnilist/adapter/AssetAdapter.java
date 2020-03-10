package com.example.bnilist.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import androidx.recyclerview.widget.RecyclerView;

import com.example.bnilist.ItemClickListener;
import com.example.bnilist.R;
import com.example.bnilist.activity.DetailActivity;
import com.example.bnilist.holder.AssetHolder;
import com.example.bnilist.model.AssetModel;

import java.util.ArrayList;

public class AssetAdapter extends RecyclerView.Adapter<AssetHolder> {
    Context ctx;
    public ArrayList<AssetModel> offices;

    public AssetAdapter(Context ctx, ArrayList<AssetModel> offices){
        this.ctx = ctx;
        this.offices=offices;
    }
    @Override
    public AssetHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_list_kp, null);
        AssetHolder holder = new AssetHolder(v);
        return holder;
    }

    //DATA BOUND TO VIEWS
    @Override
    public void onBindViewHolder(AssetHolder holder, int position) {
        //BIND DATA
        holder.tvName.setText(offices.get(position).getNama());


        Animation animation = AnimationUtils.loadAnimation(ctx, android.R.anim.slide_in_left);
        holder.itemView.startAnimation(animation);

        //IMPLEMENT CLICK LISTENER
        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onItemClick(View v, int pos) {

                //INTENT OBJ
                Intent i=new Intent(ctx, DetailActivity.class);

                //ADD DATA TO OUR INTENT
                i.putExtra("Nama",offices.get(pos).getNama());
                i.putExtra("Image",offices.get(pos).getImg());
                i.putExtra("Address",offices.get(pos).getAlamat());
                i.putExtra("Npa",offices.get(pos).getNpa());
                i.putExtra("Nib",offices.get(pos).getNib());
                i.putExtra("Kpa",offices.get(pos).getKpa());
                i.putExtra("Kelurahan",offices.get(pos).getKelurahan());
                i.putExtra("Kecamatan",offices.get(pos).getKecamatan());
                i.putExtra("Kota",offices.get(pos).getKota());
                i.putExtra("Provinsi",offices.get(pos).getProvinsi());
                i.putExtra("Kodepos",offices.get(pos).getKodepos());
                i.putExtra("Luastanah",offices.get(pos).getLuastanah());
                i.putExtra("Luasbangunan",offices.get(pos).getLuasbangunan());
                i.putExtra("Jumlahlantai",offices.get(pos).getJml_lantai());
                i.putExtra("Legalitas",offices.get(pos).getDoc_legal());
                i.putExtra("Noimb",offices.get(pos).getNo_imb());
                i.putExtra("Nopajak",offices.get(pos).getNop());
                i.putExtra("Latitude",offices.get(pos).getLatitude());
                i.putExtra("Longitude",offices.get(pos).getLongitude());

                //START DETAIL ACTIVITY
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                ctx.startActivity(i);

            }
        });
    }
    //GET TOTAL NUM OF Offices
    @Override
    public int getItemCount() {
        return offices.size();
    }



}
