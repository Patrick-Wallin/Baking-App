package com.example.android.bakingapp.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by piwal on 5/10/2017.
 */

public class RecipeDBHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "recipes.db";
    private static int DATABASE_VERSION = 1;

    public RecipeDBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        final String SQL_CREATE_RECIPE_TABLE = "CREATE TABLE " +
                RecipeContract.RecipeEntry.TABLE_NAME + " (" +
                RecipeContract.RecipeEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                RecipeContract.RecipeEntry.COLUMN_RECIPE_ID + " INTEGER NOT NULL, " +
                RecipeContract.RecipeEntry.COLUMN_RECIPE_NAME + " TEXT NOT NULL, " +
                RecipeContract.RecipeEntry.COLUMN_RECIPE_INGREDIENTS_ID + " INTEGER NOT NULL, " +
                RecipeContract.RecipeEntry.COLUMN_RECIPE_STEPS_ID + " INTEGER NOT NULL, " +
                RecipeContract.RecipeEntry.COLUMN_RECIPE_SERVINGS + " INTEGER NOT NULL, " +
                RecipeContract.RecipeEntry.COLUMN_RECIPE_IMAGE + " TEXT NOT NULL" +
                ")";
        db.execSQL(SQL_CREATE_RECIPE_TABLE);

        final String SQL_CREATE_INGREDIENTS_TABLE = "CREATE TABLE " +
                RecipeContract.IngredientsEntry.TABLE_NAME + " (" +
                RecipeContract.IngredientsEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                RecipeContract.IngredientsEntry.COLUMN_INGREDIENTS_ID + " INTEGER NOT NULL, " +
                RecipeContract.IngredientsEntry.COLUMN_INGREDIENTS_QUANTITY + " INTEGER NOT NULL, " +
                RecipeContract.IngredientsEntry.COLUMN_INGREDIENTS_MEASURE + " TEXT NOT NULL, " +
                RecipeContract.IngredientsEntry.COLUMN_INGREDIENTS_INGREDIENT + " TEXT NOT NULL" +
                ")";
        db.execSQL(SQL_CREATE_INGREDIENTS_TABLE);

        final String SQL_CREATE_STEPS_TABLE = "CREATE TABLE " +
                RecipeContract.StepsEntry.TABLE_NAME + " (" +
                RecipeContract.StepsEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                RecipeContract.StepsEntry.COLUMN_STEPS_ID + " INTEGER NOT NULL, " +
                RecipeContract.StepsEntry.COLUMN_STEPS_DESCRIPTION + " TEXT NOT NULL, " +
                RecipeContract.StepsEntry.COLUMN_STEPS_DESCRIPTION + " TEXT NOT NULL, " +
                RecipeContract.StepsEntry.COLUMN_STEPS_THUMBNAIL_URL + " TEXT NOT NULL, " +
                RecipeContract.StepsEntry.COLUMN_STEPS_VIDEO_URL + " TEXT NOT NULL" +
                ")";
        db.execSQL(SQL_CREATE_STEPS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + RecipeContract.RecipeEntry.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + RecipeContract.IngredientsEntry.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + RecipeContract.StepsEntry.TABLE_NAME);
        onCreate(db);
    }
}
