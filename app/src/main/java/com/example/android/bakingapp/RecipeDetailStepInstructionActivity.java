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
            if(intent.hasExtra("recipeName"))
                setTitle(intent.getStringExtra("recipeName"));
            if(intent.hasExtra("currentStepData")) {
                StepData data = getIntent().getParcelableExtra("currentStepData");
                Bundle recipeDetailSteps = new Bundle();
                recipeDetailSteps.putParcelable("currentStepData",data);
                if(intent.hasExtra("numberOfSteps"))
                    recipeDetailSteps.putInt("numberOfSteps",getIntent().getIntExtra("numberOfSteps",0));
                RecipeDetailStepInstructionActivityFragment recipeDetailStepInstructionActivityFragment = new RecipeDetailStepInstructionActivityFragment();
                recipeDetailStepInstructionActivityFragment.setArguments(recipeDetailSteps);
                getSupportFragmentManager().beginTransaction().replace(R.id.activity_recipe_step_detail_container,recipeDetailStepInstructionActivityFragment).commit();
            }
            if(intent.hasExtra("recipeData")) {
                mRecipeData = getIntent().getParcelableExtra("recipeData");
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
    public void onClickedDirectionButton(int stepId) {
        StepData data = mRecipeData.getStepData().get(stepId);
        Bundle recipeDetailSteps = new Bundle();
        recipeDetailSteps.putParcelable("currentStepData",data);
        recipeDetailSteps.putInt("numberOfSteps",mRecipeData.getStepData().size());
        RecipeDetailStepInstructionActivityFragment recipeDetailStepInstructionActivityFragment = new RecipeDetailStepInstructionActivityFragment();
        recipeDetailStepInstructionActivityFragment.setArguments(recipeDetailSteps);
        getSupportFragmentManager().beginTransaction().replace(R.id.activity_recipe_step_detail_container,recipeDetailStepInstructionActivityFragment).commit();
    }
}
