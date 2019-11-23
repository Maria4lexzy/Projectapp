package com.example.projectapp.networking;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceGenerator {
    private static Retrofit.Builder retrofitBuilder = new Retrofit.Builder()
            .baseUrl("https://www.themealdb.com/api/json/v1/1/")
            .addConverterFactory(GsonConverterFactory.create());

    private static Retrofit retrofit = retrofitBuilder.build();

    private static InspirationApi inspirationApi = retrofit.create(InspirationApi.class);

    public static InspirationApi getMeal() {
        return inspirationApi;
    }
    public  static  InspirationApi getInspirationApi(){
        return  inspirationApi;
    }
}
