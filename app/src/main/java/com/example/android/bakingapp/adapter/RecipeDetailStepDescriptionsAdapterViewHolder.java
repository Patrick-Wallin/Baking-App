package com.example.android.bakingapp.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.android.bakingapp.R;

/**
 * Created by piwal on 5/15/2017.
 */

public class RecipeDetailStepDescriptionsAdapterViewHolder extends RecyclerView.ViewHolder {
    final TextView mStepDescriptionTextView;

    public RecipeDetailStepDescriptionsAdapterViewHolder(View itemView) {
        super(itemView);

        mStepDescriptionTextView = (TextView)itemView.findViewById(R.id.step_description_text_view);
    }
}
