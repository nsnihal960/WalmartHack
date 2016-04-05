package com.example.nsriva1.myapplication;

import android.graphics.Bitmap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;

/**
 * Created by nsriva1 on 4/1/16.
 */
public class DisplayItem {
    Bitmap bitmap;
    String title;
    Double price;
    Button b1;
    Button b2;
    EditText e1;

    public DisplayItem(Bitmap bitmap, String title, Double price,  Button b1, Button b2, EditText e1) {
        this.bitmap = bitmap;
        this.title = title;
        this.price = price;
        this.b1 = b1;
        this.b2 = b2;
        this.e1 = e1;
    }

    public Button getB1() {
        return b1;
    }

    public void setB1(Button b1) {
        this.b1 = b1;
    }

    public Button getB2() {
        return b2;
    }

    public void setB2(Button b2) {
        this.b2 = b2;
    }

    public EditText getE1() {
        return e1;
    }

    public void setE1(EditText e1) {
        this.e1 = e1;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
