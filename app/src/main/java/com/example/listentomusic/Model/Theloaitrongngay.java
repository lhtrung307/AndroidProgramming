package com.example.listentomusic.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Theloaitrongngay {

    @SerializedName("TheLoai")
    @Expose
    private ArrayList<Genre> genres = null;
    @SerializedName("ChuDe")
    @Expose
    private ArrayList<Category> categories = null;

    public ArrayList<Genre> getGenre() {
        return genres;
    }

    public void setGenre(ArrayList<Genre> genres) {
        this.genres = genres;
    }

    public ArrayList<Category> getCategories() {
        return categories;
    }

    public void setCategories(ArrayList<Category> categories) {
        this.categories = categories;
    }
}
