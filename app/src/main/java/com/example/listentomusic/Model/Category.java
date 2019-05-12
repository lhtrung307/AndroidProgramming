package com.example.listentomusic.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Category implements Serializable {

    @SerializedName("idchude")
    @Expose
    private String categoryId;
    @SerializedName("tenchude")
    @Expose
    private String categoryName;
    @SerializedName("hinhchude")
    @Expose
    private String categoryImage;

    public Category(String categoryId, String categoryName, String categoryImage) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.categoryImage = categoryImage;
    }

    public String getIdChuDe() {
        return categoryId;
    }

    public void setIdChuDe(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getTen() {
        return categoryName;
    }

    public void setTen(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getHinhChuDe() {
        return categoryImage;
    }

    public void setHinhChuDe(String categoryImage) {
        this.categoryImage = categoryImage;
    }

}
