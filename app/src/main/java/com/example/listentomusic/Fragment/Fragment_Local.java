package com.example.listentomusic.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.listentomusic.Activity.SongsActivity;
import com.example.listentomusic.Model.Song;
import com.example.listentomusic.R;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static android.media.CamcorderProfile.get;
class Mp3Fielter implements FilenameFilter {
    @Override
    public boolean accept(File dir, String name) {
        return (name.endsWith(".mp3"));
    }
}

public class Fragment_Local extends Fragment {
    final String MEDIA_PATH = Environment.getExternalStorageDirectory()
            .getPath() + "/Zing MP3/";
    ArrayList<String> listSongdemo = new ArrayList<>();
    View view;
    TextView textView;
    ArrayList<Song> songLocals;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_local, container,false);
        textView = view.findViewById(R.id.textviewlistlocal);
        songLocals = new ArrayList<>();
        upDatePlaylist();
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), SongsActivity.class);
                intent.putExtra("local", songLocals);
                startActivity(intent);

            }
        });
        return view;
    }
    private void upDatePlaylist(){
        int i=0;
        if(MEDIA_PATH != null){
            File home = new File(MEDIA_PATH);
            if(home.listFiles(new Mp3Fielter()).length > 0){
                for(File file: home.listFiles(new Mp3Fielter())){
                    listSongdemo.add(file.getName());
                    songLocals.add(new Song("",file.getName(),"https://static-zmp3.zadn.vn/skins/common/logo600.png","",MEDIA_PATH+""+file.getName(),""));
                    System.out.println("list file doc tu android ne: "+listSongdemo.get(i++));
                }
            }
        }


    }



}
