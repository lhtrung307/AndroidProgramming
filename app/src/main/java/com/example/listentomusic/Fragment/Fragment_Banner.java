package com.example.listentomusic.Fragment;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.listentomusic.Adapter.BannerAdapter;
import com.example.listentomusic.Model.Quangcao;
import com.example.listentomusic.R;
import com.example.listentomusic.Service.APIService;
import com.example.listentomusic.Service.DataService;

import java.util.ArrayList;
import java.util.List;

import me.relex.circleindicator.CircleIndicator;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Fragment_Banner extends Fragment {
    View view;
    ViewPager viewPager;
    CircleIndicator circleIndicator;
    BannerAdapter bannerAdapter;
    Runnable runnable;
    Handler handler;
    int currentItem;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_banner,container,false);
        anhXa();
        GetData();
        return view;
    }

    private void anhXa() {
        viewPager = view.findViewById(R.id.viewpager);
        circleIndicator = view.findViewById(R.id.indicatordefault);
    }

    private void GetData() {
        DataService dataService = APIService.getService();
        Call<List<Quangcao>> callback = dataService.GetDataBanner();
        ((retrofit2.Call) callback).enqueue(new Callback() {
            @Override
            public void onResponse(retrofit2.Call call, Response response) {
                ArrayList<Quangcao> banners = (ArrayList<Quangcao>) response.body();
                bannerAdapter = new BannerAdapter(getActivity(),banners);
                viewPager.setAdapter(bannerAdapter);
                circleIndicator.setViewPager(viewPager);
                handler = new Handler();
                runnable = new Runnable() {
                    @Override
                    public void run() {
                        currentItem = viewPager.getCurrentItem();
                        currentItem++;
                        if(currentItem >= viewPager.getAdapter().getCount()){
                            currentItem = 0;

                        }
                        viewPager.setCurrentItem(currentItem, true);
                        handler.postDelayed(runnable, 4500);

                    }
                };
                handler.postDelayed(runnable,4500);

            }

            @Override
            public void onFailure(retrofit2.Call call, Throwable t) {

            }
        });

    }
}
