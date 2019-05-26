package com.example.listentomusic.Service;

import com.example.listentomusic.Model.Album;
import com.example.listentomusic.Model.Category;
import com.example.listentomusic.Model.Genre;
import com.example.listentomusic.Model.Song;
import com.example.listentomusic.Model.Playlist;
import com.example.listentomusic.Model.Banner;
import com.example.listentomusic.Model.Theloaitrongngay;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface DataService {
    @GET("songbanner.php")
    Call<List<Banner>> GetDataBanner();

    @GET("playlistforcurrentday.php")
    Call<List<Playlist>> GetPlaylistCurentDay();

    @GET("chudevatheloaitrongngay.php")
    Call<Theloaitrongngay> GetCategoryMusic();

    @GET("albumhot.php")
    Call<List<Album>> GetAlbumHot();

    @GET("baihatduocthich.php")
    Call<List<Song>> GetBaiHatHot();

    @FormUrlEncoded
    @POST("danhsachbaihat.php")
    Call<List<Song>> Getdanhsachbaihattheoquangcao(@Field("idquangcao") String idquangcao);

    @FormUrlEncoded
    @POST("danhsachbaihat.php")
    Call<List<Song>> GetDanhsachbaitheoplaylist(@Field("idplaylist") String idplaylist);

    @FormUrlEncoded
    @POST("danhsachbaihat.php")
    Call<List<Song>> GetDanhsachbaihattheotheloai(@Field("idtheloai") String idtheloai);

    @GET("danhsachplaylist.php")
    Call<List<Playlist>> GetDanhsachCacPlaylist();

    @GET("tatcachude.php")
    Call<List<Category>> GetAllChuDe();

    @FormUrlEncoded
    @POST("theloaitheochude.php")
    Call<List<Genre>> GetTheLoaitheochude(@Field("idchude") String idchude);

    @GET ("tatcaalbum.php")
    Call<List<Album>> GetAllAlbum();

    @FormUrlEncoded
    @POST("danhsachbaihat.php")
    Call<List<Song>> GetDanhsachbaihattheoalbum(@Field("idalbum") String idalbum);

    @FormUrlEncoded
    @POST("updateluotthich.php")
    Call<String> UpdateLuotThich(@Field("luotthich") String luotthich, @Field("idbaihat") String idbaihat);

    @FormUrlEncoded
    @POST("searchbaihat.php")
    Call<List<Song>> GetSearchBaiHat(@Field("tukhoa") String tukhoa);

    @FormUrlEncoded
    @POST("checkuser.php")
    Call<String> CheckUser(@Field("username") String username,@Field("password") String password);

    @FormUrlEncoded
    @POST("insertusers.php")
    Call<String> Insert(@Field("username") String username,@Field("password") String password);

    @FormUrlEncoded
    @POST("loibaihat.php")
    Call<String> GetLoiBaiHat(@Field("idBaiHat") String idBaiHat);

    @FormUrlEncoded
    @POST("insertuserlikebaihat.php")
    Call<String> UserLikeBaiHat(@Field("username") String username, @Field("idBaiHat") String idBaiHat);
}
