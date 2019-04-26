package com.example.listentomusic.Activity;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.listentomusic.Adapter.MainViewPagerAdapter;
import com.example.listentomusic.Fragment.Fragment_Banner;
import com.example.listentomusic.Fragment.Fragment_ChuDe_TheLoai_ToDay;
import com.example.listentomusic.Fragment.Fragment_Playlist;
import com.example.listentomusic.Fragment.Fragment_Tim_Kiem;
import com.example.listentomusic.Fragment.Fragment_Trang_Chu;
import com.example.listentomusic.R;

public class MainActivity extends AppCompatActivity {

    TabLayout tabLayout;
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        anhXa();
        init();

    }

    private void init() {
        MainViewPagerAdapter mainViewPagerAdapter = new MainViewPagerAdapter(getSupportFragmentManager());
        mainViewPagerAdapter.addFragment(new Fragment_Trang_Chu(),"Trang chu");
        mainViewPagerAdapter.addFragment(new Fragment_Tim_Kiem(),"Tim kiem");
        viewPager.setAdapter(mainViewPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.getTabAt(0).setIcon(R.drawable.icontrangchu);
        tabLayout.getTabAt(1).setIcon(R.drawable.icontimkiem);

    }

    private void anhXa(){
        tabLayout = findViewById(R.id.myTabLayout);
        viewPager = findViewById(R.id.myViewPager);
    }
}
