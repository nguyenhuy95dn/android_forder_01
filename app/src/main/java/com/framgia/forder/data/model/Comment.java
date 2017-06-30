package com.framgia.forder.data.model;

import com.framgia.forder.utils.Utils;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import static com.framgia.forder.utils.Constant.VERTICAL_COLUMN;
import static com.framgia.forder.utils.Utils.INPUT_TIME_FORMAT;
import static com.framgia.forder.utils.Utils.OUTPUT_DATE_FORMAT;
import static com.framgia.forder.utils.Utils.OUTPUT_TIME_FORMAT;

/**
 * Created by levutantuan on 4/27/17.
 */

public class Comment {

    @Expose
    @SerializedName("id")
    private int mId;
    @Expose
    @SerializedName("content")
    private String mComment;
    @Expose
    @SerializedName("user_id")
    private int mUserId;
    @Expose
    @SerializedName("commentable_type")
    private String mCommentableType;
    @Expose
    @SerializedName("commentable_id")
    private int mCommentableId;
    @Expose
    @SerializedName("created_at")
    private String mTimeCreate;
    @Expose
    @SerializedName("updated_at")
    private String mTimeUpdate;
    @Expose
    @SerializedName("name")
    private String mUserName;
    @Expose
    @SerializedName("avatar")
    private Image mImage;

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        mId = id;
    }

    public String getComment() {
        return mComment;
    }

    public void setComment(String comment) {
        mComment = comment;
    }

    public int getUserId() {
        return mUserId;
    }

    public void setUserId(int userId) {
        mUserId = userId;
    }

    public String getCommentableType() {
        return mCommentableType;
    }

    public void setCommentableType(String commentableType) {
        mCommentableType = commentableType;
    }

    public int getCommentableId() {
        return mCommentableId;
    }

    public void setCommentableId(int commentableId) {
        mCommentableId = commentableId;
    }

    public String getTimeCreate() {
        return mTimeCreate;
    }

    public void setTimeCreate(String timeCreate) {
        mTimeCreate = timeCreate;
    }

    public String getTimeUpdate() {
        return mTimeUpdate;
    }

    public void setTimeUpdate(String timeUpdate) {
        mTimeUpdate = timeUpdate;
    }

    public String getUserName() {
        return mUserName;
    }

    public void setUserName(String userName) {
        mUserName = userName;
    }

    public Image getImage() {
        return mImage;
    }

    public void setImage(Image image) {
        mImage = image;
    }

    public String getTimeCommentFormat() {
        return Utils.DateTimeUntils.convertUiFormatToDataFormat(mTimeCreate, INPUT_TIME_FORMAT,
                OUTPUT_TIME_FORMAT + VERTICAL_COLUMN + OUTPUT_DATE_FORMAT);
    }
}
