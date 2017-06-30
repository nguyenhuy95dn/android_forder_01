package com.framgia.forder.data.source.remote.api.request;

import com.framgia.forder.data.model.RegisterSendComment;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by levutantuan on 4/27/17.
 */

public class CommentRequest {
    @Expose
    @SerializedName("product_id")
    private int mProductId;
    @Expose
    @SerializedName("comment")
    private RegisterSendComment mComment;

    public int getProductId() {
        return mProductId;
    }

    public void setProductId(int productId) {
        mProductId = productId;
    }

    public RegisterSendComment getComment() {
        return mComment;
    }

    public void setComment(RegisterSendComment comment) {
        mComment = comment;
    }
}
