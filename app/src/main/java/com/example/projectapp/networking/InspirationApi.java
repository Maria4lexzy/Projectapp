package com.example.projectapp.networking;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface InspirationApi {
@GET("random.php")
        //"   https://www.themealdb.com/api/json/v1/1/random.php/meal/52767")
Call<MealRespnse> getMeal();



}
