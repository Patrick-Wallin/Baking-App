package com.example.android.bakingapp.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.bakingapp.R;

/**
 * Created by piwal on 5/10/2017.
 */

public class RecipeAdapterViewHolder extends RecyclerView.ViewHolder {
    final ImageView mRecipeImageView;
    final TextView mRecipeNameTextView;
    final TextView mServingsTextView;

    public RecipeAdapterViewHolder(View itemView) {
        super(itemView);

        mRecipeImageView = (ImageView) itemView.findViewById(R.id.recipe_card_image_view);
        mRecipeNameTextView = (TextView) itemView.findViewById(R.id.name_text_view);
        mServingsTextView = (TextView) itemView.findViewById(R.id.servings_text_view);
    }
}
