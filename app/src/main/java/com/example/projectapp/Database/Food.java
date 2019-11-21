package com.example.projectapp.database;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "food_table")
public class Food {
    @NonNull

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String foodName;
    private String foodDescription;
    private double price;
    private double weight;
    @Ignore
    private String imageUrl;

    public Food( String foodName, String foodDescription, double price, double weight) {

        this.foodName = foodName;
        this.foodDescription = foodDescription;
        this.price = price;
        this.weight = weight;

    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public void setFoodDescription(String foodDescription) {
        this.foodDescription = foodDescription;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setWeight(double weight) {
        this.weight = weight;
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


    public double getWeight() {
        return weight;
    }

    public String getFoodName() {
        return foodName;
    }

    public String getFoodDescription() {
        return foodDescription;
    }

    public double getPrice() {
        return price;
    }

    public String getImageUrl() {
        return imageUrl;
    }
}
