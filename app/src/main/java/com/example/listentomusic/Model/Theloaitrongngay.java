package com.example.listentomusic.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Theloaitrongngay {

    @SerializedName("TheLoai")
    @Expose
    private ArrayList<TheLoai> theLoai = null;
    @SerializedName("ChuDe")
    @Expose
    private ArrayList<ChuDe> chuDe = null;

    public ArrayList<TheLoai> getTheLoai() {
        return theLoai;
    }

    public void setTheLoai(ArrayList<TheLoai> theLoai) {
        this.theLoai = theLoai;
    }

    public ArrayList<ChuDe> getChuDe() {
        return chuDe;
    }

    public void setChuDe(ArrayList<ChuDe> chuDe) {
        this.chuDe = chuDe;
    }
}
