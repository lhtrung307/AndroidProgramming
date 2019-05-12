package com.example.listentomusic.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.listentomusic.Activity.DanhsachtheloaitheochudeActivity;
import com.example.listentomusic.Model.Category;
import com.example.listentomusic.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class AllGenresAdapter extends RecyclerView.Adapter<AllGenresAdapter.ViewHolder>{
    Context context;
    ArrayList<Category> genres;

    public AllGenresAdapter(Context context, ArrayList<Category> genres) {
        this.context = context;
        this.genres = genres;
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
        Category category = genres.get(i);
        Picasso.with(context).load(category.getHinhChuDe()).into(viewHolder.genreImg);
    }

    @Override
    public int getItemCount() {
        return genres.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView genreImg;
        public ViewHolder(View itemView){
            super(itemView);
            genreImg = itemView.findViewById(R.id.imageviewdongcacchude);
            genreImg.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, DanhsachtheloaitheochudeActivity.class);
                    intent.putExtra("chude", genres.get(getPosition()));
                    context.startActivity(intent);
                }
            });
        }
    }
}
