package com.framgia.forder.screen.productdetail.adapter;

import android.databinding.BaseObservable;
import android.view.View;
import com.framgia.forder.data.model.Comment;
import com.framgia.forder.screen.BaseRecyclerViewAdapter;

/**
 * Created by levutantuan on 4/27/17.
 */

public class ItemUserViewModel extends BaseObservable {

    private final Comment mComment;
    private final BaseRecyclerViewAdapter.OnRecyclerViewItemClickListener<Comment>
            mItemClickListener;

    public ItemUserViewModel(Comment comment,
            BaseRecyclerViewAdapter.OnRecyclerViewItemClickListener<Comment> listener) {
        mComment = comment;
        mItemClickListener = listener;
    }

    public void onAvatarClicked(View view) {
        if (mItemClickListener == null) {
            return;
        }
        mItemClickListener.onItemRecyclerViewClick(mComment);
    }

    public String getUserImage() {
        if (mComment.getImage() != null) {
            return mComment.getImage().getUrl();
        }
        return "";
    }

    public String getUserName() {
        return mComment.getUserName();
    }

    public String getComment() {
        return mComment.getComment();
    }

    public String getDate() {
        return mComment.getTimeCommentFormat();
    }
}
