package com.example.nsriva1.myapplication;

import android.graphics.drawable.Drawable;

/**
 * Created by nsriva1 on 3/31/16.
 */
public class Product {
    public String title;
    public String desc;
    public Drawable image;
    public Double price;
    public int quantity;

    public Product(String title, String desc, Drawable image, Double price, int quantity) {
        this.title = title;
        this.desc = desc;
        this.image = image;
        this.price = price;
        this.quantity = quantity;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Drawable getImage() {
        return image;
    }

    public void setImage(Drawable image) {
        this.image = image;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
