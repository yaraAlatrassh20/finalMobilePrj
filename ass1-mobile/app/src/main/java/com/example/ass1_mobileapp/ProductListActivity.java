package com.example.ass1_mobileapp;

import android.os.Bundle;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Collections;

public class ProductListActivity extends AppCompatActivity {

    private RecyclerView product_RecView;
    private Adapter_product adapt_product;
    private ArrayList<products> list_product;
    private ArrayList<products> list_filter;
//---------------------------------------------------------------------------
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {//---
        super.onCreate(savedInstanceState);//----------
        setContentView(R.layout.activity_product_list);

        product_RecView = findViewById(R.id.productRecyclerView);
        product_RecView.setLayoutManager(new LinearLayoutManager(this));

        UserPreferences user_Pref = new UserPreferences(this);
        list_product = user_Pref.getProductList();

        list_filter = new ArrayList<>();
//hold data from intent
        String Names = getIntent().getStringExtra("productName");
        String Catagores = getIntent().getStringExtra("category");
        String Colors = getIntent().getStringExtra("color");
        boolean only_Available = getIntent().getBooleanExtra("availableOnly", false);
        boolean Sort_by_Price = getIntent().getBooleanExtra("sortByPrice", false);


        for (products product : list_product) {
            if ((Names.isEmpty() || product.getName().toLowerCase().contains(Names.toLowerCase()))
                    && product.getCategory().equalsIgnoreCase(Catagores)
                    && product.getColor().equalsIgnoreCase(Colors)
                    && (!only_Available || product.isIs_Available())) {
                list_filter.add(product);
            }
        }

        if (Sort_by_Price) {
            Collections.sort(list_filter, Comparator.comparingDouble(products::getPrices));
        }

        if (list_filter.isEmpty()) {
            Toast.makeText(this, "No product found", Toast.LENGTH_SHORT).show();
        }

        adapt_product = new Adapter_product(list_filter);
        product_RecView.setAdapter(adapt_product);
    }
}
