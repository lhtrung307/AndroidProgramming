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
import android.widget.TextView;
import android.widget.Toast;

import com.example.listentomusic.Activity.MainActivity;
import com.example.listentomusic.Activity.PlayNhacActivity;
import com.example.listentomusic.Model.Song;
import com.example.listentomusic.R;
import com.example.listentomusic.Service.APIService;
import com.example.listentomusic.Service.DataService;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BaihathotAdapter extends RecyclerView.Adapter<BaihathotAdapter.ViewHolder>  {
    Context context;
    ArrayList<Song> songs;

    public BaihathotAdapter(Context context, ArrayList<Song> songs) {
        this.context = context;
        this.songs = songs;
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
            Song song = songs.get(i);
            viewHolder.artistName.setText(song.getCasi());
            viewHolder.songName.setText(song.getTenbaihat());
            Picasso.with(context).load(song.getHinhbaihat()).into(viewHolder.songImg);

    }

    @Override
    public int getItemCount() {
        return songs.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView songName, artistName;
        ImageView songImg, imgLike;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            songName = itemView.findViewById(R.id.textviewtenbaihathot);
            artistName = itemView.findViewById(R.id.textviewcasibaihathot);
            songImg = itemView.findViewById(R.id.imageviewbaihathot);
            imgLike =itemView.findViewById(R.id.imageviewluotthich);
            DataService dataService = APIService.getService();
            Call<List<Song>> callback = dataService.GetListSongLikedByUser(MainActivity.username);
            callback.enqueue(new Callback<List<Song>>() {
                @Override
                public void onResponse(Call<List<Song>> call, Response<List<Song>> response) {
                    ArrayList<Song> likedSong = (ArrayList<Song>) response.body();

                    for(Song song: likedSong){
                        Log.d("BBB", "onResponse: " + song.getTenbaihat() + " " + songs.get(getPosition()).getTenbaihat());
                        if(song.getIdbaihat().equals(songs.get(getPosition()).getIdbaihat())){
                            imgLike.setImageResource(R.drawable.iconloved);
                            imgLike.setEnabled(false);
                        }
                    }
                }

                @Override
                public void onFailure(Call<List<Song>> call, Throwable t) {

                }
            });


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
                    likeButtonOnClick(imgLike, songId, context);
                }
            });

        }
    }

    public static void likeButtonOnClick(ImageView imgLike, final String songId, final Context context){
        imgLike.setImageResource(R.drawable.iconloved);
        DataService dataService = APIService.getService();
        Call<String> callback = dataService.UpdateLuotThich("1", songId);
        callback.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                String ketqua = response.body();
                if (ketqua.equals("Success")){
                    Toast.makeText(context, "Đã Thích", Toast.LENGTH_SHORT).show();
                } else{
                    Toast.makeText(context, "Lỗi!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }
        });
        if( MainActivity.username != null){
            Call<String> callback1 = dataService.UserLikeBaiHat(MainActivity.username, songId);
            callback1.enqueue(new Callback<String>() {
                @Override
                public void onResponse(Call<String> call, Response<String> response) {
                    if(response.body().equals("Success")){
                        Toast.makeText(context, "Đã thêm vào bài hát yêu thích của bạn", Toast.LENGTH_SHORT).show();
                    } else{
                        Toast.makeText(context, "Thêm vào bài hát yêu thích không thành công", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<String> call, Throwable t) {

                }
            });
        }
        imgLike.setEnabled(false);
    }
}
