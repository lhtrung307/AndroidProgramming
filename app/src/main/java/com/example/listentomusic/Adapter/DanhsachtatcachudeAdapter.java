package com.example.listentomusic.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.listentomusic.Activity.DanhsachtheloaitheochudeActivity;
import com.example.listentomusic.Model.ChuDe;
import com.example.listentomusic.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class DanhsachtatcachudeAdapter extends RecyclerView.Adapter<DanhsachtatcachudeAdapter.ViewHolder>{
    Context context;
    ArrayList<ChuDe> chuDes;

    public DanhsachtatcachudeAdapter(Context context, ArrayList<ChuDe> chuDes) {
        this.context = context;
        this.chuDes = chuDes;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.dong_cac_chu_de, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        ChuDe chuDe = chuDes.get(i);
        Picasso.with(context).load(chuDe.getHinhChuDe()).into(viewHolder.imgchude);
    }

    @Override
    public int getItemCount() {
        return chuDes.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imgchude;
        public ViewHolder(View itemView){
            super(itemView);
            imgchude = itemView.findViewById(R.id.imageviewdongcacchude);
            imgchude.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, DanhsachtheloaitheochudeActivity.class);
                    Log.d("Chudeeeeeeee", "onClick: " + chuDes.get(getLayoutPosition()).getIdChuDe());
                    intent.putExtra("chude", chuDes.get(getPosition()));
                    context.startActivity(intent);
                }
            });
        }
    }
}
