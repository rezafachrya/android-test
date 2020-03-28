package com.example.bnilist.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.bnilist.ItemClickListener;
import com.example.bnilist.R;
import com.example.bnilist.model.RegionModel;

import java.util.ArrayList;

public class RegionAdapter extends RecyclerView.Adapter<RegionAdapter.RegionHolder> {

    Context context;
    public ArrayList<RegionModel> data;
    private ItemClickListener mClickListener;

    public RegionAdapter(Context context, ArrayList<RegionModel> data){
        super();
        this.context = context;
        this.data= data;
    }
    @Override
    public RegionHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.activity_wilayah_item_kantor,parent,false);
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

        //IMPLEMENT CLICK LISTENER
        /*
        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onItemClick(View v, int pos) {

                INTENT OBJ
                Intent i=new Intent(ctx, DetailActivity.class);

                ADD DATA TO OUR INTENT
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
         */
    }
    //GET TOTAL NUM OF data
    @Override
    public int getItemCount() {
        return data.size();
    }

    class RegionHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView tvCode;

        public RegionHolder(View itemView) {
            super(itemView);

            tvCode = itemView.findViewById(R.id.tvCode);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());
        }
    }

    // allows clicks events to be caught
    void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    // parent activity will implement this method to respond to click events
    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }

}
