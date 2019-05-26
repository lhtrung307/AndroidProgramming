package com.example.listentomusic.Activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.os.StrictMode;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

import com.example.listentomusic.Adapter.BaihathotAdapter;
import com.example.listentomusic.Model.Album;
import com.example.listentomusic.Model.Genre;
import com.example.listentomusic.Model.Song;
import com.example.listentomusic.Model.Playlist;
import com.example.listentomusic.Model.Banner;
import com.example.listentomusic.R;
import com.example.listentomusic.Service.APIService;
import com.example.listentomusic.Service.DataService;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SongsActivity extends AppCompatActivity {
    CoordinatorLayout coordinatorLayout;
    CollapsingToolbarLayout collapsingToolbarLayout;
    Toolbar toolbar;
    RecyclerView recyclerViewSongs;
    FloatingActionButton floatingActionButton;
    ImageView imageView;
    Banner banner;
    ArrayList<Song> songs;
    BaihathotAdapter songsAdapter;
    Playlist playlist;
    Genre genre;
    Album album;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danhsachbaihat);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        DataIntent();
        mapping();
        init();
        if(banner !=null && !banner.getTenBaiHat().equals("")){
            setValueInView(banner.getTenBaiHat(), banner.getHinhBaiHat());
            getBannerData(banner.getIdQuangCao());
        }
        if(playlist !=null && !playlist.getTen().equals("")){
            setValueInView(playlist.getTen(),playlist.getHinhPlaylist());
            getPlaylistData(playlist.getIdPlaylist());
        }
        if(genre !=null && !genre.getTenTheloai().equals("")){
            setValueInView(genre.getTenTheloai(), genre.getHinhTheloai());
            getGenreData(genre.getIdTheloai());
        }
        if(album != null && !album.getTenAlbum().equals("")){
            setValueInView(album.getTenAlbum(), album.getHinhAlbum());
            GetDataAlbum(album.getIdAlbum());
        }
        if(songs != null){
            if(songs.size()>0)
            setValueInView(songs.get(0).getTenbaihat(),songs.get(0).getHinhbaihat());
            GetSongsData();
        }
    }
    private void GetSongsData(){
        songsAdapter = new BaihathotAdapter(SongsActivity.this, songs);
        recyclerViewSongs.setLayoutManager(new LinearLayoutManager(SongsActivity.this));
        recyclerViewSongs.setAdapter(songsAdapter);
        eventClick();
    }

    private void GetDataAlbum(String idAlbum) {
        DataService dataService = APIService.getService();
        Call<List<Song>> callback = dataService.GetDanhsachbaihattheoalbum(idAlbum);
        callback.enqueue(new Callback<List<Song>>() {
            @Override
            public void onResponse(Call<List<Song>> call, Response<List<Song>> response) {
                songs = (ArrayList<Song>) response.body();
                songsAdapter = new BaihathotAdapter(SongsActivity.this, songs);
                recyclerViewSongs.setLayoutManager(new LinearLayoutManager(SongsActivity.this));
                recyclerViewSongs.setAdapter(songsAdapter);
                eventClick();
            }

            @Override
            public void onFailure(Call<List<Song>> call, Throwable t) {

            }
        });
    }

    private void getGenreData(String idtheloai){
        DataService dataService = APIService.getService();
        Call<List<Song>> callback = dataService.GetDanhsachbaihattheotheloai(idtheloai);
        callback.enqueue(new Callback<List<Song>>() {
            @Override
            public void onResponse(Call<List<Song>> call, Response<List<Song>> response) {
                songs = (ArrayList<Song>) response.body();
                songsAdapter = new BaihathotAdapter(SongsActivity.this, songs);
                recyclerViewSongs.setLayoutManager(new LinearLayoutManager(SongsActivity.this));
                recyclerViewSongs.setAdapter(songsAdapter);
                eventClick();
            }

            @Override
            public void onFailure(Call<List<Song>> call, Throwable t) {

            }
        });
    }

    private void getPlaylistData(String idplaylist) {
        DataService dataService = APIService.getService();
        Call<List<Song>> callback = dataService.GetDanhsachbaitheoplaylist(idplaylist);
        callback.enqueue(new Callback<List<Song>>() {
            @Override
            public void onResponse(Call<List<Song>> call, Response<List<Song>> response) {
                songs = (ArrayList<Song>) response.body();
                songsAdapter = new BaihathotAdapter(SongsActivity.this, songs);
                recyclerViewSongs.setLayoutManager(new LinearLayoutManager(SongsActivity.this));
                recyclerViewSongs.setAdapter(songsAdapter);
                eventClick();
            }

            @Override
            public void onFailure(Call<List<Song>> call, Throwable t) {

            }
        });
    }

    private void getBannerData(String idquangcao) {
        DataService dataService = APIService.getService();
        Call<List<Song>> callback = dataService.Getdanhsachbaihattheoquangcao(idquangcao);
        callback.enqueue(new Callback<List<Song>>() {
            @Override
            public void onResponse(Call<List<Song>> call, Response<List<Song>> response) {
                 songs = (ArrayList<Song>) response.body();
                songsAdapter = new BaihathotAdapter(SongsActivity.this, songs);
                recyclerViewSongs.setLayoutManager(new LinearLayoutManager(SongsActivity.this));
                recyclerViewSongs.setAdapter(songsAdapter);
                eventClick();
            }

            @Override
            public void onFailure(Call<List<Song>> call, Throwable t) {

            }
        });

    }

    private void setValueInView(String ten, String hinh) {
        collapsingToolbarLayout.setTitle(ten);
        try {
            URL url =new URL(hinh);
            Bitmap bitmap = BitmapFactory.decodeStream(url.openConnection().getInputStream());
            BitmapDrawable bitmapDrawable = new BitmapDrawable(getResources(), bitmap);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                collapsingToolbarLayout.setBackground(bitmapDrawable);
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Picasso.with(this).load(hinh).into(imageView);
    }

    private void init() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        collapsingToolbarLayout.setExpandedTitleColor(Color.WHITE);
        collapsingToolbarLayout.setCollapsedTitleTextColor(Color.WHITE);
        floatingActionButton.setEnabled(false);
    }

    private void mapping() {
        coordinatorLayout = findViewById(R.id.coordinatorlayout);
        collapsingToolbarLayout = findViewById(R.id.collapsingtoolbar);
        toolbar = findViewById(R.id.toolbardanhsach);
        recyclerViewSongs = findViewById(R.id.recyclerviewdanhsachbaihat);
        floatingActionButton = findViewById(R.id.floatingactionbutton);
        imageView = findViewById(R.id.imageviewdanhsachcakhuc);
    }

    private void DataIntent() {
        Intent intent = getIntent();
        if(intent.hasExtra("banner")){
                banner = (Banner) intent.getSerializableExtra("banner");

        }
        if (intent.hasExtra("itemplaylist")){
            playlist = (Playlist) intent.getSerializableExtra("itemplaylist");

        }

        if(intent.hasExtra("idtheloai")){
            genre = (Genre) intent.getSerializableExtra("idtheloai");
        }
        if(intent.hasExtra("album")){
            album = (Album) intent.getSerializableExtra("album");

        }
        if(intent.hasExtra("local")){
            songs = (ArrayList<Song>) intent.getSerializableExtra("local");
        }

    }

    private void eventClick(){
floatingActionButton.setEnabled(true);
floatingActionButton.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Intent intent = new Intent(SongsActivity.this, PlayNhacActivity.class);
    intent.putExtra("cacbaihat", songs);
        startActivity(intent);

    }
});
    }
}
