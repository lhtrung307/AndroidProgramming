package com.example.listentomusic.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.listentomusic.Model.Song;
import com.example.listentomusic.R;

import java.util.ArrayList;

public class LoiBaiHatAdapter extends RecyclerView.Adapter<LoiBaiHatAdapter.ViewHolder> {

    Context context;
    ArrayList<Song> songs;

    public LoiBaiHatAdapter(Context context, ArrayList<Song> songs) {
        this.context = context;
        this.songs = songs;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.dong_loi_bai_hat, viewGroup, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Song song = songs.get(i);
        viewHolder.loiBaiHat.setText(song.getCasi());
    }

    @Override
    public int getItemCount() {
        return songs.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView loiBaiHat;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            loiBaiHat = itemView.findViewById(R.id.loiBaiHat);
            loiBaiHat.setMovementMethod(new ScrollingMovementMethod());
        }
    }
}
