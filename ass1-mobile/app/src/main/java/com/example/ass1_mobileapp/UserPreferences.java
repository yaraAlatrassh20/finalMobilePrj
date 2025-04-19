package com.example.ass1_mobileapp;//


import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;

import java.util.ArrayList;

public class UserPreferences {

    private static final String PREFRENCE_NAME = "UserPrefs";
    private static final String BUDGET_KEY = "user_budget";
    private static final String PRODUCT_LIST_KEY = "product_list";
    private static final float DEFAULT_BUDGET = 110.0f;

    private SharedPreferences Sharedpreference;
    private SharedPreferences.Editor Editor;
//---------------------------------
    public UserPreferences(Context context) {//
        Sharedpreference = context.getSharedPreferences(PREFRENCE_NAME, Context.MODE_PRIVATE);
        Editor = Sharedpreference.edit();
    }
//-----------------------------
    public float getBudget() {//
        return Sharedpreference.getFloat(BUDGET_KEY, DEFAULT_BUDGET);
    }
//---------------------------------------
    public void setBudget(float v) {//
        Editor.putFloat(BUDGET_KEY, v);
        Editor.apply();
    }
//--------------------------------------------------
    public void saveProductList(ArrayList<products> productList) {//save product list
        Gson Gson = new Gson();
        String json = Gson.toJson(productList); //array list to json
        Editor.putString(PRODUCT_LIST_KEY, json); //save sting in shared prefrence
        Editor.apply();
    }
//-------------------------------------------------------------------------
    public ArrayList<products> getProductList() {//get the product list fron shared prefrencr
        String json = Sharedpreference.getString(PRODUCT_LIST_KEY, ""); //get json from shared prefrence
        Gson Gson = new Gson();
        ArrayList<products> ListofProduct = new ArrayList<>();

        if (!json.isEmpty()) {//if there is no saved in the shared prefrence
            products[] products = Gson.fromJson(json, com.example.ass1_mobileapp.products[].class); //json to list
            for (com.example.ass1_mobileapp.products product : products) {
                ListofProduct.add(product);
            }
        }

        return ListofProduct;
    }
}
