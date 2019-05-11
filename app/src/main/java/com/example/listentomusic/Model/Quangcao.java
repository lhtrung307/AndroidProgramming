package com.example.listentomusic.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Quangcao  implements Serializable {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("hinhanh")
    @Expose
    private String hinhanh;
    @SerializedName("noidung")
    @Expose
    private String noidung;
    @SerializedName("idBaiHat")
    @Expose
    private String idBaiHat;
    @SerializedName("tenbaihat")
    @Expose
    private String tenbaihat;
    @SerializedName("hinhbaihat")
    @Expose
    private String hinhbaihat;

    public String getIdQuangCao() {
        return id;
    }

    public void setIdQuangCao(String idQuangCao) {
        this.id = idQuangCao;
    }

    public String getHinhanh() {
        return hinhanh;
    }

    public void setHinhanh(String hinhanh) {
        this.hinhanh = hinhanh;
    }

    public String getNoidung() {
        return noidung;
    }

    public void setNoidung(String noidung) {
        this.noidung = noidung;
    }

    public String getIdBaiHat() {
        return idBaiHat;
    }

    public void setIdBaiHat(String idBaiHat) {
        this.idBaiHat = idBaiHat;
    }

    public String getTenBaiHat() {
        return tenbaihat;
    }

    public void setTenBaiHat(String tenBaiHat) {
        this.tenbaihat = tenBaiHat;
    }

    public String getHinhBaiHat() {
        return hinhbaihat;
    }

    public void setHinhBaiHat(String hinhBaiHat) {
        this.hinhbaihat = hinhBaiHat;
    }

}
