package com.example.android.bakingapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;

import com.example.android.bakingapp.data.RecipeData;
import com.example.android.bakingapp.data.StepData;

import java.util.List;

/**
 * Created by piwal on 5/14/2017.
 */

public class RecipeDetailActivity extends AppCompatActivity implements StepInstructionListener {
    private boolean mLandscapeInTablet = false;
    private RecipeDetailStepInstructionActivityFragment mRecipeDetailStepInstructionActivityFragment;
    private RecipeData mRecipeData;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_detail);

        mLandscapeInTablet = false;
        Intent intent = getIntent();
        if(intent.hasExtra(getString(R.string.current_receipe))) {
            mRecipeData = getIntent().getParcelableExtra(getString(R.string.current_receipe));
            setTitle(mRecipeData.getName());
            Bundle recipeDetail = new Bundle();
            recipeDetail.putParcelable(getString(R.string.current_receipe),mRecipeData);
            RecipeDetailActivityFragment recipeDetailActivityFragment = new RecipeDetailActivityFragment();
            recipeDetailActivityFragment.setArguments(recipeDetail);
            getSupportFragmentManager().beginTransaction().replace(R.id.activity_recipe_detail_container,recipeDetailActivityFragment).commit();

            if(findViewById(R.id.activity_recipe_step_detail_container) != null) {
                mLandscapeInTablet = true;
                RecipeDetailStepInstructionActivityFragment recipeDetailStepInstructionActivityFragment = new RecipeDetailStepInstructionActivityFragment();
                List<StepData> stepDataList = mRecipeData.getStepData();
                Bundle recipeDetailSteps = new Bundle();
                recipeDetailSteps.putParcelable(getString(R.string.current_step_data),(stepDataList.size() > 0 ? stepDataList.get(0) : null));
                recipeDetailSteps.putInt(getString(R.string.number_of_steps),stepDataList.size());
                recipeDetailSteps.putParcelable(getString(R.string.recipe_data),mRecipeData);
                recipeDetailStepInstructionActivityFragment.setArguments(recipeDetailSteps);
                getSupportFragmentManager().beginTransaction().replace(R.id.activity_recipe_step_detail_container,recipeDetailStepInstructionActivityFragment).commit();
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

    @Override
    public void onClicked(String name, StepData stepData, int numberOfSteps) {
        if(mLandscapeInTablet == true) {
            RecipeDetailStepInstructionActivityFragment recipeDetailStepInstructionActivityFragment = new RecipeDetailStepInstructionActivityFragment();
            Bundle recipeDetailSteps = new Bundle();
            recipeDetailSteps.putParcelable(getString(R.string.current_step_data),stepData);
            recipeDetailSteps.putInt(getString(R.string.number_of_steps),numberOfSteps);
            recipeDetailSteps.putParcelable(getString(R.string.recipe_data),mRecipeData);
            recipeDetailStepInstructionActivityFragment.setArguments(recipeDetailSteps);
            getSupportFragmentManager().beginTransaction().replace(R.id.activity_recipe_step_detail_container,recipeDetailStepInstructionActivityFragment).commit();
        }else {
            Intent intent = new Intent(this, RecipeDetailStepInstructionActivity.class);
            intent.putExtra(getString(R.string.recipe_name),name);
            intent.putExtra(getString(R.string.current_step_data), stepData);
            intent.putExtra(getString(R.string.number_of_steps),numberOfSteps);
            intent.putExtra(getString(R.string.recipe_data),mRecipeData);
            this.startActivity(intent);
        }
    }
}
