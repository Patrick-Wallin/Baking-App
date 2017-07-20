package com.example.android.bakingapp;

import android.content.Context;
import android.content.Intent;
import android.os.Binder;
import android.widget.AdapterView;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import com.example.android.bakingapp.data.IngredientData;
import com.example.android.bakingapp.data.SharedPreference;

import java.util.List;

/**
 * Created by piwal on 7/18/2017.
 */

public class RecipeWidgetRemoteViewsFactory implements RemoteViewsService.RemoteViewsFactory {
    private static final String TAG = "RecipeWidgetRemoteViewsFactory";

    private List<IngredientData> mRecipeIngredientData = null;
    private String mRecipeName = "";
    private String mRecipeId = "";
    private Context mContext;

    public RecipeWidgetRemoteViewsFactory(Context context) {
        mContext = context;
    }

    @Override
    public void onCreate() {

    }

    @Override
    public void onDataSetChanged() {
        mRecipeIngredientData = null;

        final long identityToken = Binder.clearCallingIdentity();

        SharedPreference sharedPreference = new SharedPreference();
        mRecipeIngredientData = sharedPreference.getIngredientFavorite(mContext);
        mRecipeName = sharedPreference.getRecipeNameFavorite(mContext);
        mRecipeId = sharedPreference.getRecipeIdFavorite(mContext);

        Binder.restoreCallingIdentity(identityToken);
    }

    @Override
    public void onDestroy() {
        mRecipeIngredientData = null;
    }

    @Override
    public int getCount() {
        return (mRecipeIngredientData == null ? 0 : mRecipeIngredientData.size());
    }

    @Override
    public RemoteViews getViewAt(int position) {
        if(position == AdapterView.INVALID_POSITION ||
                mRecipeIngredientData == null || mRecipeIngredientData.size() < position) {
            return null;
        }

        IngredientData ingredientData = mRecipeIngredientData.get(position);

        RemoteViews view = new RemoteViews(mContext.getPackageName(), R.layout.recipe_widget_item);
        view.setTextViewText(R.id.quantity_widget_text_view,ingredientData.getQuantity());
        view.setTextViewText(R.id.measure_widget_text_view,ingredientData.getMeasure());
        view.setTextViewText(R.id.ingredient_widget_text_view,ingredientData.getIngredient());

        Intent intent = new Intent();
        intent.putExtra(mContext.getString(R.string.id),mRecipeId);
        view.setOnClickFillInIntent(R.id.widget_row_item,intent);

        return view;
    }

    @Override
    public RemoteViews getLoadingView() {
        return null;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }
}
