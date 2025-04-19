package com.example.ass1_mobileapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Adapter_product extends RecyclerView.Adapter<Adapter_product.ProductViewHolder> {

    private ArrayList<products> List_product;
    private Context Context;
    private UserPreferences useprefrence;

    public Adapter_product(ArrayList<products> productList) {
        this.List_product = productList;
    }

    public static class ProductViewHolder extends RecyclerView.ViewHolder {//--
        ImageView product_imgview;
        TextView name_Txtview, cat_Txtview, color_txtview, price_txtview, quantity_txtview, available_txtview;
        Button buy_but;
//-------------------------------------------------
        public ProductViewHolder(@NonNull View itemView) {//---
            super(itemView);
            product_imgview = itemView.findViewById(R.id.productImageView);
            name_Txtview = itemView.findViewById(R.id.nameTextView);
            cat_Txtview = itemView.findViewById(R.id.categoryTextView);
            color_txtview = itemView.findViewById(R.id.colorTextView);
            price_txtview = itemView.findViewById(R.id.priceTextView);
            quantity_txtview = itemView.findViewById(R.id.quantityTextView);
            available_txtview = itemView.findViewById(R.id.availabilityTextView);
            buy_but = itemView.findViewById(R.id.buyButton);
        }
    }
//--------------------------
    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context = parent.getContext();
        useprefrence = new UserPreferences(Context);
        View view = LayoutInflater.from(Context).inflate(R.layout.item_product, parent, false);
        return new ProductViewHolder(view);
    }
//---------------------------------
    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {//-
        products prodcts = List_product.get(position);

        holder.product_imgview.setImageResource(prodcts.getImage_Id());
        holder.name_Txtview.setText(prodcts.getName());
        holder.cat_Txtview.setText("Category: " + prodcts.getCategory());
        holder.color_txtview.setText("Color: " + prodcts.getColor());
        holder.price_txtview.setText("Price: $" + prodcts.getPrices());
        holder.quantity_txtview.setText("Quantity: " + prodcts.getQuantities());
        holder.product_imgview.setImageResource(prodcts.getImage_Id());

        boolean avalable = prodcts.getQuantities() > 0;
        holder.available_txtview.setText(avalable ? "Available" : "Out of Stock");
        holder.available_txtview.setTextColor(avalable ? 0xFF008000 : 0xFFFF0000);
        holder.buy_but.setEnabled(avalable);

        holder.buy_but.setOnClickListener(v -> {
            int quantities = prodcts.getQuantities();
            float Budgets = useprefrence.getBudget();
            double Prices = prodcts.getPrices();

            if (quantities > 0) {
               useprefrence.setBudget(110);

                if (Budgets >= Prices) {
                    prodcts.setQuantities(quantities - 1);
                    useprefrence.setBudget(Budgets - (float) Prices);

                    holder.quantity_txtview.setText("Quantity: " + prodcts.getQuantities());
                    boolean stillAvailable = prodcts.getQuantities() > 0;
                    holder.available_txtview.setText(stillAvailable ? "Available" : "Out of stock");
                    holder.available_txtview.setTextColor(stillAvailable ? 0xFF008000 : 0xFFFF0000);
                    holder.buy_but.setEnabled(stillAvailable);
                    useprefrence.saveProductList(List_product);
//------------------------
                    Toast.makeText(Context,
                            " successful!\nRemaining Budget: $" + useprefrence.getBudget(),
                            Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(Context, "Insufficient Budget", Toast.LENGTH_SHORT).show();
                }
            } else {
                holder.available_txtview.setText("Out Of Stock");
                holder.available_txtview.setTextColor(0xFFFF0000);
                holder.buy_but.setEnabled(false);
                Toast.makeText(Context, "Product out of stock", Toast.LENGTH_SHORT).show();
            }
        });
    }
//------------------------------
    @Override
    public int getItemCount() {//--
        return List_product.size();//---
    }
}
