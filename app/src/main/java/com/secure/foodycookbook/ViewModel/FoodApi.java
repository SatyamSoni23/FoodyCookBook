package com.secure.foodycookbook.ViewModel;

import com.secure.foodycookbook.Model.Categories;
import com.secure.foodycookbook.Model.Meals;
import com.secure.foodycookbook.Model.Search;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface FoodApi {

    @GET("random.php")
    Call<Meals> getMeal();

    @GET("categories.php")
    Call<Categories> getCategories();

    @GET("filter.php")
    Call<Meals> getMealByCategory(@Query("c") String category);

    @GET("search.php")
    Call<Meals> getMealByName(@Query("s") String mealName);
}
