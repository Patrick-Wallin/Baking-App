package com.example.android.bakingapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.Toast;

import com.example.android.bakingapp.adapter.RecipeDetailIngredientsAdapter;
import com.example.android.bakingapp.adapter.RecipeDetailStepDescriptionsAdapter;
import com.example.android.bakingapp.data.RecipeData;

/**
 * Created by piwal on 5/14/2017.
 */

public class RecipeDetailActivityFragment extends Fragment {
    private RecipeData mRecipe;
    private RecyclerView mRecyclerViewIngredients;
    private RecipeDetailIngredientsAdapter mRecipeDetailIngredientsAdapter;
    private RecyclerView mRecyclerViewStepDescriptions;
    private RecipeDetailStepDescriptionsAdapter mRecipeDetailStepDescriptionsAdapter;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getArguments().containsKey("currentRecipe")) {
            mRecipe = getArguments().getParcelable("currentRecipe");
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_recipe_ingredient_direction,container,false);

        mRecyclerViewIngredients = (RecyclerView) rootView.findViewById(R.id.recipe_detail_ingredients_recycler_view);
        mRecyclerViewStepDescriptions = (RecyclerView) rootView.findViewById(R.id.recipe_detail_steps_recycler_view);

        if (mRecipeDetailIngredientsAdapter == null) {
            mRecipeDetailIngredientsAdapter = new RecipeDetailIngredientsAdapter(mRecipe, getActivity());
        } else {
            mRecipeDetailIngredientsAdapter.setRecipeData(mRecipe);
        }
        if (mRecipeDetailStepDescriptionsAdapter == null) {
            mRecipeDetailStepDescriptionsAdapter = new RecipeDetailStepDescriptionsAdapter(mRecipe, getActivity());
        } else {
            mRecipeDetailStepDescriptionsAdapter.setRecipeData(mRecipe);
        }

        mRecyclerViewIngredients.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerViewIngredients.setAdapter(mRecipeDetailIngredientsAdapter);
        mRecyclerViewStepDescriptions.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerViewStepDescriptions.setAdapter(mRecipeDetailStepDescriptionsAdapter);


        //if(rootView.findViewById(R.id.recipe_steps_fragment) != null) {




        //}




            return rootView;
    }



}
