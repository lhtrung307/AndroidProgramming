package com.example.listentomusic.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.listentomusic.Activity.PlayNhacActivity;
import com.example.listentomusic.Model.BaiHat;
import com.example.listentomusic.R;
import com.example.listentomusic.Service.APIService;
import com.example.listentomusic.Service.DataService;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchBaiHatAdapter extends RecyclerView.Adapter<SearchBaiHatAdapter.ViewHolder> {

    Context context;
    ArrayList<BaiHat> mangbaihat;

    public SearchBaiHatAdapter(Context context, ArrayList<BaiHat> mangbaihat) {
        this.context = context;
        this.mangbaihat = mangbaihat;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.dong_search_bai_hat, viewGroup, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        BaiHat baiHat = mangbaihat.get(i);
        viewHolder.txtTenBaiHat.setText(baiHat.getTenbaihat());
        viewHolder.txtCaSi.setText(baiHat.getCasi());
        Picasso.with(context).load(baiHat.getHinhbaihat()).into(viewHolder.imgBaiHat);
    }

    @Override
    public int getItemCount() {
        return mangbaihat.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView txtTenBaiHat, txtCaSi;
        ImageView imgBaiHat, imgLuotThich;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTenBaiHat = itemView.findViewById(R.id.textviewsearchtenbaihat);
            txtCaSi = itemView.findViewById(R.id.textviewsearchtencasi);
            imgBaiHat = itemView.findViewById(R.id.imageviewsearchbaihat);
            imgLuotThich = itemView.findViewById(R.id.imageviewsearchluotthich);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, PlayNhacActivity.class);
                    intent.putExtra("cakhuc", mangbaihat.get(getPosition()));
                    context.startActivity(intent);
                }
            });
            imgLuotThich.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    imgLuotThich.setImageResource(R.drawable.iconloved);
                    DataService dataService = APIService.getService();
                    Call<String> callback = dataService.UpdateLuotThich("1", mangbaihat.get(getPosition()).getIdbaihat());
                    callback.enqueue(new Callback<String>() {
                        @Override
                        public void onResponse(Call<String> call, Response<String> response) {
                            String ketqua = response.body();
                            if (ketqua.equals("Success")){
                                Toast.makeText(context, "Đã Thích", Toast.LENGTH_SHORT).show();
                            } else{
                                Toast.makeText(context, "Lỗi!", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<String> call, Throwable t) {

                        }
                    });
                    imgLuotThich.setEnabled(false);
                }
            });

        }
    }
}
