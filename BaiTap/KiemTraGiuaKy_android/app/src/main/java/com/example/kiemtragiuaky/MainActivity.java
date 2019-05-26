package com.example.kiemtragiuaky;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.View;

import com.example.kiemtragiuaky.Activities.DiemActivity;
import com.example.kiemtragiuaky.Activities.KhoaActivity;
import com.example.kiemtragiuaky.Activities.MonhocActivity;
import com.example.kiemtragiuaky.Activities.SinhvienActivity;
import com.example.kiemtragiuaky.sqLite.DatabaseManager;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.cardKhoa)
    CardView cardKhoa;
    @BindView(R.id.cardSinhvien)
    CardView cardSinhvien;
    @BindView(R.id.cardDiem)
    CardView cardDiem;
    @BindView(R.id.cardMonHoc)
    CardView cardMonHoc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        DatabaseManager dbm = new DatabaseManager(this);
        Log.e("Result: ", dbm.getListKhoa().size() + "");
    }

    @OnClick({R.id.cardKhoa, R.id.cardSinhvien, R.id.cardDiem, R.id.cardMonHoc})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.cardKhoa:
                Intent intentKhoa = new Intent(this, KhoaActivity.class);
                startActivity(intentKhoa);
                break;
            case R.id.cardSinhvien:
                Intent intentSinhvien = new Intent(this, SinhvienActivity.class);
                startActivity(intentSinhvien);
                break;
            case R.id.cardDiem:
                Intent intentDiem = new Intent(this, DiemActivity.class);
                startActivity(intentDiem);
                break;
            case R.id.cardMonHoc:
                Intent intentMon = new Intent(this, MonhocActivity.class);
                startActivity(intentMon);
                break;
        }
    }
}
