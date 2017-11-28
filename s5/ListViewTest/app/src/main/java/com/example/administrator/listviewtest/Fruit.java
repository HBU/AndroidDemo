package com.example.administrator.listviewtest;

/**
 * Created by Administrator on 2017/6/3 0003.
 */

public class Fruit {
    private String name;
    private int imgId;


    public Fruit(String name, int imgId) {
        this.name = name;
        this.imgId = imgId;
    }

    public String getName() {
        return name;
    }

    public int getImgId() {
        return imgId;
    }
}
