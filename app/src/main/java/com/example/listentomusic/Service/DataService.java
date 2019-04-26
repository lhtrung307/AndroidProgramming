package com.example.listentomusic.Service;

import com.example.listentomusic.Model.Album;
import com.example.listentomusic.Model.BaiHat;
import com.example.listentomusic.Model.Playlist;
import com.example.listentomusic.Model.Quangcao;
import com.example.listentomusic.Model.Theloaitrongngay;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface DataService {
    @GET("songbanner.php")
    Call<List<Quangcao>> GetDataBanner();

    @GET("playlistforcurrentday.php")
    Call<List<Playlist>> GetPlaylistCurentDay();

    @GET("chudevatheloaitrongngay.php")
    Call<Theloaitrongngay> GetCategoryMusic();

    @GET("albumhot.php")
    Call<List<Album>> GetAlbumHot();

    @GET("baihatduocthich.php")
    Call<List<BaiHat>> GetBaiHatHot();

    @FormUrlEncoded
    @POST("danhsachbaihat.php")
    Call<List<BaiHat>> Getdanhsachbaihattheoquangcao(@Field("idquangcao") String idquangcao);

    @FormUrlEncoded
    @POST("danhsachbaihat.php")
    Call<List<BaiHat>> GetDanhsachbaitheoplaylist(@Field("idplaylist") String idplaylist);


}
