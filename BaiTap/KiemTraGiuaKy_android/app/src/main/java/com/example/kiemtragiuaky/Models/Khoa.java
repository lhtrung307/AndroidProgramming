package com.example.kiemtragiuaky.Models;

public class Khoa {
    private int Makhoa;
    private String Tenkhoa;
    private boolean isChecked;

    public Khoa(int makhoa, String tenkhoa,boolean isChecked) {
        this.Makhoa = makhoa;
        this.Tenkhoa = tenkhoa;
        this.isChecked =isChecked;
    }

    public int getMakhoa() {
        return Makhoa;
    }

    public void setMakhoa(int makhoa) {
        Makhoa = makhoa;
    }

    public String getTenkhoa() {
        return Tenkhoa;
    }

    public void setTenkhoa(String tenkhoa) {
        Tenkhoa = tenkhoa;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }
    public String toString() {
        return this.getTenkhoa();
    }
}
