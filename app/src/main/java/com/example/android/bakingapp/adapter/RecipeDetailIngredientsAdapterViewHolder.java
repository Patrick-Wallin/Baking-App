package com.example.android.bakingapp.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.android.bakingapp.R;

import org.w3c.dom.Text;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by piwal on 5/14/2017.
 */

public class RecipeDetailIngredientsAdapterViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.ingredient_info_text_view) public TextView mIngredientTextView;

    public RecipeDetailIngredientsAdapterViewHolder(View itemView) {
        super(itemView);

        ButterKnife.bind(this,itemView);
    }
}
