package com.example.listentomusic.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Theloaitrongngay {

   private TheLoai theLoai;
   private  ChuDe chuDe;

    public TheLoai getTheLoai() {
        return theLoai;
    }

    public void setTheLoai(TheLoai theLoai) {
        this.theLoai = theLoai;
    }

    public ChuDe getChuDe() {
        return chuDe;
    }

    public void setChuDe(ChuDe chuDe) {
        this.chuDe = chuDe;
    }
}
