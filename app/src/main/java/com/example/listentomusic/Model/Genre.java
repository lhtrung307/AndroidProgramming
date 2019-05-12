package com.example.listentomusic.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Genre implements Serializable {

    @SerializedName("idtheloai")
    @Expose
    private String genreId;
    @SerializedName("idchude")
    @Expose
    private String categoryId;
    @SerializedName("tentheloai")
    @Expose
    private String genreName;
    @SerializedName("hinhtheloai")
    @Expose
    private String genreImage;

    public Genre(String idTheloai, String categoryId, String tenTheloai, String hinhTheloai) {
        this.genreId = idTheloai;
        this.categoryId = categoryId;
        this.genreName = tenTheloai;
        this.genreImage = hinhTheloai;
    }

    public String getIdTheloai() {
        return genreId;
    }

    public void setIdTheloai(String idTheloai) {
        this.genreId = idTheloai;
    }

    public String getIdChuDe() {
        return categoryId;
    }

    public void setIdChuDe(String idChuDe) {
        this.categoryId = idChuDe;
    }

    public String getTenTheloai() {
        return genreName;
    }

    public void setTenTheloai(String tenTheloai) {
        this.genreName = tenTheloai;
    }

    public String getHinhTheloai() {
        return genreImage;
    }

    public void setHinhTheloai(String hinhTheloai) {
        this.genreImage = hinhTheloai;
    }
}
