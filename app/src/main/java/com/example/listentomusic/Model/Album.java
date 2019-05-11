package com.example.listentomusic.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Album {

    @SerializedName("idAlbum")
    @Expose
    private String idAlbum;
    @SerializedName("tenAlbum")
    @Expose
    private String tenAlbum;
    @SerializedName("tenCasiAlbum")
    @Expose
    private String tenCasiAlbum;
    @SerializedName("hinhAlbum")
    @Expose
    private String hinhAlbum;

    public String getIdAlbum() {
        return idAlbum;
    }

    public void setIdAlbum(String idAlbum) {
        this.idAlbum = idAlbum;
    }

    public String getTenAlbum() {
        return tenAlbum;
    }

    public void setTenAlbum(String tenAlbum) {
        this.tenAlbum = tenAlbum;
    }

    public String getTenCaSiAlbum() {
        return tenCasiAlbum;
    }

    public void setTenCaSiAlbum(String tenCaSiAlbum) {
        this.tenCasiAlbum = tenCaSiAlbum;
    }

    public String getHinhAlbum() {
        return hinhAlbum;
    }

    public void setHinhAlbum(String hinhAlbum) {
        this.hinhAlbum = hinhAlbum;
    }
}
