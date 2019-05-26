package com.example.listentomusic.Fragment;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.listentomusic.Activity.LoginActivity;
import com.example.listentomusic.Activity.MainActivity;
import com.example.listentomusic.Adapter.BaihathotAdapter;
import com.example.listentomusic.Model.Song;
import com.example.listentomusic.R;
import com.example.listentomusic.Service.APIService;
import com.example.listentomusic.Service.DataService;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Fragment_Ca_Nhan extends Fragment {
    View view;
    Button btnLogin;
    LinearLayout lnProfile;
    TextView textView;
    TextView tvUsername;
    TextView tvDangXuat;
    Dialog dialogDangXuat;
    RecyclerView baiHatYeuThich;
    BaihathotAdapter baiHatYeuThichAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_ca_nhan, container, false);
        btnLogin = view.findViewById(R.id.btnDangNhap);
        lnProfile = view.findViewById(R.id.ln_profile);
        textView = view.findViewById(R.id.tv_title);
        tvUsername = view.findViewById(R.id.textviewUsername);
        tvDangXuat = view.findViewById(R.id.btnDangXuat);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), LoginActivity.class));
            }
        });
        tvDangXuat.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View v) {
                dialogDangXuat = new Dialog(Objects.requireNonNull(getContext()));
                dialogDangXuat.setContentView(R.layout.dialog_dangxuat);
                Objects.requireNonNull(dialogDangXuat.getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                Button btnOk = dialogDangXuat.findViewById(R.id.bt_add);
                Button btnCancel = dialogDangXuat.findViewById(R.id.bt_cc);
                dialogDangXuat.show();
                btnCancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialogDangXuat.dismiss();
                    }
                });
                btnOk.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        MainActivity.username = null;
                        onResume();
                        dialogDangXuat.dismiss();

                    }
                });
            }
        });

        return view;
    }


    @Override
    public void onResume() {

        super.onResume();
        if (!(MainActivity.username == null) && !MainActivity.username.equals("")) {
            btnLogin.setVisibility(View.GONE);
            textView.setVisibility(View.GONE);
            tvUsername.setText(MainActivity.username);
            lnProfile.setVisibility(View.VISIBLE);
            baiHatYeuThich = view.findViewById(R.id.baiHatYeuThich);
            baiHatYeuThich.setVisibility(View.VISIBLE);
            DataService dataService = APIService.getService();
            Call<List<Song>> callback = dataService.GetListSongLikedByUser(MainActivity.username);
            callback.enqueue(new Callback<List<Song>>() {
                @Override
                public void onResponse(Call<List<Song>> call, Response<List<Song>> response) {
                    ArrayList<Song> likedSongs = (ArrayList<Song>) response.body();
                    Log.d("CCC", "onResponse: " + likedSongs.get(0).getTenbaihat());
                    if(likedSongs.size() > 0){
                        baiHatYeuThichAdapter = new BaihathotAdapter(getActivity(), likedSongs);
                        baiHatYeuThich.setLayoutManager(new LinearLayoutManager(getActivity()));
                        baiHatYeuThich.setAdapter(baiHatYeuThichAdapter);
                    }
                }

                @Override
                public void onFailure(Call<List<Song>> call, Throwable t) {

                }
            });


        } else {
            btnLogin.setVisibility(View.VISIBLE);
            textView.setVisibility(View.VISIBLE);
            lnProfile.setVisibility(View.GONE);
        }
    }
}
