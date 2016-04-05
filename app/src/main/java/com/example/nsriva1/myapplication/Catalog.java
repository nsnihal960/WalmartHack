package com.example.nsriva1.myapplication;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nsriva1 on 3/31/16.
 */

public class Catalog {
    private static List<Product> items = new ArrayList<Product>();

    public static List<Product> getItems() {
        return items;
    }

    public static void addItems(Product p) {
        if (p != null) {
            items.add(p);
        }

    }
}