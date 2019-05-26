package com.example.kiemtragiuaky.Models;

public class SinhVien {
    private int MaSV;
    private String HoVaTen;
    private int Phai;
    private String NgaySinh;
    private String Diachi;
    private String DienThoai;
    private int Makhoa;

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }

    private boolean isChecked;

    public SinhVien(int maSV, String hoVaTen, int phai, String ngaySinh, String diachi, String dienThoai, int makhoa, boolean ischecked) {
        MaSV = maSV;
        HoVaTen = hoVaTen;
        Phai = phai;
        NgaySinh = ngaySinh;
        Diachi = diachi;
        DienThoai = dienThoai;
        Makhoa = makhoa;
        isChecked = ischecked;
    }

    public int getMaSV() {
        return MaSV;
    }

    public void setMaSV(int maSV) {
        MaSV = maSV;
    }

    public String getHoVaTen() {
        return HoVaTen;
    }

    public void setHoVaTen(String hoVaTen) {
        HoVaTen = hoVaTen;
    }

    public int getPhai() {
        return Phai;
    }

    public void setPhai(int phai) {
        Phai = phai;
    }

    public String getNgaySinh() {
        return NgaySinh;
    }

    public void setNgaySinh(String ngaySinh) {
        NgaySinh = ngaySinh;
    }

    public String getDiachi() {
        return Diachi;
    }

    public void setDiachi(String diachi) {
        Diachi = diachi;
    }

    public String getDienThoai() {
        return DienThoai;
    }

    public void setDienThoai(String dienThoai) {
        DienThoai = dienThoai;
    }

    public int getMakhoa() {
        return Makhoa;
    }

    public void setMakhoa(int makhoa) {
        Makhoa = makhoa;
    }

    public String toString() {
        return this.getMaSV() + "   ---   " + this.getHoVaTen();
    }
}
