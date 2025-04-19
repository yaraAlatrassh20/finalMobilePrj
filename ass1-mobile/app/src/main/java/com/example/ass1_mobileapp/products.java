package com.example.ass1_mobileapp;
import java.io.Serializable;

public class products implements Serializable{
    private String Name;
    private String Catagores;
    private String Color;
    private boolean is_Available;
    private int image_Id;
    private int Quantities;

    private double Prices;


    public products(String name, String category, String color, boolean isAvailable,
                    int imageResId, int quantity, double price) {
        this.Name = name;
        this.Catagores = category;
        this.Color = color;
        this.is_Available = isAvailable;
        this.image_Id = imageResId;
        this.Quantities = quantity;
        this.Prices = price;
    }

    // Getters
    public String getName() { return Name; }
    public String getCategory() { return Catagores; }
    public String getColor() { return Color; }
    public boolean isIs_Available() { return is_Available; }
    public int getImage_Id() { return image_Id; }
    public int getQuantities() { return Quantities; }
    public double getPrices() { return Prices; }

    public void setQuantities(int quantities) {
        this.Quantities = quantities;
    }

    @Override
    public String toString() {
        return
                "name=" + Name + '\'' +

                " price=" + Prices
                ;
    }
}
