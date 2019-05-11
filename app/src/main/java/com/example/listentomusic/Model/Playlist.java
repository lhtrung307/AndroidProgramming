package com.example.listentomusic.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Playlist implements Serializable {
    @SerializedName("idPlaylist")
    @Expose
    private String idPlaylist;
    @SerializedName("ten")
    @Expose
    private String ten;
    @SerializedName("hinhnen")
    @Expose
    private String hinhnen;
    @SerializedName("hinhicon")
    @Expose
    private String hinhicon;

    public String getIdPlaylist() {
        return idPlaylist;
    }

    public void setIdPlaylist(String idPlaylist) {
        this.idPlaylist = idPlaylist;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getHinhPlaylist() {
        return hinhnen;
    }

    public void setHinhPlaylist(String hinhPlaylist) {
        this.hinhnen = hinhPlaylist;
    }

    public String getIcon() {
        return hinhicon;
    }

    public void setIcon(String icon) {
        this.hinhicon = icon;
    }
}
