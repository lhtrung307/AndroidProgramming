package com.example.listentomusic.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Collection;
import java.util.Iterator;

public class TheLoai implements Collection<TheLoai> {

    @SerializedName("IdTheLoai")
    @Expose
    private String idTheloai;

    @SerializedName("IdChuDe")
    @Expose
    private String idChuDe;

    public TheLoai(String idTheloai, String idChuDe, String tenTheloai, String hinhTheloai) {
        this.idTheloai = idTheloai;
        this.idChuDe = idChuDe;
        this.tenTheloai = tenTheloai;
        this.hinhTheloai = hinhTheloai;
    }

    @SerializedName("TenTheLoai")
    @Expose
    private String tenTheloai;

    @SerializedName("HinhTheLoai")
    @Expose
    private String hinhTheloai;

    public String getIdTheloai() {
        return idTheloai;
    }

    public void setIdTheloai(String idTheloai) {
        this.idTheloai = idTheloai;
    }

    public String getIdChuDe() {
        return idChuDe;
    }

    public void setIdChuDe(String idChuDe) {
        this.idChuDe = idChuDe;
    }

    public String getTenTheloai() {
        return tenTheloai;
    }

    public void setTenTheloai(String tenTheloai) {
        this.tenTheloai = tenTheloai;
    }

    public String getHinhTheloai() {
        return hinhTheloai;
    }

    public void setHinhTheloai(String hinhTheloai) {
        this.hinhTheloai = hinhTheloai;
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
    public Iterator<TheLoai> iterator() {
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
    public boolean add(TheLoai theLoai) {
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
    public boolean addAll(Collection<? extends TheLoai> c) {
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
