package com.example.listentomusic.Fragment;

import android.content.Intent;
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

import com.example.listentomusic.Activity.SongsActivity;
import com.example.listentomusic.Activity.AllGenresActivity;
import com.example.listentomusic.Activity.DanhsachtheloaitheochudeActivity;
import com.example.listentomusic.Model.Category;
import com.example.listentomusic.Model.Genre;
import com.example.listentomusic.Model.Theloaitrongngay;
import com.example.listentomusic.R;
import com.example.listentomusic.Service.APIService;
import com.example.listentomusic.Service.DataService;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

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
        txtxemthemchudetheloai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), AllGenresActivity.class);
                startActivity(intent);
            }
        });
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
                final ArrayList<Category> categories = new ArrayList<Category>();
                categories.addAll(theloaitrongngay.getCategories());

                final  ArrayList<Genre> genres = new ArrayList<Genre>();
                genres.addAll(theloaitrongngay.getGenre());
                LinearLayout linearLayout = new LinearLayout(getActivity());
                linearLayout.setOrientation(LinearLayout.HORIZONTAL);
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(580, 250);
                layoutParams.setMargins(10, 20,10, 30);

                for(int i = 0; i< categories.size(); i++){
                    CardView cardView = new CardView(getActivity());
                    cardView.setRadius(10);
                    ImageView imageView = new ImageView(getActivity());
                    imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                    if(categories.get(i).getHinhChuDe() !=null ){
                        Picasso.with(getActivity()).load(categories.get(i).getHinhChuDe()).into(imageView);
                    }
                    cardView.setLayoutParams(layoutParams);
                    cardView.addView(imageView);
                    linearLayout.addView(cardView);
                    final int finalI = i;
                    imageView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(getActivity(), DanhsachtheloaitheochudeActivity.class);
                            intent.putExtra("chude", categories.get(finalI));
                            startActivity(intent);
                        }
                    });
                }

                for(int j = 0; j < genres.size(); j++){
                    CardView cardView = new CardView(getActivity());
                    cardView.setRadius(10);
                    ImageView imageView = new ImageView(getActivity());
                    imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                    if(genres.get(j).getHinhTheloai()  !=null){
                        Picasso.with(getActivity()).load(genres.get(j).getHinhTheloai()).into(imageView);
                    }
                    cardView.setLayoutParams(layoutParams);
                    cardView.addView(imageView);
                    linearLayout.addView(cardView);

                    final int finalJ = j;
                    imageView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(getActivity(), SongsActivity.class);
                            intent.putExtra("idtheloai", genres.get(finalJ));
                            startActivity(intent);
                        }
                    });
                }
                horizontalScrollView.addView(linearLayout);
            }

            @Override
            public void onFailure(Call<Theloaitrongngay> call, Throwable t) {

            }
        });
    }
}
