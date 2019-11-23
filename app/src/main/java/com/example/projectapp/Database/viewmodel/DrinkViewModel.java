package com.example.projectapp.database.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;


import com.example.projectapp.database.Drinks;
import com.example.projectapp.database.repositories.DrinkRepo;

import java.util.List;

public class DrinkViewModel extends AndroidViewModel {
    private DrinkRepo drinkRepo;

    public DrinkViewModel(@NonNull Application application) {
        super(application);
        drinkRepo = DrinkRepo.getInstance(application);

    }
    public  void insert (Drinks drink){
        drinkRepo.insert(drink);
    }
    public  void update (Drinks drink){
        drinkRepo.update(drink);
    }
    public  void delete (Drinks drink){
        drinkRepo.delete(drink);
    }
    public  void deleteAll (){
        drinkRepo.deleteAllDrinks();
    }
    public  LiveData<List<Drinks>> getDrinkList(){
        return  drinkRepo.getDrinks();
    }
}
