package com.example.projectapp.database.repositories;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;


import com.example.projectapp.database.Food;
import com.example.projectapp.database.MenuDatabase;
import com.example.projectapp.database.interfaces.FoodDao;

import java.util.List;

public class FoodRepo {
    private FoodDao foodDao;
    private LiveData<List<Food>> foodlist;
    private static FoodRepo instance;

    public FoodRepo(Application application) {
        MenuDatabase database = MenuDatabase.getInstance(application);
        foodDao = database.foodDao();
        foodlist = foodDao.getAllFoodies();

    }

    public static synchronized FoodRepo getInstance(Application application) {
        if (instance == null) {
            instance = new FoodRepo(application);
        }
        return instance;
    }

    public void insert(Food food) {
        new InsertFoodAsyncTask(foodDao).execute(food);
    }

    public void update(Food food) {
        new UpdateFoodAsyncTask(foodDao).execute(food);
    }

    public void delete(Food food) {
        new DeleteFoodAsyncTask(foodDao).execute(food);
    }

    public void deleteAllFood() {
        new DeleteAllAsyncTask(foodDao).execute();
    }

    public LiveData<List<Food>> getFoodlist() {
        return foodlist;
    }

    private static class InsertFoodAsyncTask extends AsyncTask<Food, Void, Void> {
        private FoodDao foodDao;

        private InsertFoodAsyncTask(FoodDao foodDao) {
            this.foodDao = foodDao;
        }

        @Override
        protected Void doInBackground(Food... foods) {

            foodDao.insert(foods[0]);
            return null;
        }
    }

    private static class UpdateFoodAsyncTask extends AsyncTask<Food, Void, Void> {
        private FoodDao foodDao;

        private UpdateFoodAsyncTask(FoodDao foodDao) {
            this.foodDao = foodDao;
        }

        @Override
        protected Void doInBackground(Food... foods) {

            foodDao.update(foods[0]);
            return null;
        }
    }

    private static class DeleteFoodAsyncTask extends AsyncTask<Food, Void, Void> {
        private FoodDao foodDao;

        private DeleteFoodAsyncTask(FoodDao foodDao) {
            this.foodDao = foodDao;
        }

        @Override
        protected Void doInBackground(Food... foods) {

            foodDao.delete(foods[0]);
            return null;
        }
    }

    private static class DeleteAllAsyncTask extends AsyncTask<Void, Void, Void> {
        private FoodDao foodDao;

        private DeleteAllAsyncTask(FoodDao foodDao) {
            this.foodDao = foodDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {

            foodDao.deleteAllFood();
            return null;
        }
    }
}
