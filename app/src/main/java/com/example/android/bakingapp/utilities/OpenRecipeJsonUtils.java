package com.example.android.bakingapp.utilities;

import android.util.Log;

import com.example.android.bakingapp.data.RecipeData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by piwal on 5/11/2017.
 */

public class OpenRecipeJsonUtils {
    private static String LOG_TAG = OpenRecipeJsonUtils.class.getSimpleName();

    public static List<RecipeData> getRecipeDataFromJson(String recipeJson) {
        List<RecipeData> parsedRecipeData = new ArrayList<>();

        try {
            //JSONObject jsonRecipe = new JSONObject(recipeJson);
            JSONArray jsonRecipe = new JSONArray(recipeJson);
            if(jsonRecipe != null && jsonRecipe.length() > 0) {
                Log.d(LOG_TAG,"Json starts with Array!");
                for(int i = 0; i < jsonRecipe.length(); i++) {
                    if(jsonRecipe.get(i) instanceof JSONObject) {
                        Log.d(LOG_TAG,"This is an object.");
                        RecipeData data = new RecipeData(jsonRecipe.getJSONObject(i));
                        parsedRecipeData.add(data);
                    }
                }

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return parsedRecipeData;

    }
}
