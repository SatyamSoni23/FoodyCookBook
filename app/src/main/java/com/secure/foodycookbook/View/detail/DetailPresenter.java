package com.secure.foodycookbook.View.detail;

import androidx.annotation.NonNull;

import com.secure.foodycookbook.Model.Meals;
import com.secure.foodycookbook.Utils.Utils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailPresenter {
    private DetailView view;

    public DetailPresenter(DetailView view) {
        this.view = view;
    }

    void getMealById(String mealName) {
        view.showLoading();
        Utils.getApi().getMealByName(mealName)
                .enqueue(new Callback<Meals>() {
                    @Override
                    public void onResponse(@NonNull Call<Meals> call, @NonNull Response<Meals> response) {
                        view.hideLoading();
                        if(response.isSuccessful() && response.body() != null){
                            view.setMeal(response.body().getMeals().get(0));
                        }
                        else{
                            view.onErrorLoading(response.message());
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<Meals> call, @NonNull Throwable t) {
                        view.hideLoading();
                        view.onErrorLoading(t.getLocalizedMessage());
                    }
                });
    }
}

