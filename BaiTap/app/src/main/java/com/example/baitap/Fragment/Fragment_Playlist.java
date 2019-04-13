package com.example.baitap.Fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.example.baitap.R;

public class Fragment_Playlist extends Fragment {
    View view;
    ListView lvPlaylist;
    TextView txtTitle,txtXemthem;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_playlist, container, false);
        anhxa();
        return view;
    }

    private void anhxa(){
        lvPlaylist = view.findViewById(R.id.lvPlaylist);
        txtTitle=view.findViewById(R.id.txtTitlePlaylist);
        txtXemthem  = view.findViewById(R.id.txtXemthem);
    }
}
