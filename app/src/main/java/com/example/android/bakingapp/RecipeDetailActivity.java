package com.example.android.bakingapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.android.bakingapp.adapter.RecipeDetailStepDescriptionsAdapter;
import com.example.android.bakingapp.data.RecipeData;
import com.example.android.bakingapp.data.StepData;

import java.util.List;

/**
 * Created by piwal on 5/14/2017.
 */

public class RecipeDetailActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_detail_fragment);

        if(savedInstanceState == null) {
            Intent intent = getIntent();
            if(intent.hasExtra("currentRecipe")) {
                RecipeData data = getIntent().getParcelableExtra("currentRecipe");
                StepData stepData = null;
                if(data != null) {
                    List<StepData> listStepData = data.getStepData();
                    if(listStepData != null && !listStepData.isEmpty()) {
                        stepData = listStepData.get(0);
                    }
                }
                setTitle(data.getName());
                Bundle recipeDetail = new Bundle();
                recipeDetail.putParcelable("currentRecipe",data);

                boolean isThisInLandscape = getResources().getBoolean(R.bool.isItInLandscape);
                boolean isThisInPhone = getResources().getBoolean(R.bool.isItInPhone);

                RecipeDetailActivityFragment recipeDetailActivityFragment = new RecipeDetailActivityFragment();
                recipeDetailActivityFragment.setArguments(recipeDetail);

                if(isThisInLandscape && !isThisInPhone) {
                    Bundle recipeSteps = new Bundle();
                    recipeSteps.putParcelable("currentStepData",stepData);
                    RecipeDetailStepInstructionActivityFragment recipeDetailStepInstructionActivityFragment = new RecipeDetailStepInstructionActivityFragment();
                    recipeDetailStepInstructionActivityFragment.setArguments(recipeSteps);

                    FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

                    transaction.add(R.id.recipe_details_fragment, recipeDetailActivityFragment);
                    transaction.add(R.id.recipe_steps_fragment, recipeDetailStepInstructionActivityFragment);

                    transaction.commit();


                }else {
                    //RecipeDetailActivityFragment recipeDetailActivityFragment = new RecipeDetailActivityFragment();
                    //recipeDetailActivityFragment.setArguments(recipeDetail);
                    //getSupportFragmentManager().beginTransaction().add(R.id.activity_recipe_detail_container, recipeDetailActivityFragment).commit();
                    getSupportFragmentManager().beginTransaction().add(R.id.recipe_details_fragment, recipeDetailActivityFragment).commit();
                }
            }
        }


    }


}
