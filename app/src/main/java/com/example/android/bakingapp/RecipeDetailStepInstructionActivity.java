package com.example.android.bakingapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.example.android.bakingapp.data.RecipeData;
import com.example.android.bakingapp.data.StepData;

/**
 * Created by piwal on 5/16/2017.
 */

public class RecipeDetailStepInstructionActivity extends AppCompatActivity implements StepDirectionListener {
    private RecipeData mRecipeData;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_step_detail);


        if(savedInstanceState == null) {
            Intent intent = getIntent();
            if(intent.hasExtra(getString(R.string.recipe_name)))
                setTitle(intent.getStringExtra(getString(R.string.recipe_name)));
            if(intent.hasExtra(getString(R.string.current_step_data))) {
                StepData data = getIntent().getParcelableExtra(getString(R.string.current_step_data));
                Bundle recipeDetailSteps = new Bundle();
                recipeDetailSteps.putParcelable(getString(R.string.current_step_data),data);
                if(intent.hasExtra(getString(R.string.number_of_steps)))
                    recipeDetailSteps.putInt(getString(R.string.number_of_steps),getIntent().getIntExtra(getString(R.string.number_of_steps),0));
                RecipeDetailStepInstructionActivityFragment recipeDetailStepInstructionActivityFragment = new RecipeDetailStepInstructionActivityFragment();
                recipeDetailStepInstructionActivityFragment.setArguments(recipeDetailSteps);
                getSupportFragmentManager().beginTransaction().replace(R.id.activity_recipe_step_detail_container,recipeDetailStepInstructionActivityFragment).commit();
            }
            if(intent.hasExtra(getString(R.string.recipe_data))) {
                mRecipeData = getIntent().getParcelableExtra(getString(R.string.recipe_data));
            }
        }


    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if(savedInstanceState != null) {
            mRecipeData = savedInstanceState.getParcelable(getString(R.string.recipe_data));
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable(getString(R.string.recipe_data),mRecipeData);

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
    public void onClickedDirectionButton(int stepId) {
        StepData data = mRecipeData.getStepData().get(stepId);
        Bundle recipeDetailSteps = new Bundle();
        recipeDetailSteps.putParcelable(getString(R.string.current_step_data),data);
        recipeDetailSteps.putInt(getString(R.string.number_of_steps),mRecipeData.getStepData().size());
        RecipeDetailStepInstructionActivityFragment recipeDetailStepInstructionActivityFragment = new RecipeDetailStepInstructionActivityFragment();
        recipeDetailStepInstructionActivityFragment.setArguments(recipeDetailSteps);
        getSupportFragmentManager().beginTransaction().replace(R.id.activity_recipe_step_detail_container,recipeDetailStepInstructionActivityFragment).commit();
    }
}
