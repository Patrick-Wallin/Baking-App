package com.example.android.bakingapp;

import com.example.android.bakingapp.data.StepData;

/**
 * Created by piwal on 7/17/2017.
 */

public interface StepInstructionListener {
    void onClicked(String name, StepData stepData);
}
