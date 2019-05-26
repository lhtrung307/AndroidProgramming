package com.example.kiemtragiuaky.Models;

public class Diem {
    private int MaSV;
    private int MaMon;
    private float Diem;

    public Diem(int maSV, int maMH, float diem) {
        MaSV = maSV;
        MaMon = maMH;
        Diem = diem;
    }

    public int getMaSV() {
        return MaSV;
    }

    public void setMaSV(int maSV) {
        MaSV = maSV;
    }

    public int getMaMH() {
        return MaMon;
    }

    public void setMaMH(int maMH) {
        MaMon = maMH;
    }

    public float getDiem() {
        return Diem;
    }

    public void setDiem(float diem) {
        Diem = diem;
    }
}
