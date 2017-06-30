package com.framgia.forder.screen.productdetail.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import com.framgia.forder.R;
import com.framgia.forder.data.model.Comment;
import com.framgia.forder.data.model.User;
import com.framgia.forder.databinding.ItemCommentProductBinding;
import com.framgia.forder.screen.BaseRecyclerViewAdapter;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by levutantuan on 4/26/17.
 */

public class CommentAdapter extends BaseRecyclerViewAdapter<CommentAdapter.ItemViewHolder> {

    private final List<Comment> mComments;
    private User mUser;
    private DeleteCommentListener mDeleteCommentListener;

    public CommentAdapter(@NonNull Context context, List<Comment> comments) {
        super(context);
        mComments = new ArrayList<>();
        if (comments != null) {
            mComments.addAll(comments);
        }
    }

    @Override
    public CommentAdapter.ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemCommentProductBinding binding =
                DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                        R.layout.item_comment_product, parent, false);
        return new CommentAdapter.ItemViewHolder(binding, mUser, mDeleteCommentListener);
    }

    @Override
    public void onBindViewHolder(CommentAdapter.ItemViewHolder holder, int position) {
        holder.bind(mComments.get(position));
    }

    @Override
    public int getItemCount() {
        return mComments.size();
    }

    public void updateData(List<Comment> comments, User user) {
        if (comments == null) {
            return;
        }
        mComments.clear();
        mComments.addAll(comments);
        mUser = user;
        notifyDataSetChanged();
    }

    static class ItemViewHolder extends RecyclerView.ViewHolder {

        private final ItemCommentProductBinding mBinding;
        private final User mUser;
        private final DeleteCommentListener mDeleteCommentListener;

        ItemViewHolder(ItemCommentProductBinding binding, User user,
                DeleteCommentListener deleteCommentListener) {
            super(binding.getRoot());
            mBinding = binding;
            mUser = user;
            mDeleteCommentListener = deleteCommentListener;
        }

        void bind(Comment comment) {
            mBinding.setViewModel(new ItemUserViewModel(comment, mUser, mDeleteCommentListener));
            mBinding.executePendingBindings();
        }
    }

    public interface DeleteCommentListener {
        void onDeleteComment(int commentId);
    }

    public void setDeleteCommentListener(DeleteCommentListener deleteCommentListener) {
        mDeleteCommentListener = deleteCommentListener;
    }
}
