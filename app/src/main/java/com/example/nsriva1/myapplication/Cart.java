package com.example.nsriva1.myapplication;

import java.util.List;
import java.util.ArrayList;

/**
 * Created by nsriva1 on 3/31/16.
 */
public class Cart {
    public static String time = "";
    public static boolean lever = true;
    private static List<DisplayItem> items= new ArrayList<DisplayItem>();
    public static int cartCount = 0;

    public static int getCartCount() {
        return cartCount;
    }

    public static void setCartCount(int cartCount) {
        Cart.cartCount = cartCount;
    }

    public static List<DisplayItem> getItems(){
        return items;
    }
    public static void addItems(DisplayItem p){
        if(p != null){
            items.add(p);
        }

    }
    public static void clear(){
        items.clear();
    }
}

