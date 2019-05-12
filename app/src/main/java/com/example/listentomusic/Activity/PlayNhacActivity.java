package com.example.listentomusic.Activity;

import android.content.Intent;
import android.graphics.Color;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.StrictMode;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.listentomusic.Adapter.ViewPagerPlaylistnhac;
import com.example.listentomusic.Fragment.Fragment_Dia_Nhac;
import com.example.listentomusic.Fragment.Fragment_Play_Danh_Sach_Cac_Bai_Hat;
import com.example.listentomusic.Model.BaiHat;
import com.example.listentomusic.R;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Random;

public class PlayNhacActivity extends AppCompatActivity {

    Toolbar toolbarplaynhac;
    TextView txtTimesong, txtTotalTimesong;
    SeekBar sktime;
    ImageButton imgplay, imgrepeat, imgnext, imgpre, imgrandom;
    ViewPager viewPagerplaynhac;
    public static ArrayList<BaiHat> mangBaiHat = new ArrayList<>();
    public static ViewPagerPlaylistnhac adapternhac;
    Fragment_Dia_Nhac fragment_dia_nhac;
    Fragment_Play_Danh_Sach_Cac_Bai_Hat fragment_play_danh_sach_cac_bai_hat;
    MediaPlayer mediaPlayer;
    int position = 0;
    boolean repeat = false;
    boolean checkrandom = false;
    boolean next = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_nhac);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        GetDataFromIntent();
        init();
        eventClick();
    }

    private void eventClick() {
        final Handler handler = new Handler();
        Log.d("BBBB", "eventClick: play nhac");
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if(adapternhac.getItem(1) != null){
                    if(mangBaiHat.size() > 0){
                        fragment_dia_nhac.Playnhac(mangBaiHat.get(0).getHinhbaihat());
                        handler.removeCallbacks(this);
                    }else {
                        handler.postDelayed(this, 300);
                    }
                }
            }
        }, 500);
        imgplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mediaPlayer.isPlaying()){
                    mediaPlayer.pause();
                    imgplay.setImageResource(R.drawable.iconplay);
                } else {
                    mediaPlayer.start();
                    imgplay.setImageResource(R.drawable.iconpause);
                }
            }
        });
        imgrepeat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!repeat){
                    if(checkrandom){
                        checkrandom = false;
                        imgrepeat.setImageResource(R.drawable.iconsyned);
                        imgrandom.setImageResource(R.drawable.iconsuffle);
                    }
                    imgrepeat.setImageResource(R.drawable.iconsyned);
                    repeat = true;
                } else {
                    imgrepeat.setImageResource(R.drawable.iconrepeat);
                    repeat = false;
                }
            }
        });
        imgrandom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!checkrandom){
                    if(repeat){
                        repeat = false;
                        imgrepeat.setImageResource(R.drawable.iconrepeat);
                        imgrandom.setImageResource(R.drawable.iconshuffled);
                    }
                    imgrandom.setImageResource(R.drawable.iconshuffled);
                    checkrandom = true;
                } else {
                    imgrandom.setImageResource(R.drawable.iconsuffle);
                    checkrandom = false;
                }
            }
        });
        sktime.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                mediaPlayer.seekTo(seekBar.getProgress());
            }
        });
        imgnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mangBaiHat.size() > 0) {
                    if (mediaPlayer.isPlaying() || mediaPlayer != null){
                        mediaPlayer.stop();
                        mediaPlayer.release();
                        mediaPlayer = null;
                    }
                    if(position < mangBaiHat.size()){
                        imgplay.setImageResource(R.drawable.iconpause);
                        position++;
                        if (repeat){
                            if (position == 0){
                                position = mangBaiHat.size();
                            }
                            position -= 1;
                        }
                        if (checkrandom){
                            Random random = new Random();
                            int index = random.nextInt(mangBaiHat.size());
                            if (index == position){
                                position = index - 1;
                            }
                            position = index;
                        }
                        if (position > (mangBaiHat.size() - 1)){
                            position = 0;
                        }
                        new PlayMp3().execute(mangBaiHat.get(position).getLinkbaihat());
                        fragment_dia_nhac.Playnhac(mangBaiHat.get(position).getHinhbaihat());
                        getSupportActionBar().setTitle(mangBaiHat.get(position).getTenbaihat());
                        UpdateTime();
                    }
                }
                imgpre.setClickable(false);
                imgnext.setClickable(false);
                Handler handler1 = new Handler();
                handler1.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        imgpre.setClickable(true);
                        imgnext.setClickable(true);
                    }
                }, 5000);

            }
        });
        imgpre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mangBaiHat.size() > 0) {
                    if (mediaPlayer.isPlaying() || mediaPlayer != null) {
                        mediaPlayer.stop();
                        mediaPlayer.release();
                        mediaPlayer = null;
                    }
                    if (position < mangBaiHat.size()) {
                        imgplay.setImageResource(R.drawable.iconpause);
                        position--;

                        if (position < 0) {
                            position = mangBaiHat.size() - 1;
                        }

                        if (repeat) {
                            position += 1;
                        }
                        if (checkrandom) {
                            Random random = new Random();
                            int index = random.nextInt(mangBaiHat.size());
                            if (index == position) {
                                position = index - 1;
                            }
                            position = index;
                        }
                        new PlayMp3().execute(mangBaiHat.get(position).getLinkbaihat());
                        fragment_dia_nhac.Playnhac(mangBaiHat.get(position).getHinhbaihat());
                        getSupportActionBar().setTitle(mangBaiHat.get(position).getTenbaihat());
                        UpdateTime();
                    }
                }
                imgpre.setClickable(false);
                imgnext.setClickable(false);
                Handler handler1 = new Handler();
                handler1.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        imgpre.setClickable(true);
                        imgnext.setClickable(true);
                    }
                }, 5000);
            }
        });
    }

    private void GetDataFromIntent() {
        Intent intent = getIntent();
        mangBaiHat.clear();
        if (intent != null){
            if(intent.hasExtra("cakhuc")){
                BaiHat baihat = intent.getParcelableExtra("cakhuc");
                mangBaiHat.add(baihat);
            }
            if(intent.hasExtra("cacbaihat")){
                ArrayList<BaiHat> baiHatArrayList = intent.getParcelableArrayListExtra("cacbaihat");
                mangBaiHat = baiHatArrayList;
            }
        }

    }

    private void init() {
        toolbarplaynhac = findViewById(R.id.toolbarplaynhac);
        txtTimesong = findViewById(R.id.textviewtimesong);
        txtTotalTimesong = findViewById(R.id.textviewtotaltimesong);
        sktime = findViewById(R.id.seekbarsong);
        imgplay = findViewById(R.id.imagebuttonplay);
        imgnext = findViewById(R.id.imagebuttonnext);
        imgpre = findViewById(R.id.imagebuttonpre);
        imgrandom = findViewById(R.id.imagebuttonshuffle);
        imgrepeat = findViewById(R.id.imagebuttonrepeat);
        viewPagerplaynhac = findViewById(R.id.viewpagerplaynhac);
        setSupportActionBar(toolbarplaynhac);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbarplaynhac.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                mediaPlayer.stop();
                mangBaiHat.clear();
            }
        });
        toolbarplaynhac.setTitleTextColor(Color.WHITE);
        fragment_dia_nhac = new Fragment_Dia_Nhac();
        fragment_play_danh_sach_cac_bai_hat = new Fragment_Play_Danh_Sach_Cac_Bai_Hat();
        adapternhac = new ViewPagerPlaylistnhac(getSupportFragmentManager());
        adapternhac.AddFragment(fragment_play_danh_sach_cac_bai_hat);
        adapternhac.AddFragment(fragment_dia_nhac);
        viewPagerplaynhac.setAdapter(adapternhac);
        fragment_dia_nhac = (Fragment_Dia_Nhac) adapternhac.getItem(1);
        if(mangBaiHat.size() > 0){
            getSupportActionBar().setTitle(mangBaiHat.get(0).getTenbaihat());
            new PlayMp3().execute(mangBaiHat.get(0).getLinkbaihat());
            imgplay.setImageResource(R.drawable.iconpause);
        }
    }

    class PlayMp3 extends AsyncTask<String, Void, String>{

        @Override
        protected String doInBackground(String... strings) {
            return strings[0];
        }

        @Override
        protected void onPostExecute(String baihat) {
            super.onPostExecute(baihat);
            try {
                mediaPlayer = new MediaPlayer();
                mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        mediaPlayer.stop();
                        mediaPlayer.reset();
                    }
                });
                mediaPlayer.setDataSource(baihat);
                mediaPlayer.prepare();
            } catch (IOException e) {
                e.printStackTrace();
            }
            mediaPlayer.start();
            TimeSong();
            UpdateTime();
        }
    }

    private void TimeSong() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("mm:ss");
        txtTotalTimesong.setText(simpleDateFormat.format(mediaPlayer.getDuration()));
        sktime.setMax(mediaPlayer.getDuration());
    }

    private void UpdateTime(){
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if(mediaPlayer != null){
                    sktime.setProgress(mediaPlayer.getCurrentPosition());
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("mm:ss");
                    txtTimesong.setText(simpleDateFormat.format(mediaPlayer.getCurrentPosition()));
                    handler.postDelayed(this, 300);
                    mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mp) {
                            next = true;
                            try {
                                Thread.sleep(1000);
                            }
                            catch (InterruptedException e){
                                e.printStackTrace();
                            }
                        }
                    });
                }
            }
        }, 300);
        final Handler handler1 = new Handler();
        handler1.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (next) {
                    if (position < mangBaiHat.size()) {
                        imgplay.setImageResource(R.drawable.iconpause);
                        position--;

                        if (position < 0) {
                            position = mangBaiHat.size() - 1;
                        }

                        if (repeat) {
                            position += 1;
                        }
                        if (checkrandom) {
                            Random random = new Random();
                            int index = random.nextInt(mangBaiHat.size());
                            if (index == position) {
                                position = index - 1;
                            }
                            position = index;
                        }
                        new PlayMp3().execute(mangBaiHat.get(position).getLinkbaihat());
                        fragment_dia_nhac.Playnhac(mangBaiHat.get(position).getHinhbaihat());
                        getSupportActionBar().setTitle(mangBaiHat.get(position).getTenbaihat());
                    }
                imgpre.setClickable(false);
                imgnext.setClickable(false);
                Handler handler2 = new Handler();
                handler2.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        imgpre.setClickable(true);
                        imgnext.setClickable(true);
                    }
                }, 5000);
                next = false;
                handler1.removeCallbacks(this);
                } else {
                    handler1.postDelayed(this, 1000);
                }
            }
        }, 1000);
    }
}
