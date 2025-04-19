package com.example.ass1_mobileapp;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Switch;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
public class SearchProductActivity extends AppCompatActivity {
    //----------------------------------------------------
    private EditText edtext_productName;
    private Spinner Spinner_catagory, Spinner_color;
    private RadioButton radiobutt_price;
    private CheckBox checkbox_delever1, checkbox_delever2, checkbox_delever3;
    private Switch switch_avalable;
    private Button butt_search;
    private ListView listveiw_product;

    //---------------------------------------------------------------------
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        //element for the search page
        edtext_productName = findViewById(R.id.productNameEditText);
        Spinner_catagory = findViewById(R.id.categorySpinner);
        Spinner_color = findViewById(R.id.colorSpinner);
        radiobutt_price = findViewById(R.id.priceRadioButton);
        checkbox_delever1 = findViewById(R.id.delivary1CheckBox);
        checkbox_delever2 = findViewById(R.id.delivary2CheckBox);
        checkbox_delever3 = findViewById(R.id.delivary3CheckBox);
        switch_avalable = findViewById(R.id.availableSwitch);
        butt_search = findViewById(R.id.searchButton);
        listveiw_product = findViewById(R.id.productListView); // تحديد ListView
//--------------------------------------------------------------------
        //set spinner for catagory
        ArrayAdapter<CharSequence> adapt_catagory = ArrayAdapter.createFromResource(
                this,
                R.array.category_options,
                android.R.layout.simple_spinner_item
        );
        adapt_catagory.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Spinner_catagory.setAdapter(adapt_catagory);
//--------------------------
        //set spinner for color
        ArrayAdapter<CharSequence> adapt_color = ArrayAdapter.createFromResource(
                this,
                R.array.color_options,
                android.R.layout.simple_spinner_item
        );
        adapt_color.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Spinner_color.setAdapter(adapt_color);
//----------------------------------------------
        display_product();

        butt_search.setOnClickListener(v -> {//hanling for button search
            UserPreferences user_Preference = new UserPreferences(SearchProductActivity.this);
            ArrayList<products> products = Data_for_product.getAllProducts();
            user_Preference.saveProductList(products);

            Intent Intents = new Intent(SearchProductActivity.this, ProductListActivity.class);

            Intents.putExtra("productName", edtext_productName.getText().toString());
            Intents.putExtra("category", Spinner_catagory.getSelectedItem().toString());
            Intents.putExtra("color", Spinner_color.getSelectedItem().toString());
            Intents.putExtra("sortByPrice", radiobutt_price.isChecked());
            Intents.putExtra("filterCotton", checkbox_delever1.isChecked());
            Intents.putExtra("filterHomeDelivery", checkbox_delever2.isChecked());
            Intents.putExtra("filterStorePickup", checkbox_delever3.isChecked());
            Intents.putExtra("availableOnly", switch_avalable.isChecked());

            startActivity(Intents);
        });
//---------------------------------------
        listveiw_product.setOnItemClickListener((parent, view, position, id) -> {
            products select_products = Data_for_product.getAllProducts().get(position);

            Intent Intents = new Intent(SearchProductActivity.this, ProductListActivity.class);
            Intents.putExtra("productName", select_products.getName());
            Intents.putExtra("category", select_products.getCategory());
            Intents.putExtra("color", select_products.getColor());
            Intents.putExtra("availableOnly", select_products.isIs_Available());
            Intents.putExtra("sortByPrice", true);
            startActivity(Intents);
        });

    }

    //----------------------------------------------
    public void display_product() {//display products name is list view

        ArrayList<products> productList = Data_for_product.getAllProducts();

        ArrayList<String> productNames = new ArrayList<>();
        for (products product : productList) {
            productNames.add(product.getName());
        }

        ArrayAdapter<String> productAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, productNames);
        listveiw_product.setAdapter(productAdapter);  // ربط الـ Adapter بـ ListView
    }
}



