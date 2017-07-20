package com.example.android.bakingapp.adapter;

import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.bakingapp.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by piwal on 5/10/2017.
 */

public class RecipeAdapterViewHolder extends RecyclerView.ViewHolder {
    @Nullable
    @BindView(R.id.recipe_card_image_view) public ImageView mRecipeImageView;
    @BindView(R.id.name_text_view) public TextView mRecipeNameTextView;
    @BindView(R.id.servings_text_view) public TextView mServingsTextView;

    public RecipeAdapterViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this,itemView);
    }
}
