package com.example.projectapp.database;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "drink_table")
public class Drinks {
    @NonNull

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String drinkName;
    private String drinkDescription;
    private double price;
    private double capacity;
    @Ignore
    private String imageUrl;

    public Drinks(String drinkName, String drinkDescription, double price, double capacity) {

        this.drinkName = drinkName;
        this.drinkDescription = drinkDescription;
        this.price = price;
        this.capacity = capacity;

    }

    public void setDrinkName(String drinkName) {
        this.drinkName = drinkName;
    }

    public void setDrinkDescription(String drinkDescription) {
        this.drinkDescription = drinkDescription;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setCapacity(double capacity) {
        this.capacity = capacity;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public int getId() {
        return id;
    }


    public void setId(int id) {
        this.id = id;
    }


    public double getCapacity() {
        return capacity;
    }

    public String getDrinkName() {
        return drinkName;
    }

    public String getDrinkDescription() {
        return drinkDescription;
    }

    public double getPrice() {
        return price;
    }

    public String getImageUrl() {
        return imageUrl;
    }
}
