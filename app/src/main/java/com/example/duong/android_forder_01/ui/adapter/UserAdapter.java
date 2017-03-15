package com.example.duong.android_forder_01.ui.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.duong.android_forder_01.BR;
import com.example.duong.android_forder_01.R;
import com.example.duong.android_forder_01.data.model.User;
import com.example.duong.android_forder_01.ui.domain.detaildomain.memberdomain.MemberDomainContract;

import java.util.List;

/**
 * Created by Vinh on 07/03/2017.
 */
public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder> {
    private List<User> mUserList;
    private LayoutInflater mLayoutInflater;
    private MemberDomainContract.Presenter mListener;

    public UserAdapter(List<User> list, Context context, MemberDomainContract.Presenter presenter) {
        mUserList = list;
        mLayoutInflater = LayoutInflater.from(context);
        mListener = presenter;
    }

    @Override
    public UserAdapter.UserViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView =
            mLayoutInflater.inflate(R.layout.item_user, parent, false);
        return new UserAdapter.UserViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(UserAdapter.UserViewHolder holder, int position) {
        holder.bindData(mUserList.get(position));
    }

    @Override
    public int getItemCount() {
        return mUserList != null ? mUserList.size() : 0;
    }

    public class UserViewHolder extends RecyclerView.ViewHolder {
        private ViewDataBinding mDataBinding;

        public UserViewHolder(View view) {
            super(view);
            mDataBinding = DataBindingUtil.bind(view);
        }

        public void bindData(User user) {
            if (user == null) return;
            mDataBinding.setVariable(BR.user, user);
            mDataBinding.executePendingBindings();
        }
    }
}
