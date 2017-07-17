package com.example.android.bakingapp.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.android.bakingapp.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by piwal on 5/15/2017.
 */

public class RecipeDetailStepDescriptionsAdapterViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.step_description_text_view) public TextView mStepDescriptionTextView;

    public RecipeDetailStepDescriptionsAdapterViewHolder(View itemView) {
        super(itemView);

        ButterKnife.bind(this,itemView);
    }
}
