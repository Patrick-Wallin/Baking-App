package com.example.android.bakingapp.data;

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by piwal on 5/11/2017.
 */

public class StepData implements Parcelable {
    private String mId;
    private String mShortDescription;
    private String mDescription;
    private String mVideoUrl;
    private String mThumbnailUrl;

    public StepData(JSONObject json) {
        if (json != null) {
            if (json.has("id")) {
                try {
                    setId(json.get("id").toString().trim());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            if (json.has("shortDescription")) {
                try {
                    setShortDescription(json.get("shortDescription").toString().trim());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            if (json.has("description")) {
                try {
                    setDescription(json.get("description").toString().trim());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            if (json.has("videoURL")) {
                try {
                    setVideoUrl(json.get("videoURL").toString().trim());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            if (json.has("thumbnailURL")) {
                try {
                    setThumbnailUrl(json.get("thumbnailURL").toString().trim());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void setId(String id) {
        mId = id;
    }
    public String getId() {
        return mId;
    }
    public void setShortDescription(String shortDescription) {
        mShortDescription = shortDescription;
    }
    public String getShortDescription() {
        return mShortDescription;
    }
    public void setDescription(String description) {
        mDescription = description;
    }
    public String getDescription() {
        return mDescription;
    }
    public void setVideoUrl(String videoUrl) {
        mVideoUrl = videoUrl;
    }
    public String getVideoUrl() {
        return mVideoUrl;
    }
    public void setThumbnailUrl(String thumbnailUrl) {
        mThumbnailUrl = thumbnailUrl;
    }
    public String getThumbnailUrl() {
        return mThumbnailUrl;
    }

    protected StepData(Parcel in) {
        mId = in.readString();
        mShortDescription = in.readString();
        mDescription = in.readString();
        mVideoUrl = in.readString();
        mThumbnailUrl = in.readString();
    }

    public static final Creator<StepData> CREATOR =  new Creator<StepData>() {

        @Override
        public StepData createFromParcel(Parcel source) {
            return new StepData(source);
        }

        @Override
        public StepData[] newArray(int size) {
            return new StepData[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mId);
        dest.writeString(mShortDescription);
        dest.writeString(mDescription);
        dest.writeString(mVideoUrl);
        dest.writeString(mThumbnailUrl);
    }
}
