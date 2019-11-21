package com.example.projectapp.database.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;


import com.example.projectapp.database.Food;
import com.example.projectapp.database.repositories.FoodRepo;

import java.util.List;

public class FoodViewModel extends AndroidViewModel {
    private FoodRepo foodRepo;
private LiveData<List<Food>>foodList;
    public FoodViewModel(@NonNull Application application) {
        super(application);
        foodRepo = FoodRepo.getInstance(application);

    }
    public  void insert (Food food){
        foodRepo.insert(food);
    }
    public  void update (Food food){
        foodRepo.update(food);
    }
    public  void delete (Food food){
        foodRepo.delete(food);
    }
    public  void deleteAll (){
        foodRepo.deleteAllFood();
    }
    public  LiveData<List<Food>> getFoodList(){
        return  foodRepo.getFoodlist();
    }
}
