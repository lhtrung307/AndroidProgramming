package com.example.listentomusic.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.listentomusic.Adapter.PlaylistsAdapter;
import com.example.listentomusic.Model.Playlist;
import com.example.listentomusic.R;
import com.example.listentomusic.Service.APIService;
import com.example.listentomusic.Service.DataService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AllPlaylistsActivity extends AppCompatActivity {
Toolbar toolbar;
RecyclerView recyclerViewdanhsachcacplaylist;
PlaylistsAdapter playlistsAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danhsachcacplaylist);
        anhxa();
        init();
        getData();
    }

    private void getData() {
        DataService dataService = APIService.getService();
        Call<List<Playlist>> callback = dataService.GetDanhsachCacPlaylist();
        callback.enqueue(new Callback<List<Playlist>>() {
            @Override
            public void onResponse(Call<List<Playlist>> call, Response<List<Playlist>> response) {
                ArrayList<Playlist> mangplaylist = (ArrayList<Playlist>) response.body();
                playlistsAdapter = new PlaylistsAdapter(AllPlaylistsActivity.this, mangplaylist);
                recyclerViewdanhsachcacplaylist.setLayoutManager(new GridLayoutManager(AllPlaylistsActivity.this, 2));
                recyclerViewdanhsachcacplaylist.setAdapter(playlistsAdapter);

            }

            @Override
            public void onFailure(Call<List<Playlist>> call, Throwable t) {

            }
        });
    }


    private void init() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Play Lists");
        toolbar.setTitleTextColor(getResources().getColor(R.color.mautim));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    private void anhxa() {
        toolbar = findViewById(R.id.toolbardanhsachcacplaylist);
        recyclerViewdanhsachcacplaylist = findViewById(R.id.recyclerviewdanhsachcacplaylist);
    }
}
