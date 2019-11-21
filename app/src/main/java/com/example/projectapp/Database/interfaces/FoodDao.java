package com.example.projectapp.database.interfaces;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;


import com.example.projectapp.database.Food;

import java.util.List;
@Dao
public interface FoodDao {
    @Insert
    void insert(Food food);
    @Update
    void update(Food food);
    @Delete
    void delete(Food food);
    @Query("DELETE FROM food_table" )
    void deleteAllFood();
    @Query("SELECT * FROM food_table ORDER BY price DESC")
    LiveData<List<Food>> getAllFoodies();
}
