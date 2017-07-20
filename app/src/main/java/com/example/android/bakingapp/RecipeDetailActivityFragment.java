package com.example.android.bakingapp;

import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android.bakingapp.adapter.RecipeDetailIngredientsAdapter;
import com.example.android.bakingapp.adapter.RecipeDetailStepDescriptionsAdapter;
import com.example.android.bakingapp.data.RecipeData;
import com.example.android.bakingapp.data.SharedPreference;
import com.example.android.bakingapp.data.StepData;

/**
 * Created by piwal on 5/14/2017.
 */

public class RecipeDetailActivityFragment extends Fragment {
    private RecipeData mRecipe;
    private RecyclerView mRecyclerViewIngredients;
    private RecipeDetailIngredientsAdapter mRecipeDetailIngredientsAdapter;
    private RecyclerView mRecyclerViewStepDescriptions;
    private RecipeDetailStepDescriptionsAdapter mRecipeDetailStepDescriptionsAdapter;
    private Context mContext;

    StepInstructionListener mStepInstructionListenerCallback;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(savedInstanceState != null) {
            mRecipe = savedInstanceState.getParcelable(getString(R.string.recipe_data));
        }else {
            if (getArguments().containsKey(getString(R.string.current_receipe))) {
                mRecipe = getArguments().getParcelable(getString(R.string.current_receipe));
            }
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_recipe_detail_fragment,container,false);

        FloatingActionButton fab = (FloatingActionButton)rootView.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreference sharedPreference = new SharedPreference();
                sharedPreference.saveOrReplaceRecipeFavorite(mContext,mRecipe.getIngredientData(),mRecipe.getName(),mRecipe.getId());

                ComponentName name = new ComponentName(mContext, RecipeWidgetProvider.class);
                int[] ids = AppWidgetManager.getInstance(mContext).getAppWidgetIds(name);

                Intent intent = new Intent(mContext,RecipeWidgetProvider.class);
                intent.setAction(AppWidgetManager.ACTION_APPWIDGET_UPDATE);
                intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS,ids);
                mContext.sendBroadcast(intent);

            }
        });

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

        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if(savedInstanceState != null) {
            mRecipe = savedInstanceState.getParcelable(getString(R.string.recipe_data));
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable(getString(R.string.recipe_data),mRecipe);
    }


}
