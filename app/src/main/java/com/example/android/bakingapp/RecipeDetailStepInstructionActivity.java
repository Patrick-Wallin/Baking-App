package com.example.android.bakingapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.android.bakingapp.data.RecipeData;
import com.example.android.bakingapp.data.StepData;

/**
 * Created by piwal on 5/16/2017.
 */

public class RecipeDetailStepInstructionActivity extends AppCompatActivity {
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
                RecipeDetailStepInstructionActivityFragment recipeDetailStepInstructionActivityFragment = new RecipeDetailStepInstructionActivityFragment();
                recipeDetailStepInstructionActivityFragment.setArguments(recipeDetailSteps);
                getSupportFragmentManager().beginTransaction().add(R.id.activity_recipe_step_detail_container,recipeDetailStepInstructionActivityFragment).commit();
            }
        }


    }
}
