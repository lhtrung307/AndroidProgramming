package com.example.quanlithucpham;

public class Fruit {
    String img;
    String fruitName;
    String description;

    public Fruit(String img, String fruitName, String description) {
        this.img = img;
        this.fruitName = fruitName;
        this.description = description;
    }

    public Fruit(){
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getFruitName() {
        return fruitName;
    }

    public void setFruitName(String fruitName) {
        this.fruitName = fruitName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return this.fruitName+" (Description: "+ this.description+")";
    }
}
