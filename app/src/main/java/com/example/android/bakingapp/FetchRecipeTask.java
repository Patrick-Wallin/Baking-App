package com.example.android.bakingapp;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;

import com.example.android.bakingapp.adapter.RecipeAdapter;
import com.example.android.bakingapp.data.RecipeData;
import com.example.android.bakingapp.utilities.NetworkUtils;
import com.example.android.bakingapp.utilities.OpenRecipeJsonUtils;

import java.io.IOException;
import java.net.URL;
import java.util.List;

/**
 * Created by piwal on 5/13/2017.
 */

public class FetchRecipeTask extends AsyncTask<Void, Void, List<RecipeData>> {
    private String LOG_TAG = FetchRecipeTask.class.getSimpleName();

    private RecipeAdapter mRecipeAdapter;
    private Context mContext;
    private View mRootView;

    public FetchRecipeTask(RecipeAdapter recipeAdapter, Context context, View rootView) {
        mRecipeAdapter = recipeAdapter;
        mContext = context;
        mRootView = rootView;
    }


    @Override
    protected List<RecipeData> doInBackground(Void... params) {
        NetworkUtils networkUtils = new NetworkUtils(mContext);
        URL recipeRequestURL = networkUtils.buildUrl();

        if(recipeRequestURL != null) {
            try {
                Log.d(LOG_TAG,recipeRequestURL.toString());
                String jsonRecipeResponse = networkUtils.getResponseFromHttpUrl(recipeRequestURL);
                Log.d(LOG_TAG,"json: " + jsonRecipeResponse);
                return OpenRecipeJsonUtils.getRecipeDataFromJson(jsonRecipeResponse);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


        return null;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(List<RecipeData> recipeData) {
        //super.onPostExecute(recipeData);
        if(recipeData != null) {
            mRecipeAdapter.setRecipeData(recipeData);
            Log.d(LOG_TAG,"we got some!");
        }else {
            Log.d(LOG_TAG,"we did not get some");
        }
    }
}
