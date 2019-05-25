package com.example.listentomusic.Fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.example.listentomusic.Activity.PlayNhacActivity;
import com.example.listentomusic.Adapter.LoiBaiHatAdapter;
import com.example.listentomusic.R;

public class Fragment_Loi_Bai_Hat extends Fragment {
    View view;
    RecyclerView recyclerViewLoiBaiHat;
    LoiBaiHatAdapter loiBaiHatAdapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_loi_bai_hat, container, false);
        recyclerViewLoiBaiHat = view.findViewById(R.id.recyclerviewLoiBaiHat);
        if (PlayNhacActivity.songs.size() > 0){
            loiBaiHatAdapter = new LoiBaiHatAdapter(getActivity(), PlayNhacActivity.songs);
            recyclerViewLoiBaiHat.setLayoutManager(new LinearLayoutManager(getActivity()));
            recyclerViewLoiBaiHat.setAdapter(loiBaiHatAdapter);
        }
        return view;
    }
}
