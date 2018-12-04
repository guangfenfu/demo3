package com.example.myfood;

import android.graphics.Bitmap;

import java.net.MalformedURLException;
import java.net.URL;

public class Dish {
    private int id;
    private String img_url;
    private String name;
    private Bitmap img;

    public Dish(int id, String img_url, String name) {
        this.id = id;
        this.img_url = img_url;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Bitmap getImg() {
        return img;
    }

    public void setImg(Bitmap img) {
        this.img = img;
    }

    public URL getUrl(){
        URL url = null;
        try {
            url = new URL(img_url);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return url;
    }
}
