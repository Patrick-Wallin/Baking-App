package com.example.android.bakingapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android.bakingapp.R;
import com.example.android.bakingapp.RecipeDetailStepInstructionActivity;
import com.example.android.bakingapp.data.IngredientData;
import com.example.android.bakingapp.data.RecipeData;
import com.example.android.bakingapp.data.StepData;

import java.util.List;

/**
 * Created by piwal on 5/15/2017.
 */

public class RecipeDetailStepDescriptionsAdapter extends RecyclerView.Adapter<RecipeDetailStepDescriptionsAdapterViewHolder> {
    private RecipeData mRecipeData;
    private Context mContext;

    public RecipeDetailStepDescriptionsAdapter(RecipeData recipeData, Context context) {
        mRecipeData = recipeData;
        mContext = context;
    }

    public void setRecipeData(RecipeData recipeData) {
        mRecipeData = recipeData;
        notifyDataSetChanged();
    }

    @Override
    public RecipeDetailStepDescriptionsAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        int layoutId;

        layoutId = R.layout.step_description_item;

        View view = LayoutInflater.from(parent.getContext()).inflate(layoutId,parent,false);

        return new RecipeDetailStepDescriptionsAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecipeDetailStepDescriptionsAdapterViewHolder holder, final int position) {
        if(mRecipeData != null) {
            final List<StepData> data = mRecipeData.getStepData();
            StringBuilder description = new StringBuilder();
            description.append(data.get(position).getShortDescription());
            holder.mStepDescriptionTextView.setText(description);

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //Bundle recipeDetail = new Bundle();
                    //recipeDetail.putParcelable("currentStepData", data.get(position));
                    Intent intent = new Intent(mContext, RecipeDetailStepInstructionActivity.class);
                    intent.putExtra("recipeName",mRecipeData.getName());
                    intent.putExtra("currentStepData", data.get(position));
                    mContext.startActivity(intent);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return (mRecipeData.getStepData() != null ? mRecipeData.getStepData().size() : 0);
    }
}
