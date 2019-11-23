package com.example.projectapp;

import com.example.projectapp.Database.FoodDatabase;
import com.example.projectapp.dao.FoodDao;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */


public class ExampleUnitTest
{
    public     static FoodDao foodDao;
    private FoodDatabase db;
    @Before
    public void  setUp(){

    }
    @Test
    public  void testAddFood(){

        foodDao.insert(new Food("Chicken", "yummy chicken", 200));

    }
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }
}