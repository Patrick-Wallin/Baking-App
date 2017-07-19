package com.example.android.bakingapp.data;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by piwal on 7/18/2017.
 */

public class SharedPreference {
    public static final String PREFS_NAME = "RECIPE_APP";
    public static final String INGREDIENT_FAVORITE = "Ingredient_Favorite";
    public static final String RECIPE_FAVORITE_NAME = "Recipe_Favorite_Name";
    public static final String RECIPE_FAVORITE_ID = "Recipe_Favorite_Id";

    public SharedPreference() {
        super();
    }

    public void saveOrReplaceRecipeFavorite(Context context,
                                            List<IngredientData> ingredientDataList,
                                            String recipeName,
                                            String recipeId) {
        SharedPreferences settings;
        SharedPreferences.Editor editor;

        settings = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        editor = settings.edit();

        Gson gson = new Gson();
        String jsonFavorites = gson.toJson(ingredientDataList);

        editor.putString(INGREDIENT_FAVORITE, jsonFavorites);
        editor.putString(RECIPE_FAVORITE_NAME,recipeName);
        editor.putString(RECIPE_FAVORITE_ID,recipeId);

        editor.commit();
    }

    public ArrayList<IngredientData> getIngredientFavorite(Context context) {
        SharedPreferences settings;
        List<IngredientData> favorites = null;

        settings = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);

        if(settings.contains(INGREDIENT_FAVORITE)) {
            String jsonFavorites = settings.getString(INGREDIENT_FAVORITE, null);
            Gson gson = new Gson();
            IngredientData[] favoriteItems = gson.fromJson(jsonFavorites, IngredientData[].class);

            favorites = Arrays.asList(favoriteItems);
            favorites = new ArrayList<IngredientData>(favorites);
        } else
            return null;

        return (ArrayList<IngredientData>) favorites;
    }

    public String getRecipeNameFavorite(Context context) {
        SharedPreferences settings;
        String name;

        settings = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);

        if(settings.contains(RECIPE_FAVORITE_NAME)) {
            name = settings.getString(RECIPE_FAVORITE_NAME, null);
        } else
            return null;

        return name;
    }

    public String getRecipeIdFavorite(Context context) {
        SharedPreferences settings;
        String id;

        settings = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);

        if(settings.contains(RECIPE_FAVORITE_ID)) {
            id = settings.getString(RECIPE_FAVORITE_ID, null);
        } else
            return null;

        return id;
    }

}