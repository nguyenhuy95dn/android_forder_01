package com.example.duong.android_forder_01.data.model;

import java.io.Serializable;

/**
 * Created by tri on 21/03/2017.
 */
public class Guide implements Serializable {
    private String mTitle;
    private String mDescription;

    public Guide(String title, String description) {
        mTitle = title;
        mDescription = description;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String description) {
        mDescription = description;
    }
}
