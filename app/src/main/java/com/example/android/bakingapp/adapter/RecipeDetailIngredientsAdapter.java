package com.example.android.bakingapp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android.bakingapp.R;
import com.example.android.bakingapp.data.IngredientData;
import com.example.android.bakingapp.data.RecipeData;

import java.util.List;

/**
 * Created by piwal on 5/14/2017.
 */

public class RecipeDetailIngredientsAdapter extends RecyclerView.Adapter<RecipeDetailIngredientsAdapterViewHolder> {
    private RecipeData mRecipeData;
    private Context mContext;

    public RecipeDetailIngredientsAdapter(RecipeData recipeData, Context context) {
        mRecipeData = recipeData;
        mContext = context;
    }

    public void setRecipeData(RecipeData recipeData) {
        mRecipeData = recipeData;
        notifyDataSetChanged();
    }

    @Override
    public RecipeDetailIngredientsAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        int layoutId;
        layoutId = R.layout.ingredient_item;
        View view = LayoutInflater.from(parent.getContext()).inflate(layoutId,parent,false);
        return new RecipeDetailIngredientsAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecipeDetailIngredientsAdapterViewHolder holder, int position) {
        if(mRecipeData != null) {
            List<IngredientData> data = mRecipeData.getIngredientData();
            StringBuilder description = new StringBuilder();
            description.append("\u25FC");
            description.append("\t");
            description.append(data.get(position).getQuantity());
            description.append(" ");
            description.append(data.get(position).getMeasure());
            description.append(" ");
            description.append(data.get(position).getIngredient());
            holder.mIngredientTextView.setText(description);
        }

    }

    @Override
    public int getItemCount() {
        return (mRecipeData.getIngredientData() != null ? mRecipeData.getIngredientData().size() : 0);
    }
}
