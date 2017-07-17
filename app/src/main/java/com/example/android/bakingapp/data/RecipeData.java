package com.example.android.bakingapp.data;

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by piwal on 5/11/2017.
 */

public class RecipeData implements Parcelable {
    private String mId;
    private String mName;
    private String mServings;
    private String mImage;
    private List<IngredientData> mIngredientData;
    private List<StepData> mStepData;

    private List<RecipeData> mObjList;

    public RecipeData(JSONObject json) {
        if(json != null) {
            if(json.has("id")) {
                try {
                    setId(json.get("id").toString().trim());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            if(json.has("name")) {
                try {
                    setName(json.get("name").toString().trim());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            if(json.has("servings")) {
                try {
                    setServings(json.get("servings").toString().trim());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            if(json.has("image")) {
                try {
                    setImage(json.get("image").toString().trim());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            if(json.has("ingredients")) {
                try {
                    JSONArray jsonIngredient = json.getJSONArray("ingredients");
                    if(mIngredientData == null)
                        mIngredientData = new ArrayList<>();
                    for(int i = 0; i < jsonIngredient.length(); i++) {
                        if (jsonIngredient.get(i) instanceof JSONObject) {
                            IngredientData ingredientData = new IngredientData(jsonIngredient.getJSONObject(i));
                            mIngredientData.add(ingredientData);
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            if(json.has("steps")) {
                try {
                    JSONArray jsonSteps = json.getJSONArray("steps");
                    if(mStepData == null)
                        mStepData = new ArrayList<>();
                    for(int i = 0; i < jsonSteps.length(); i++) {
                        if (jsonSteps.get(i) instanceof JSONObject) {
                            StepData stepData = new StepData(jsonSteps.getJSONObject(i));
                            mStepData.add(stepData);
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    public void setId(String id) { mId = id; }
    public String getId() { return mId; }

    public void setName(String name) { mName = name; }
    public String getName() { return mName; }

    public void setServings(String servings) { mServings = servings; }
    public String getServings() { return mServings; }

    public void setImage(String image) { mImage = image; }
    public String getImage() { return mImage; }

    public void setIngredientData(List<IngredientData> ingredientData) {
        mIngredientData = ingredientData;
    }
    public List<IngredientData> getIngredientData() {
        return mIngredientData;
    }

    public void setStepData(List<StepData> stepData) {
        mStepData = stepData;
    }
    public List<StepData> getStepData() {
        return mStepData;
    }

    protected RecipeData(Parcel in) {
        mId = in.readString();
        mName = in.readString();
        mServings = in.readString();
        mImage = in.readString();
        mIngredientData = new ArrayList<IngredientData>();
        in.readList(mIngredientData,RecipeData.class.getClassLoader());
        mStepData = new ArrayList<StepData>();
        in.readList(mStepData,RecipeData.class.getClassLoader());
    }
    public static final Creator<RecipeData> CREATOR =  new Creator<RecipeData>() {

        @Override
        public RecipeData createFromParcel(Parcel source) {
            return new RecipeData(source);
        }

        @Override
        public RecipeData[] newArray(int size) {
            return new RecipeData[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mId);
        dest.writeString(mName);
        dest.writeString(mImage);
        dest.writeString(mServings);
        dest.writeList(mIngredientData);
        dest.writeList(mStepData);

    }
}
