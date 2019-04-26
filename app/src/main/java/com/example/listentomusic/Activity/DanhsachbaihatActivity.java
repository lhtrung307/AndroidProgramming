package com.example.listentomusic.Activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.listentomusic.Adapter.DanhsachbaihatAdapter;
import com.example.listentomusic.Model.BaiHat;
import com.example.listentomusic.Model.Playlist;
import com.example.listentomusic.Model.Quangcao;
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

public class DanhsachbaihatActivity extends AppCompatActivity {
    CoordinatorLayout coordinatorLayout;
    CollapsingToolbarLayout collapsingToolbarLayout;
    Toolbar toolbar;
    RecyclerView recyclerViewDanhSachBaiHat;
    FloatingActionButton floatingActionButton;
    ImageView imageView;
    Quangcao quangcao;
    ArrayList<BaiHat> mangbaihat;
    DanhsachbaihatAdapter danhsachbaihatAdapter;
    Playlist playlist;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danhsachbaihat);
        DataIntent();
        anhxa();
        init();
        if(quangcao !=null && !quangcao.getTenBaiHat().equals("")){
            setValueInVew(quangcao.getTenBaiHat(), quangcao.getHinhBaiHat());
            GetDataquangcao(quangcao.getIdQuangCao());
        }
        if(playlist !=null && !playlist.getTen().equals("")){
            setValueInVew(playlist.getTen(),playlist.getHinhPlaylist());
            GetDataplaylist(playlist.getIdPlaylist());
        }
    }

    private void GetDataplaylist(String idplaylist) {
        DataService dataService = APIService.getService();
        Call<List<BaiHat>> callback = dataService.GetDanhsachbaitheoplaylist(idplaylist);
        callback.enqueue(new Callback<List<BaiHat>>() {
            @Override
            public void onResponse(Call<List<BaiHat>> call, Response<List<BaiHat>> response) {
                mangbaihat = (ArrayList<BaiHat>) response.body();
                danhsachbaihatAdapter = new DanhsachbaihatAdapter(DanhsachbaihatActivity.this, mangbaihat);
                recyclerViewDanhSachBaiHat.setLayoutManager(new LinearLayoutManager(DanhsachbaihatActivity.this));
                recyclerViewDanhSachBaiHat.setAdapter(danhsachbaihatAdapter);
            }

            @Override
            public void onFailure(Call<List<BaiHat>> call, Throwable t) {

            }
        });
    }

    private void GetDataquangcao(String idquangcao) {
        DataService dataService = APIService.getService();
        Call<List<BaiHat>> callback = dataService.Getdanhsachbaihattheoquangcao(idquangcao);
        callback.enqueue(new Callback<List<BaiHat>>() {
            @Override
            public void onResponse(Call<List<BaiHat>> call, Response<List<BaiHat>> response) {
                 mangbaihat = (ArrayList<BaiHat>) response.body();
                danhsachbaihatAdapter = new DanhsachbaihatAdapter(DanhsachbaihatActivity.this,mangbaihat);
                recyclerViewDanhSachBaiHat.setLayoutManager(new LinearLayoutManager(DanhsachbaihatActivity.this));
                recyclerViewDanhSachBaiHat.setAdapter(danhsachbaihatAdapter);

            }

            @Override
            public void onFailure(Call<List<BaiHat>> call, Throwable t) {

            }
        });

    }

    private void setValueInVew(String ten, String hinh) {
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

    }

    private void anhxa() {
        coordinatorLayout = findViewById(R.id.coordinatorlayout);
        collapsingToolbarLayout = findViewById(R.id.collapsingtoolbar);
        toolbar = findViewById(R.id.toolbardanhsach);
        recyclerViewDanhSachBaiHat = findViewById(R.id.recyclerviewdanhsachbaihat);
        floatingActionButton = findViewById(R.id.floatingactionbutton);
        imageView = findViewById(R.id.imageviewdanhsachcakhuc);
    }

    private void DataIntent() {
        Intent intent = getIntent();
        if(intent.hasExtra("banner")){
                quangcao= (Quangcao) intent.getSerializableExtra("banner");

        }
        if (intent.hasExtra("itemplaylist")){
            playlist = (Playlist) intent.getSerializableExtra("itemplaylist");

        }

    }
}
