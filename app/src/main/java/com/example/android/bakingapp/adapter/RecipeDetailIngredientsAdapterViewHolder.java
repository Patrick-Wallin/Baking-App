package com.example.android.bakingapp.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.android.bakingapp.R;

import org.w3c.dom.Text;

/**
 * Created by piwal on 5/14/2017.
 */

public class RecipeDetailIngredientsAdapterViewHolder extends RecyclerView.ViewHolder {
    final TextView mIngredientTextView;

    public RecipeDetailIngredientsAdapterViewHolder(View itemView) {
        super(itemView);

        mIngredientTextView = (TextView) itemView.findViewById(R.id.ingredient_info_text_view);


    }
}
