package com.example.bai1tuan6;

public class Employee {
    private int imgSource;
    private String id,name;
    private boolean gender;

    public Employee() {}

    public Employee(int imgSource, String id, String name, boolean gender) {
        this.imgSource = imgSource;
        this.id = id;
        this.name = name;
        this.gender=gender;
    }

    public int getImgSource() {
        return imgSource;
    }

    public void setImgSource(int imgSource) {
        this.imgSource = imgSource;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isGender() {
        return gender;
    }

    public void setGender(boolean sex) {
        this.gender = sex;
    }
    @Override
    public String toString() {
        return this.id+" - "+this.name;
    }
}
