package com.example.android.bakingapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.android.bakingapp.data.RecipeData;

/**
 * Created by piwal on 5/14/2017.
 */

public class RecipeDetailActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_detail);

        if(savedInstanceState == null) {
            Intent intent = getIntent();
            if(intent.hasExtra("currentRecipe")) {
                RecipeData data = getIntent().getParcelableExtra("currentRecipe");
                setTitle(data.getName());
                Bundle recipeDetail = new Bundle();
                recipeDetail.putParcelable("currentRecipe",data);
                RecipeDetailActivityFragment recipeDetailActivityFragment = new RecipeDetailActivityFragment();
                recipeDetailActivityFragment.setArguments(recipeDetail);
                getSupportFragmentManager().beginTransaction().add(R.id.activity_recipe_detail_container,recipeDetailActivityFragment).commit();
            }
        }


    }
}
