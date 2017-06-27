package com.framgia.forder.screen.userindomain;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import com.framgia.forder.R;
import com.framgia.forder.data.model.User;
import com.framgia.forder.databinding.ItemUserInDomainBinding;
import com.framgia.forder.screen.BaseRecyclerViewAdapter;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by nguyenhuy95dn on 6/26/2017.
 */

public class UserInDomainAdapter
        extends BaseRecyclerViewAdapter<UserInDomainAdapter.ItemViewHolder> {

    private final List<User> mUsers;
    private UserInDomainListener mUserInDomainListener;
    private boolean mIsAuthority;

    UserInDomainAdapter(@NonNull Context context) {
        super(context);
        mUsers = new ArrayList<>();
    }

    @Override
    public UserInDomainAdapter.ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemUserInDomainBinding binding =
                DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                        R.layout.item_user_in_domain, parent, false);
        return new UserInDomainAdapter.ItemViewHolder(binding, mUserInDomainListener, mIsAuthority);
    }

    @Override
    public void onBindViewHolder(UserInDomainAdapter.ItemViewHolder holder, int position) {
        holder.bind(mUsers.get(position));
    }

    public void setUserInDomainListener(UserInDomainListener userInDomainListener) {
        mUserInDomainListener = userInDomainListener;
    }

    public void setAuthorityInDomain(boolean isAuthority) {
        mIsAuthority = isAuthority;
    }

    @Override
    public int getItemCount() {
        return mUsers.size();
    }

    public void updateData(List<User> users) {
        if (users == null) {
            return;
        }
        mUsers.clear();
        mUsers.addAll(users);
        notifyDataSetChanged();
    }

    static class ItemViewHolder extends RecyclerView.ViewHolder {

        private final ItemUserInDomainBinding mBinding;
        private final UserInDomainListener mUserInDomainListener;
        private boolean mIsAuthority;

        ItemViewHolder(ItemUserInDomainBinding binding, UserInDomainListener userInDomainListener,
                boolean isAuthority) {
            super(binding.getRoot());
            mBinding = binding;
            mUserInDomainListener = userInDomainListener;
            mIsAuthority = isAuthority;
        }

        void bind(User user) {
            mBinding.setViewModel(
                    new ItemUserInDomainViewModel(user, mUserInDomainListener, mIsAuthority));
            mBinding.executePendingBindings();
        }
    }
}
