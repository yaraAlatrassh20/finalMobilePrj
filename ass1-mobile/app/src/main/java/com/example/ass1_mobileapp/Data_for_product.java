package com.example.ass1_mobileapp;

import java.util.ArrayList;

public class Data_for_product {
    public static ArrayList<products> getAllProducts() {//----
        ArrayList<products> products = new ArrayList<>();

        products.add(new products("Bag", "Accessories", "Red", true, R.drawable.bag, 5, 20.0));
        products.add(new products("Scarf", "Clothing", "Blue", true, R.drawable.scarf, 3, 15.5));
        products.add(new products("Bag", "Accessories", "Red", false, R.drawable.bag3, 5, 50.0));

        products.add(new products("Wall Art", "Home Decor", "Green", true, R.drawable.wallart, 10, 35.99));
        products.add(new products("gloves", "Clothing", "Blue", true, R.drawable.gloves, 2, 10));

        products.add(new products("Bag", "Accessories", "Red", true, R.drawable.bag2, 10, 30.0));

        return products;
    }
}
