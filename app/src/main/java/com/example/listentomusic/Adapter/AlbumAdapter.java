package com.example.listentomusic.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.listentomusic.Model.Album;
import com.example.listentomusic.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class AlbumAdapter extends RecyclerView.Adapter<AlbumAdapter.ViewHolder> {

    Context context;
    ArrayList<Album> albums;

    public AlbumAdapter(Context context, ArrayList<Album> albums) {
        this.context = context;
        this.albums = albums;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.dong_album,viewGroup, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Album album = albums.get(i);
        viewHolder.txtcasialbum.setText(album.getTenCaSiAlbum());
        viewHolder.txttenalbum.setText(album.getTenAlbum());
        Picasso.with(context).load(album.getHinhAlbum()).into(viewHolder.imghinhalbum);

    }

    @Override
    public int getItemCount() {
        return albums.size();
    }

    public  class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imghinhalbum;
        TextView txttenalbum, txtcasialbum;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imghinhalbum = itemView.findViewById(R.id.imageviewalbum);
            txttenalbum = itemView.findViewById(R.id.textviewtenalbum);
            txtcasialbum = itemView.findViewById(R.id.textviewtencasialbum);
        }
    }
}
