package com.example.projectapp.networking;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

public class MealViewModel extends ViewModel {


    MealRepository repository;

    public MealViewModel(){
        repository = MealRepository.getInstance();
    }

  public   LiveData<Meal> getMeal() {
        return repository.getMeal();
    }

    public void updateMeal() {
        repository.updateMeal();
    }
}
