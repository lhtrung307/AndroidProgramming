package com.example.kiemtragiuaky.Models;

public class Mon {
    private int MaMon;
    private String TenMon;
    private int SoTiet;
    boolean isChecked;

    public Mon(int maMon, String tenMon, int soTiet, boolean check) {
        MaMon = maMon;
        TenMon = tenMon;
        SoTiet = soTiet;
        isChecked = check;
    }

    public int getMaMon() {
        return MaMon;
    }

    public void setMaMon(int maMon) {
        MaMon = maMon;
    }

    public String getTenMon() {
        return TenMon;
    }

    public void setTenMon(String tenMon) {
        TenMon = tenMon;
    }

    public int getSoTiet() {
        return SoTiet;
    }

    public void setSoTiet(int soTiet) {
        SoTiet = soTiet;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }

    public String toString(){
        return this.getTenMon();
    }
}
