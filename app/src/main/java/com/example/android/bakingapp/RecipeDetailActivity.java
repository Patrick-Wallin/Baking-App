package com.example.android.bakingapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

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
            if(intent.hasExtra(getString(R.string.current_receipe))) {
                RecipeData data = getIntent().getParcelableExtra(getString(R.string.current_receipe));
                setTitle(data.getName());
                Bundle recipeDetail = new Bundle();
                recipeDetail.putParcelable(getString(R.string.current_receipe),data);
                RecipeDetailActivityFragment recipeDetailActivityFragment = new RecipeDetailActivityFragment();
                recipeDetailActivityFragment.setArguments(recipeDetail);
                getSupportFragmentManager().beginTransaction().replace(R.id.activity_recipe_detail_container,recipeDetailActivityFragment).commit();
            }
        }


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);

    }
}
