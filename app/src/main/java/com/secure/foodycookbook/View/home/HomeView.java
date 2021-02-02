package com.secure.foodycookbook.View.home;

import com.secure.foodycookbook.Model.Categories;
import com.secure.foodycookbook.Model.Meals;

import java.util.List;

public interface HomeView {
    void showLoading();
    void hideLoading();
    void setMeal(List<Meals.Meal> meal);
    void setCategory(List<Categories.Category> category);
    void onErrorLoading(String message);
}
