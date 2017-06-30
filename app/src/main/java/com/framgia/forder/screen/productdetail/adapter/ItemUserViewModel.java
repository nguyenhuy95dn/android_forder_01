package com.framgia.forder.screen.productdetail.adapter;

import com.framgia.forder.data.model.Comment;
import com.framgia.forder.data.model.User;

/**
 * Created by levutantuan on 4/27/17.
 */

public class ItemUserViewModel {

    private final Comment mComment;
    private final User mUser;
    private boolean mIsDeleteCommentVisible;
    private CommentAdapter.DeleteCommentListener mDeleteCommentListener;

    public ItemUserViewModel(Comment comment, User user,
            CommentAdapter.DeleteCommentListener deleteCommentListener) {
        mComment = comment;
        mUser = user;
        mDeleteCommentListener = deleteCommentListener;
        buttonRemoveComment();
    }

    public String getUserImage() {
        if (mComment.getImage() != null) {
            return mComment.getImage().getUrl();
        }
        return "";
    }

    public void onClickDeleteComment() {
        mDeleteCommentListener.onDeleteComment(mComment.getId());
    }

    private void buttonRemoveComment() {
        if (mComment.getUserId() == mUser.getId()) {
            setDeleteCommentVisible(true);
        } else {
            setDeleteCommentVisible(false);
        }
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

    public boolean isDeleteCommentVisible() {
        return mIsDeleteCommentVisible;
    }

    private void setDeleteCommentVisible(boolean deleteCommentVisible) {
        mIsDeleteCommentVisible = deleteCommentVisible;
    }
}
