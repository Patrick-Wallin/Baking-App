package com.example.android.bakingapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Window;

import com.example.android.bakingapp.adapter.RecipeAdapter;
import com.example.android.bakingapp.data.RecipeData;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private RecipeAdapter mRecipeAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = (RecyclerView) findViewById(R.id.baking_recycler_view);
        mRecyclerView.setHasFixedSize(true);

        //LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,1);

        boolean isThisInLandscape = getResources().getBoolean(R.bool.isItInLandscape);
        boolean isThisInPhone = getResources().getBoolean(R.bool.isItInPhone);
        if(isThisInLandscape) {
            gridLayoutManager.setOrientation(GridLayoutManager.HORIZONTAL);
            if(!isThisInPhone)
                gridLayoutManager.setSpanCount(2);
            else
                gridLayoutManager.setSpanCount(1);
            //linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
            //linearLayoutManager.setS;
        }else {
            gridLayoutManager.setSpanCount(1);
            gridLayoutManager.setOrientation(GridLayoutManager.VERTICAL);
            //linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        }

        //mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.setLayoutManager(gridLayoutManager);
        if(mRecipeAdapter == null) {
            mRecipeAdapter = new RecipeAdapter(new ArrayList<RecipeData>(),this);
        }
        mRecyclerView.setAdapter(mRecipeAdapter);
        loadRecipeData();
    }

    private void loadRecipeData() {
        new FetchRecipeTask(mRecipeAdapter,this, getWindow().findViewById(Window.ID_ANDROID_CONTENT)).execute();

    }
}
