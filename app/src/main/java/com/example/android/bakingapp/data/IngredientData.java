package com.example.android.bakingapp.data;

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by piwal on 5/11/2017.
 */

public class IngredientData implements Parcelable {
    private String mQuantity;
    private String mMeasure;
    private String mIngredient;

    public IngredientData(JSONObject json) {
        if(json != null) {
            if (json.has("quantity")) {
                try {
                    setQuantity(json.get("quantity").toString().trim());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            if (json.has("measure")) {
                try {
                    setMeasure(json.get("measure").toString().trim());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            if(json.has("ingredient")) {
                try {
                    setIngredient(json.get("ingredient").toString().trim());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void setQuantity(String quantity) {
        mQuantity = quantity;
    }
    public String getQuantity() {
        return mQuantity;
    }
    public void setMeasure(String measure) {
        mMeasure = measure;
    }
    public String getMeasure() {
        return mMeasure;
    }
    public void setIngredient(String ingredient) {
        mIngredient = ingredient;
    }
    public String getIngredient() {
        return mIngredient;
    }

    protected IngredientData(Parcel in) {
        mQuantity = in.readString();
        mMeasure = in.readString();
        mIngredient = in.readString();
    }

    public static final Creator<IngredientData> CREATOR =  new Creator<IngredientData>() {

        @Override
        public IngredientData createFromParcel(Parcel source) {
            return new IngredientData(source);
        }

        @Override
        public IngredientData[] newArray(int size) {
            return new IngredientData[size];
        }
    };
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mQuantity);
        dest.writeString(mMeasure);
        dest.writeString(mIngredient);
    }
}
