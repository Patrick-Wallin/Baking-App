package com.example.android.bakingapp.data;


import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by piwal on 5/10/2017.
 */

public class RecipeContract {
    public static final String SCHEME = "content://";
    public static final String CONTENT_AUTHORITY = "com.example.android.bakingapp";
    public static final Uri BASE_CONTENT_URI = Uri.parse(SCHEME + CONTENT_AUTHORITY);
    public static final String PATH_RECIPES = "recipes";
    public static final String PATH_INGREDIENTS = "ingredients";
    public static final String PATH_STEPS = "steps";

    public static final class RecipeEntry implements BaseColumns {
        public static final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon().appendPath(PATH_RECIPES).build();

        public static final String TABLE_NAME = PATH_RECIPES;

        public static final String COLUMN_RECIPE_ID = "recipe_id";
        public static final String COLUMN_RECIPE_NAME = "name";
        public static final String COLUMN_RECIPE_INGREDIENTS_ID = "ingredients";
        public static final String COLUMN_RECIPE_STEPS_ID = "steps";
        public static final String COLUMN_RECIPE_SERVINGS = "servings";
        public static final String COLUMN_RECIPE_IMAGE = "image";

        public static final int COL_RECIPE_ID = 1;
        public static final int COL_RECIPE_NAME = 2;
        public static final int COL_RECIPE_INGREDIENTS = 3;
        public static final int COL_RECIPE_STEPS = 4;
        public static final int COL_RECIPE_SERVINGS = 5;
        public static final int COL_RECIPE_IMAGE = 6;
    }

    public static final class IngredientsEntry implements BaseColumns {
        public static final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon().appendPath(PATH_INGREDIENTS).build();

        public static final String TABLE_NAME = PATH_INGREDIENTS;

        public static final String COLUMN_INGREDIENTS_ID = "ingredients_id";
        public static final String COLUMN_INGREDIENTS_QUANTITY = "quantity";
        public static final String COLUMN_INGREDIENTS_MEASURE = "measure";
        public static final String COLUMN_INGREDIENTS_INGREDIENT = "ingredient";

        public static final int COL_INGREDIENTS_ID = 1;
        public static final int COL_INGREDIENTS_QUANTITY = 2;
        public static final int COL_INGREDIENTS_MEASURE = 3;
        public static final int COL_INGREDIENTS_INGREDIENT = 4;
    }

    public static final class StepsEntry implements BaseColumns {
        public static final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon().appendPath(PATH_STEPS).build();

        public static final String TABLE_NAME = PATH_STEPS;

        public static final String COLUMN_STEPS_ID = "steps_id";
        public static final String COLUMN_STEPS_SHORT_DESCRIPTION = "short_description";
        public static final String COLUMN_STEPS_DESCRIPTION = "description";
        public static final String COLUMN_STEPS_VIDEO_URL = "video_url";
        public static final String COLUMN_STEPS_THUMBNAIL_URL = "thumbnail_url";

        public static final int COL_STEPS_ID = 1;
        public static final int COL_STEPS_SHORT_DESCRIPTION = 2;
        public static final int COL_STEPS_DESCRIPTION = 3;
        public static final int COL_STEPS_VIDEO_URL = 4;
        public static final int COL_STEPS_THUMBNAIL_URL = 5;

    }
}
