package com.framgia.forder.data.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Duong on 4/17/2017.
 */

public class CollectionAvatar extends InputStream implements Parcelable {

    public static final Creator<CollectionAvatar> CREATOR = new Creator<CollectionAvatar>() {
        @Override
        public CollectionAvatar createFromParcel(Parcel in) {
            return new CollectionAvatar(in);
        }

        @Override
        public CollectionAvatar[] newArray(int size) {
            return new CollectionAvatar[size];
        }
    };

    public CollectionAvatar(Image image) {
        mImage = image;
    }

    @Expose
    @SerializedName(value = "avatar", alternate = { "standard" })
    private Image mImage;
    @Expose
    @SerializedName("url")
    private String mUrl;

    private CollectionAvatar(Parcel in) {
        mImage = in.readParcelable(Image.class.getClassLoader());
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(mImage, flags);
    }

    public Image getImage() {
        return mImage;
    }

    public void setImage(Image image) {
        mImage = image;
    }

    public String getUrl() {
        return mUrl;
    }

    public void setUrl(String url) {
        mUrl = url;
    }

    @Override
    public int read() throws IOException {
        return 0;
    }
}
