package com.example.android.bakingapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android.bakingapp.R;
import com.example.android.bakingapp.RecipeDetailActivity;
import com.example.android.bakingapp.data.RecipeData;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by piwal on 5/10/2017.
 */

public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapterViewHolder> {
    private ArrayList<RecipeData> mRecipeData;
    private Context mContext;

    public RecipeAdapter(ArrayList<RecipeData> recipeData, Context context) {
        mRecipeData = recipeData;
        mContext = context;
    }

    @Override
    public RecipeAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        int layoutId;

        layoutId = R.layout.main_recipe_card_view;

        View view = LayoutInflater.from(mContext).inflate(layoutId,parent,false);

        return new RecipeAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecipeAdapterViewHolder holder, int position) {
        Log.d("onbindviewholder!","");
        if(position >= 0 && !mRecipeData.isEmpty() && mRecipeData.size() > position) {
            final RecipeData recipe = mRecipeData.get(position);

            holder.mRecipeNameTextView.setText(recipe.getName());
            holder.mServingsTextView.setText(recipe.getServings());

            String imageName = recipe.getImage().trim();

            if(imageName.isEmpty())
                holder.mRecipeImageView.setImageResource(R.drawable.default_no_image_found);
            else {
                Picasso.with(mContext)
                        .load(imageName)
                        .placeholder(R.drawable.default_no_image_found)
                        .error(R.drawable.default_no_image_found)
                        .into(holder.mRecipeImageView);
            }

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //Bundle recipeDetail = new Bundle();
                    //recipeDetail.putParcelable("currentRecipe",recipe);
                    Intent intent = new Intent(mContext, RecipeDetailActivity.class);
                    intent.putExtra("currentRecipe",recipe);
                    mContext.startActivity(intent);
                }
            });
        }

    }

    @Override
    public int getItemCount() {
        if(mRecipeData != null)
            return mRecipeData.size();
        else
            return 0;
    }

    public void setRecipeData(List<RecipeData> recipeData) {
        mRecipeData = (ArrayList)recipeData;
        notifyDataSetChanged();
    }
}
