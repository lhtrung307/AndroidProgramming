package com.example.listentomusic.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.listentomusic.Model.BaiHat;
import com.example.listentomusic.R;

import java.util.ArrayList;

public class PlaynhacAdapter extends RecyclerView.Adapter<PlaynhacAdapter.ViewHolder>{

    Context context;
    ArrayList<BaiHat> mangbaihat;

    public PlaynhacAdapter(Context context, ArrayList<BaiHat> mangbaihat) {
        this.context = context;
        this.mangbaihat = mangbaihat;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.dong_play_bai_hat, viewGroup, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        BaiHat baiHat = mangbaihat.get(i);
        viewHolder.txtcasi.setText(baiHat.getCasi());
        viewHolder.txttenbaihat.setText(baiHat.getTenbaihat());
        viewHolder.txtindex.setText(i + 1 + "");
    }

    @Override
    public int getItemCount() {
        return mangbaihat.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView txtindex, txtcasi, txttenbaihat;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtcasi = itemView.findViewById(R.id.textviewplaynhactencasi);
            txtindex = itemView.findViewById(R.id.textviewplaynhacindex);
            txttenbaihat = itemView.findViewById(R.id.textviewplaynhactenbaihat);
        }
    }
}
