package com.secure.foodycookbook.View.detail;

import com.secure.foodycookbook.Model.Meals;

public interface DetailView {
    void showLoading();
    void hideLoading();
    void setMeal(Meals.Meal meal);
    void onErrorLoading(String message);

}
