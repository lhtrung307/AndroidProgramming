package com.example.listentomusic.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.listentomusic.Adapter.DanhsachtheloaitheochudeAdapter;
import com.example.listentomusic.Model.ChuDe;
import com.example.listentomusic.Model.TheLoai;
import com.example.listentomusic.R;
import com.example.listentomusic.Service.APIService;
import com.example.listentomusic.Service.DataService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DanhsachtheloaitheochudeActivity extends AppCompatActivity {

    ChuDe chuDe;
    RecyclerView recyclerViewtheloaitheochude;
    Toolbar toolbartheloaitheochude;
    DanhsachtheloaitheochudeAdapter danhsachtheloaitheochudeAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danhsachtheloaitheochude);
        GetIntent();
        init();
        GetData();
    }

    private void GetData() {
        DataService dataService = APIService.getService();
        Call<List<TheLoai>> callback = dataService.GetTheLoaitheochude(chuDe.getIdChuDe());
        callback.enqueue(new Callback<List<TheLoai>>() {
            @Override
            public void onResponse(Call<List<TheLoai>> call, Response<List<TheLoai>> response) {
                ArrayList<TheLoai> theLoais = (ArrayList<TheLoai>) response.body();
                Log.d("bbbbb", theLoais.get(0).getIdTheloai());
                danhsachtheloaitheochudeAdapter = new DanhsachtheloaitheochudeAdapter(DanhsachtheloaitheochudeActivity.this, theLoais);
                recyclerViewtheloaitheochude.setLayoutManager(new GridLayoutManager(DanhsachtheloaitheochudeActivity.this, 2));
                recyclerViewtheloaitheochude.setAdapter(danhsachtheloaitheochudeAdapter);
            }

            @Override
            public void onFailure(Call<List<TheLoai>> call, Throwable t) {
                Log.d("bbbbb", "Cant get data");
            }
        });
    }

    private void init() {
        recyclerViewtheloaitheochude = findViewById(R.id.recyclerviewtheloaitheochude);
        toolbartheloaitheochude = findViewById(R.id.toolbartheloaitheochude);
        setSupportActionBar(toolbartheloaitheochude);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(chuDe.getTen());
        toolbartheloaitheochude.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void GetIntent(){
        Intent intent = getIntent();
        if( intent.hasExtra("chude")){
            chuDe = (ChuDe) intent.getSerializableExtra("chude");
        }
    }
}
