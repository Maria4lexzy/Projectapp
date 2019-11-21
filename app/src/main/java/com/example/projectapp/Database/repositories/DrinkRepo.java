package com.example.projectapp.database.repositories;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;


import com.example.projectapp.database.Drinks;
import com.example.projectapp.database.MenuDatabase;
import com.example.projectapp.database.interfaces.DrinkDao;

import java.util.List;

public class DrinkRepo {
    private DrinkDao drinkDao;
    private LiveData<List<Drinks>> drinks;
    private static DrinkRepo instance;

    public DrinkRepo(Application application) {
        MenuDatabase database = MenuDatabase.getInstance(application);
        drinkDao = database.drinkDAo();
        drinks = drinkDao.getAllDrinkies();

    }

    public static synchronized DrinkRepo getInstance(Application application) {
        if (instance == null) {
            instance = new DrinkRepo(application);
        }
        return instance;
    }

    public void insert(Drinks drink) {
        new InsertDrinkAsyncTAsk(drinkDao).execute(drink);
    }

    public void update(Drinks drink) {
        new UpdateDrinkAsyncTask(drinkDao).execute(drink);
    }

    public void delete(Drinks drink) {
        new DeleteDrinkAsynkTask(drinkDao).execute(drink);
    }

    public void deleteAllDrinks() {
        new DeleteAllAsyncTask(drinkDao).execute();
    }

    public LiveData<List<Drinks>> getDrinks() {
        return drinks;
    }

    private static class InsertDrinkAsyncTAsk extends AsyncTask<Drinks, Void, Void> {
        private DrinkDao drinkDao;

        private InsertDrinkAsyncTAsk(DrinkDao drinkDao) {
            this.drinkDao = drinkDao;
        }

        @Override
        protected Void doInBackground(Drinks... drinks) {

            drinkDao.insert(drinks[0]);
            return null;
        }
    }

    private static class UpdateDrinkAsyncTask extends AsyncTask<Drinks, Void, Void> {
        private DrinkDao drinkDao;

        private UpdateDrinkAsyncTask(DrinkDao drinkDao) {
            this.drinkDao = drinkDao;
        }

        @Override
        protected Void doInBackground(Drinks... drink) {

            drinkDao.update(drink[0]);
            return null;
        }
    }

    private static class DeleteDrinkAsynkTask extends AsyncTask<Drinks, Void, Void> {
        private DrinkDao drinkdao;

        private DeleteDrinkAsynkTask(DrinkDao drinkdao) {
            this.drinkdao = drinkdao;
        }

        @Override
        protected Void doInBackground(Drinks... drinks) {

            drinkdao.delete(drinks[0]);
            return null;
        }
    }

    private static class DeleteAllAsyncTask extends AsyncTask<Void, Void, Void> {
        private DrinkDao drinkDao;

        private DeleteAllAsyncTask(DrinkDao drinkDao) {
            this.drinkDao = drinkDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {

            drinkDao.deleteAllDrinks();
            return null;
        }
    }
}
