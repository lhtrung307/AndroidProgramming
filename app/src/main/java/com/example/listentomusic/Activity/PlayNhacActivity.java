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
import android.view.View;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.listentomusic.Adapter.ViewPagerPlaylistnhac;
import com.example.listentomusic.Fragment.Fragment_Dia_Nhac;
import com.example.listentomusic.Fragment.Fragment_Loi_Bai_Hat;
import com.example.listentomusic.Fragment.Fragment_Play_Danh_Sach_Cac_Bai_Hat;
import com.example.listentomusic.Model.Song;
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
    public static ArrayList<Song> songs = new ArrayList<>();
    public static ViewPagerPlaylistnhac adapternhac;
    Fragment_Dia_Nhac fragment_dia_nhac;
    Fragment_Play_Danh_Sach_Cac_Bai_Hat fragment_play_danh_sach_cac_bai_hat;
    Fragment_Loi_Bai_Hat fragment_loi_bai_hat;
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
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if(adapternhac.getItem(1) != null){
                    if(songs.size() > 0){
                        fragment_dia_nhac.Playnhac(songs.get(0).getHinhbaihat());
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
                if (songs.size() > 0) {
                    clearMediaPlayer();
                    next = true;
//                    if(position < songs.size()){
//                        imgplay.setImageResource(R.drawable.iconpause);
//                        position++;
//                        if (repeat){
//                            if (position == 0){
//                                position = songs.size();
//                            }
//                            position -= 1;
//                        }
//                        if (checkrandom) {
//                            getRandomSongIndex();
//                        }
//                        if (position > (songs.size() - 1)){
//                            position = 0;
//                        }

//                        new PlayMp3().execute(songs.get(position).getLinkbaihat());
//                        renderSongInfoToUI();
//                    }
                }
            }
        });
        imgpre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (songs.size() > 0) {
                    clearMediaPlayer();
                    if (position < songs.size()) {
                        imgplay.setImageResource(R.drawable.iconpause);
                        position--;

                        if (position < 0) {
                            position = songs.size() - 1;
                        }

                        if (repeat) {
                            position += 1;
                        }
                        if (checkrandom) {
                            getRandomSongIndex();
                        }
                        new PlayMp3().execute(songs.get(position).getLinkbaihat());
                        renderSongInfoToUI();
                    }
                }
                disableChangeSongIn(5000);
            }
        });
    }

    private void renderSongInfoToUI(){
        fragment_dia_nhac.Playnhac(songs.get(position).getHinhbaihat());
        getSupportActionBar().setTitle(songs.get(position).getTenbaihat());
    }

    private void disableChangeSongIn(int milisecs){
        imgpre.setClickable(false);
        imgnext.setClickable(false);
        Handler handler1 = new Handler();
        handler1.postDelayed(new Runnable() {
            @Override
            public void run() {
                imgpre.setClickable(true);
                imgnext.setClickable(true);
            }
        }, milisecs);
    }

    private void getRandomSongIndex(){
        Random random = new Random();
        int index = random.nextInt(songs.size());
        if (index == position) {
            position = index - 1;
        }
        position = index;
    }

    private void clearMediaPlayer(){
        if (mediaPlayer.isPlaying() || mediaPlayer != null){
            mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }

    private void GetDataFromIntent() {
        Intent intent = getIntent();
        songs.clear();
        if (intent != null){
            if(intent.hasExtra("cakhuc")){
                Song baihat = intent.getParcelableExtra("cakhuc");
                songs.add(baihat);
            }
            if(intent.hasExtra("cacbaihat")){
                ArrayList<Song> songs = intent.getParcelableArrayListExtra("cacbaihat");
                PlayNhacActivity.songs = songs;
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
                songs.clear();
            }
        });
        toolbarplaynhac.setTitleTextColor(Color.WHITE);
        fragment_dia_nhac = new Fragment_Dia_Nhac();
        fragment_play_danh_sach_cac_bai_hat = new Fragment_Play_Danh_Sach_Cac_Bai_Hat();
        fragment_loi_bai_hat = new Fragment_Loi_Bai_Hat();
        adapternhac = new ViewPagerPlaylistnhac(getSupportFragmentManager());
        adapternhac.AddFragment(fragment_play_danh_sach_cac_bai_hat);
        adapternhac.AddFragment(fragment_dia_nhac);
        adapternhac.AddFragment(fragment_loi_bai_hat);
        viewPagerplaynhac.setAdapter(adapternhac);
        fragment_dia_nhac = (Fragment_Dia_Nhac) adapternhac.getItem(1);
        if(songs.size() > 0){
            getSupportActionBar().setTitle(songs.get(0).getTenbaihat());
            new PlayMp3().execute(songs.get(0).getLinkbaihat());
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
                        next = true;
                        try {
                            Thread.sleep(1000);
                        }
                        catch (InterruptedException e){
                            e.printStackTrace();
                        }
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
                }
            }
        }, 300);
        final Handler handler1 = new Handler();
        handler1.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (next) {
                    if (position < songs.size()) {
                        imgplay.setImageResource(R.drawable.iconpause);
                        position--;

                        if (position < 0) {
                            position = songs.size() - 1;
                        }

                        if (repeat) {
                            position += 1;
                        }
                        if (checkrandom) {
                            getRandomSongIndex();
                        }
                        new PlayMp3().execute(songs.get(position).getLinkbaihat());
                        fragment_dia_nhac.Playnhac(songs.get(position).getHinhbaihat());
                        getSupportActionBar().setTitle(songs.get(position).getTenbaihat());
                    }
                disableChangeSongIn(5000);
                next = false;
                handler1.removeCallbacks(this);
                } else {
                    handler1.postDelayed(this, 1000);
                }
            }
        }, 1000);
    }
}
