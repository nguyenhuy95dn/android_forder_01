package com.framgia.forder.data.source.remote.api.response;

import com.framgia.forder.data.model.Comment;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;

/**
 * Created by levutantuan on 6/30/17.
 */

public class CommentResponse {

    @Expose
    @SerializedName("status")
    private Integer mStatus;
    @Expose
    @SerializedName("message")
    private String mMessage;
    @Expose
    @SerializedName("content")
    private List<Comment> mCommentList;

    public Integer getStatus() {
        return mStatus;
    }

    public void setStatus(Integer status) {
        mStatus = status;
    }

    public String getMessage() {
        return mMessage;
    }

    public void setMessage(String message) {
        mMessage = message;
    }

    public List<Comment> getCommentList() {
        return mCommentList;
    }

    public void setCommentList(List<Comment> commentList) {
        mCommentList = commentList;
    }
}
