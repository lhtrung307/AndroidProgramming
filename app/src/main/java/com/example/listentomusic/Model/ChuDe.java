package com.example.listentomusic.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Collection;
import java.util.Iterator;

public class ChuDe implements Collection<ChuDe> {

    @SerializedName("IdChuDe")
    @Expose
    private String idChuDe;

    @SerializedName("TenChuDe")
    @Expose
    private String ten;

    public ChuDe(String idChuDe, String ten, String hinhChuDe) {
        this.idChuDe = idChuDe;
        this.ten = ten;
        this.hinhChuDe = hinhChuDe;
    }

    @SerializedName("HinhChuDe")
    @Expose
    private String hinhChuDe;

    public String getIdChuDe() {
        return idChuDe;
    }

    public void setIdChuDe(String idChuDe) {
        this.idChuDe = idChuDe;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getHinhChuDe() {
        return hinhChuDe;
    }

    public void setHinhChuDe(String hinhChuDe) {
        this.hinhChuDe = hinhChuDe;
    }


    @Override
    public int size() {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public boolean contains(Object o) {
        return false;
    }

    @Override
    public Iterator<ChuDe> iterator() {
        return null;
    }

    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return null;
    }

    @Override
    public boolean add(ChuDe chuDe) {
        return false;
    }

    @Override
    public boolean remove(Object o) {
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends ChuDe> c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return false;
    }

    @Override
    public void clear() {

    }
}
