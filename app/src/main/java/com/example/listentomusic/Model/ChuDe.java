package com.example.listentomusic.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;

public class ChuDe implements Serializable {

    @SerializedName("idChuDe")
    @Expose
    private String idChuDe;
    @SerializedName("tenchude")
    @Expose
    private String tenchude;
    @SerializedName("hinhchude")
    @Expose
    private String hinhchude;

    public ChuDe(String idChuDe, String ten, String hinhChuDe) {
        this.idChuDe = idChuDe;
        this.tenchude = ten;
        this.hinhchude = hinhChuDe;
    }

    public String getIdChuDe() {
        return idChuDe;
    }

    public void setIdChuDe(String idChuDe) {
        this.idChuDe = idChuDe;
    }

    public String getTen() {
        return tenchude;
    }

    public void setTen(String ten) {
        this.tenchude = ten;
    }

    public String getHinhChuDe() {
        return hinhchude;
    }

    public void setHinhChuDe(String hinhChuDe) {
        this.hinhchude = hinhChuDe;
    }

}
