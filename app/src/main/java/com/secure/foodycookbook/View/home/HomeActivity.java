package com.secure.foodycookbook.View.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.secure.foodycookbook.R;
import com.secure.foodycookbook.Utils.Utils;
import com.secure.foodycookbook.View.Adapter.RecyclerViewHomeAdapter;
import com.secure.foodycookbook.View.Adapter.ViewPagerHeaderAdapter;
import com.secure.foodycookbook.Model.Categories;
import com.secure.foodycookbook.Model.Meals;
import com.secure.foodycookbook.View.category.CategoryActivity;
import com.secure.foodycookbook.View.detail.DetailActivity;

import java.io.Serializable;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeActivity extends AppCompatActivity implements HomeView{

    public static final String EXTRA_CATEGORY = "category";
    public static final String EXTRA_POSITION = "position";
    public static final String EXTRA_DETAIL = "detail";

    @BindView(R.id.view_pager_header)
    ViewPager viewPagerMeal;
    @BindView(R.id.recycler_category)
    RecyclerView recyclerViewCategory;

    HomePresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);

        presenter = new HomePresenter(this);
        presenter.getMeals();
        presenter.getCategories();
    }

    @Override
    public void showLoading() {
        findViewById(R.id.shimmer_meal).setVisibility(View.VISIBLE);
        findViewById(R.id.shimmer_category).setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        findViewById(R.id.shimmer_meal).setVisibility(View.GONE);
        findViewById(R.id.shimmer_category).setVisibility(View.GONE);
    }

    @Override
    public void setMeal(List<Meals.Meal> meal) {
        ViewPagerHeaderAdapter headerAdapter = new ViewPagerHeaderAdapter(meal, this);
        viewPagerMeal.setAdapter(headerAdapter);
        viewPagerMeal.setPadding(40, 0, 40, 0);
        headerAdapter.notifyDataSetChanged();

        headerAdapter.setOnItemClickListener(new ViewPagerHeaderAdapter.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                TextView mealName = view.findViewById(R.id.mealName);
                Intent intent = new Intent(getApplicationContext(), DetailActivity.class);
                intent.putExtra(EXTRA_DETAIL, mealName.getText().toString());
                startActivity(intent);
            }
        });
    }

    @Override
    public void setCategory(final List<Categories.Category> category) {
        RecyclerViewHomeAdapter homeAdapter = new RecyclerViewHomeAdapter(category, this);
        recyclerViewCategory.setAdapter(homeAdapter);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 3,
                GridLayoutManager.VERTICAL, false);
        recyclerViewCategory.setLayoutManager(layoutManager);
        recyclerViewCategory.setNestedScrollingEnabled(true);
        homeAdapter.notifyDataSetChanged();

        homeAdapter.setOnItemClickListener(new RecyclerViewHomeAdapter.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                Intent intent = new Intent(HomeActivity.this, CategoryActivity.class);
                intent.putExtra(EXTRA_CATEGORY, (Serializable) category);
                intent.putExtra(EXTRA_POSITION, position);
                HomeActivity.this.startActivity(intent);
            }
        });
    }

    @Override
    public void onErrorLoading(String message) {
        Utils.showDialogMessage(this, "Title", message);
    }
}