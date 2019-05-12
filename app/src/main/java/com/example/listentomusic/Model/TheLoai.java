package com.example.listentomusic.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class TheLoai implements Serializable {

    @SerializedName("idtheloai")
    @Expose
    private String idtheloai;
    @SerializedName("idchude")
    @Expose
    private String idchude;
    @SerializedName("tentheloai")
    @Expose
    private String tentheloai;
    @SerializedName("hinhtheloai")
    @Expose
    private String hinhtheloai;

    public TheLoai(String idTheloai, String idchude, String tenTheloai, String hinhTheloai) {
        this.idtheloai = idTheloai;
        this.idchude = idchude;
        this.tentheloai = tenTheloai;
        this.hinhtheloai = hinhTheloai;
    }

    public String getIdTheloai() {
        return idtheloai;
    }

    public void setIdTheloai(String idTheloai) {
        this.idtheloai = idTheloai;
    }

    public String getIdChuDe() {
        return idchude;
    }

    public void setIdChuDe(String idChuDe) {
        this.idchude = idChuDe;
    }

    public String getTenTheloai() {
        return tentheloai;
    }

    public void setTenTheloai(String tenTheloai) {
        this.tentheloai = tenTheloai;
    }

    public String getHinhTheloai() {
        return hinhtheloai;
    }

    public void setHinhTheloai(String hinhTheloai) {
        this.hinhtheloai = hinhTheloai;
    }
}
