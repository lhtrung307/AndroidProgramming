package com.example.listentomusic.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;

public class ChuDe implements Serializable {

    @SerializedName("idchude")
    @Expose
    private String idchude;
    @SerializedName("tenchude")
    @Expose
    private String tenchude;
    @SerializedName("hinhchude")
    @Expose
    private String hinhchude;

    public ChuDe(String idChuDe, String ten, String hinhchude) {
        this.idchude = idChuDe;
        this.tenchude = ten;
        this.hinhchude = hinhchude;
    }

    public String getIdChuDe() {
        return idchude;
    }

    public void setIdChuDe(String idchude) {
        this.idchude = idchude;
    }

    public String getTen() {
        return tenchude;
    }

    public void setTen(String tenchude) {
        this.tenchude = tenchude;
    }

    public String getHinhChuDe() {
        return hinhchude;
    }

    public void setHinhChuDe(String hinhChuDe) {
        this.hinhchude = hinhChuDe;
    }

}
