package com.example.listentomusic.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.listentomusic.Activity.PlayNhacActivity;
import com.example.listentomusic.Model.Song;
import com.example.listentomusic.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class SearchBaiHatAdapter extends RecyclerView.Adapter<SearchBaiHatAdapter.ViewHolder> {

    Context context;
    ArrayList<Song> songs;

    public SearchBaiHatAdapter(Context context, ArrayList<Song> songs) {
        this.context = context;
        this.songs = songs;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.dong_search_bai_hat, viewGroup, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Song song = songs.get(i);
        viewHolder.txtTenBaiHat.setText(song.getTenbaihat());
        viewHolder.txtCaSi.setText(song.getCasi());
        Picasso.with(context).load(song.getHinhbaihat()).into(viewHolder.imgBaiHat);
    }

    @Override
    public int getItemCount() {
        return songs.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView txtTenBaiHat, txtCaSi;
        ImageView imgBaiHat, imgLike;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTenBaiHat = itemView.findViewById(R.id.textviewsearchtenbaihat);
            txtCaSi = itemView.findViewById(R.id.textviewsearchtencasi);
            imgBaiHat = itemView.findViewById(R.id.imageviewsearchbaihat);
            imgLike = itemView.findViewById(R.id.imageviewsearchluotthich);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, PlayNhacActivity.class);
                    intent.putExtra("cakhuc", songs.get(getPosition()));
                    context.startActivity(intent);
                }
            });
            imgLike.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String songId = songs.get(getPosition()).getIdbaihat();
//                    likeButtonOnClick(imgLike, songId, context);
                }
            });
        }
    }
}
