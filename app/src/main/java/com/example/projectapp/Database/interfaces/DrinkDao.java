package com.example.projectapp.database.interfaces;
import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;


import com.example.projectapp.database.Drinks;

import java.util.List;

@Dao
public interface DrinkDao {
    @Insert
    void insert(Drinks drink);
    @Update
    void update(Drinks drink);
    @Delete
    void delete(Drinks drink);
    @Query("DELETE FROM drink_table" )
    void deleteAllDrinks();


    @Query("SELECT * FROM drink_table ORDER BY price DESC")
    LiveData<List<Drinks>> getAllDrinkies();
}
