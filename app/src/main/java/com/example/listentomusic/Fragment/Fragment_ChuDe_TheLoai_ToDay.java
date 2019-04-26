package com.example.listentomusic.Fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.listentomusic.Model.ChuDe;
import com.example.listentomusic.Model.TheLoai;
import com.example.listentomusic.Model.Theloaitrongngay;
import com.example.listentomusic.R;
import com.example.listentomusic.Service.APIService;
import com.example.listentomusic.Service.DataService;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Collection;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Fragment_ChuDe_TheLoai_ToDay extends Fragment {
    View view;
    HorizontalScrollView horizontalScrollView;
    TextView txtxemthemchudetheloai;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_chude_theloai_today, container, false);
        horizontalScrollView = view.findViewById(R.id.horizontalScrollview);
        txtxemthemchudetheloai = view.findViewById(R.id.textviewxemthem);

        getData();
        return view;
    }

    private void getData() {
        DataService dataService = APIService.getService();
        Call<Theloaitrongngay> callback = dataService.GetCategoryMusic();
        callback.enqueue(new Callback<Theloaitrongngay>() {
            @Override
            public void onResponse(Call<Theloaitrongngay> call, Response<Theloaitrongngay> response) {
                Theloaitrongngay theloaitrongngay = response.body();

               final ArrayList<ChuDe> chuDeArrayList = new ArrayList<ChuDe>();

               //demo database
                theloaitrongngay.setChuDe(new ChuDe("a", "b", "c"));
                theloaitrongngay.setTheLoai(new TheLoai("z","a", "c","d"));



                chuDeArrayList.addAll(theloaitrongngay.getChuDe());

                final  ArrayList<TheLoai> theLoaiArrayList = new ArrayList<TheLoai>();
                theLoaiArrayList.addAll(theloaitrongngay.getTheLoai());

                LinearLayout linearLayout = new LinearLayout(getActivity());
                linearLayout.setOrientation(LinearLayout.HORIZONTAL);
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(580, 250);
                layoutParams.setMargins(10, 20,10, 30);

                for(int i=0; i<chuDeArrayList.size(); i++){
                    CardView cardView = new CardView(getActivity());
                    cardView.setRadius(10);
                    ImageView imageView = new ImageView(getActivity());
                    imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                    if(chuDeArrayList.get(i).getHinhChuDe()  !=null){
                        Picasso.with(getActivity()).load(chuDeArrayList.get(i).getHinhChuDe()).into(imageView);

                    }
                    cardView.setLayoutParams(layoutParams);
                    cardView.addView(imageView);
                    linearLayout.addView(cardView);
                }

                for(int j=0; j<theLoaiArrayList.size(); j++){
                    CardView cardView = new CardView(getActivity());
                    cardView.setRadius(10);
                    ImageView imageView = new ImageView(getActivity());
                    imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                    if(theLoaiArrayList.get(j).getHinhTheloai()  !=null){
                        Picasso.with(getActivity()).load(theLoaiArrayList.get(j).getHinhTheloai()).into(imageView);

                    }
                    cardView.setLayoutParams(layoutParams);
                    cardView.addView(imageView);
                    linearLayout.addView(cardView);
                }

                horizontalScrollView.addView(linearLayout);

            }

            @Override
            public void onFailure(Call<Theloaitrongngay> call, Throwable t) {

            }
        });
    }
}
