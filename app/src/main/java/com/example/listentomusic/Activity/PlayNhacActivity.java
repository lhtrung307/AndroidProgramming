package com.example.listentomusic.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.listentomusic.Model.BaiHat;
import com.example.listentomusic.R;

import java.util.ArrayList;

public class PlayNhacActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_nhac);
        Intent intent = getIntent();
        if(intent.hasExtra("cakhuc")){
            BaiHat baihat = intent.getParcelableExtra("cakhuc");
            Toast.makeText(this, baihat.getTenbaihat(), Toast.LENGTH_SHORT).show();
        }
        if(intent.hasExtra("cacbaihat")){
            ArrayList<BaiHat> mangbaihat = intent.getParcelableArrayListExtra("cacbaihat");
            for(int i = 0; i < mangbaihat.size(); i++){
                Log.d("BBB", mangbaihat.get(i).getTenbaihat());
            }
        }
    }
}
