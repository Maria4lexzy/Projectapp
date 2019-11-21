package com.example.projectapp.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;


import com.example.projectapp.database.interfaces.DrinkDao;
import com.example.projectapp.database.interfaces.FoodDao;

@Database(entities = {Food.class, Drinks.class}, version = 2 , exportSchema = false)
public abstract class MenuDatabase extends RoomDatabase {
    private static MenuDatabase instance;

    public abstract DrinkDao drinkDAo();
    public abstract FoodDao foodDao();
    public static synchronized MenuDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(), MenuDatabase.class, "menu_database").build();
        }
        return instance;
    }

   /* private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbAsyncTask(instance).execute();
        }
    };
    private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void> {
        private FoodDao foodao;

        private PopulateDbAsyncTask(MenuDatabase db) {
            foodao = db.foodDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            foodao.insert(new Food("Chicken", "yummy chicken", 200));
            foodao.insert(new Food("Barbecue thingy", "yummy barbecue", 250));
            foodao.insert(new Food("Burger", "yummy burger", 110));
            foodao.insert(new Food("Fries", "yummy fries", 70));
            // foodao.insert(new Food("Fries", "yummy fries", 70, "https://poslavu.dk/ImageHandler.ashx?id=16335"));
            //foodao.insert(new Food("Chicken", "yummy chicken", 200, "https://poslavu.dk/ImageHandler.ashx?id=16335"));


            return null;
        }
    }*/
}
