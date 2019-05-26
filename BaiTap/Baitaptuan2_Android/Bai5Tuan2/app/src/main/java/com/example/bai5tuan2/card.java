package com.example.bai5tuan2;

public class card {
    private int value;
    private int path;

    public card(int value, int path) {
        this.value = value;
        this.path = path;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getPath() {
        return path;
    }

    public void setPath(int path) {
        this.path = path;
    }
}
