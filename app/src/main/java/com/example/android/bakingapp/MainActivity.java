package com.example.android.bakingapp;

import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import com.example.android.bakingapp.adapter.RecipeAdapter;
import com.example.android.bakingapp.data.RecipeData;
import com.example.android.bakingapp.utilities.NetworkUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private RecipeAdapter mRecipeAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = (RecyclerView) findViewById(R.id.baking_recycler_view);
        mRecyclerView.setHasFixedSize(true);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,1);

        boolean isThisInLandscape = getResources().getBoolean(R.bool.isItInLandscape);
        boolean isThisInPhone = getResources().getBoolean(R.bool.isItInPhone);

        if(isThisInLandscape) {
            gridLayoutManager.setOrientation(GridLayoutManager.HORIZONTAL);
            if(!isThisInPhone)
                gridLayoutManager.setSpanCount(2);
            else
                gridLayoutManager.setSpanCount(1);
        }else {
            gridLayoutManager.setSpanCount(1);
            gridLayoutManager.setOrientation(GridLayoutManager.VERTICAL);
        }

        mRecyclerView.setLayoutManager(gridLayoutManager);
        if(savedInstanceState != null) {
            ArrayList<RecipeData> a = savedInstanceState.getParcelableArrayList("RecipeList");
            if (mRecipeAdapter == null) {
                mRecipeAdapter = new RecipeAdapter(a, this);
            }else {
                mRecipeAdapter.setRecipeData(a);
            }

        }else {
            if (mRecipeAdapter == null) {
                mRecipeAdapter = new RecipeAdapter(new ArrayList<RecipeData>(), this);
            }
        }

        mRecyclerView.setAdapter(mRecipeAdapter);

        TextView messageTextView = (TextView)findViewById(R.id.main_message_text_view);
        NetworkUtils networkUtils = new NetworkUtils(MainActivity.this);
        if (networkUtils.isNetworkConnected()) {
            messageTextView.setVisibility(View.GONE);
            if (mRecipeAdapter.getItemCount() == 0)
                loadRecipeData();
        }else {
            messageTextView.setVisibility(View.VISIBLE);
            networkUtils.showAlertMessageAboutNoInternetConnection();
        }

    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        ArrayList<RecipeData> a = savedInstanceState.getParcelableArrayList(getString(R.string.recipe_list));
        mRecipeAdapter.setRecipeData(a);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList(getString(R.string.recipe_list),mRecipeAdapter.getRecipeData());
    }

    private void loadRecipeData() {
        new FetchRecipeTask(mRecipeAdapter,this, getWindow().findViewById(Window.ID_ANDROID_CONTENT)).execute();

    }
}
