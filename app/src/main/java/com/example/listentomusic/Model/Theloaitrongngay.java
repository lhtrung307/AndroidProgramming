package com.example.listentomusic.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Theloaitrongngay {

    @SerializedName("TheLoai")
    @Expose
    private ArrayList<Genre> genre = null;
    @SerializedName("ChuDe")
    @Expose
    private ArrayList<Category> categories = null;

    public ArrayList<Genre> getGenre() {
        return genre;
    }

    public void setGenre(ArrayList<Genre> genre) {
        this.genre = genre;
    }

    public ArrayList<Category> getCategories() {
        return categories;
    }

    public void setCategories(ArrayList<Category> categories) {
        this.categories = categories;
    }
}
