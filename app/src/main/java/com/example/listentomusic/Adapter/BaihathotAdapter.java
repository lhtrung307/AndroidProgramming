package com.example.listentomusic.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.listentomusic.Model.BaiHat;
import com.example.listentomusic.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class BaihathotAdapter extends RecyclerView.Adapter<BaihathotAdapter.ViewHolder>  {
    Context context;
    ArrayList<BaiHat> baiHats;

    public BaihathotAdapter(Context context, ArrayList<BaiHat> baiHats) {
        this.context = context;
        this.baiHats = baiHats;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view=layoutInflater.inflate(R.layout.dong_bai_hat_hot,viewGroup,false);


        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
            BaiHat baiHat=baiHats.get(i);
            viewHolder.txtcasi.setText(baiHat.getCasi());
            viewHolder.txtten.setText(baiHat.getTenbaihat());
            Picasso.with(context).load(baiHat.getHinhbaihat()).into(viewHolder.imghinh);

    }

    @Override
    public int getItemCount() {
        return baiHats.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView txtten, txtcasi;
        ImageView imghinh, imgluotthich;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtten = itemView.findViewById(R.id.textviewtenbaihathot);
            txtcasi = itemView.findViewById(R.id.textviewcasibaihathot);
            imghinh = itemView.findViewById(R.id.imageviewbaihathot);
            imgluotthich =itemView.findViewById(R.id.imageviewluotthich);

        }
    }
}
