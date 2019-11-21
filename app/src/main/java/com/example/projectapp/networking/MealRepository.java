package com.example.projectapp.networking;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MealRepository {
    private static MealRepository instance;
    private MutableLiveData<Meal> mealMutableLiveData;



    private MealRepository() {
            mealMutableLiveData = new MutableLiveData<>();
        }

        public static synchronized MealRepository getInstance() {
            if (instance == null) {
                instance = new MealRepository();
            }
            return instance;
        }

        public LiveData<Meal> getMeal() {
            return mealMutableLiveData;
        }

        public void updateMeal() {
            InspirationApi inspirationApi = ServiceGenerator.getInspirationApi();
            Call<MealRespnse> call = inspirationApi.getMeal();
            call.enqueue(new Callback<MealRespnse>() {
                @Override
                public void onResponse(Call<MealRespnse> call, Response<MealRespnse> response) {
                    if (response.code() == 200) {
                        mealMutableLiveData.setValue(response.body().getMeal());
                    }
                }

                @Override
                public void onFailure(Call<MealRespnse> call, Throwable t) {
                    Log.i("Retrofit", "Something went wrong :(");
                }
            });
        }
}
