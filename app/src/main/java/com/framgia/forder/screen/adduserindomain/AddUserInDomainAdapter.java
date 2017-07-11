package com.framgia.forder.screen.adduserindomain;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import com.framgia.forder.R;
import com.framgia.forder.data.model.User;
import com.framgia.forder.databinding.ItemAddUserInDomainBinding;
import com.framgia.forder.screen.BaseRecyclerViewAdapter;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by nguyenhuy95dn on 7/11/2017.
 */

public class AddUserInDomainAdapter
        extends BaseRecyclerViewAdapter<AddUserInDomainAdapter.ItemViewHolder> {

    private final List<User> mUsers;
    private AddUserInDomainListener mAddUserInDomainListener;
    private boolean mIsAuthority;

    AddUserInDomainAdapter(@NonNull Context context) {
        super(context);
        mUsers = new ArrayList<>();
    }

    @Override
    public AddUserInDomainAdapter.ItemViewHolder onCreateViewHolder(ViewGroup parent,
            int viewType) {
        ItemAddUserInDomainBinding binding =
                DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                        R.layout.item_add_user_in_domain, parent, false);
        return new AddUserInDomainAdapter.ItemViewHolder(binding, mAddUserInDomainListener);
    }

    @Override
    public void onBindViewHolder(AddUserInDomainAdapter.ItemViewHolder holder, int position) {
        holder.bind(mUsers.get(position));
    }

    public void setUserInDomainListener(AddUserInDomainListener addUserInDomainListener) {
        mAddUserInDomainListener = addUserInDomainListener;
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

    public interface AddUserInDomainListener {

        void onClickAddUser(int userId);
    }

    static class ItemViewHolder extends RecyclerView.ViewHolder {

        private final ItemAddUserInDomainBinding mBinding;
        private final AddUserInDomainListener mAddUserInDomainListener;

        ItemViewHolder(ItemAddUserInDomainBinding binding,
                AddUserInDomainListener addUserInDomainListener) {
            super(binding.getRoot());
            mBinding = binding;
            mAddUserInDomainListener = addUserInDomainListener;
        }

        void bind(User user) {
            mBinding.setViewModel(new ItemAddUserInDomainViewModel(user, mAddUserInDomainListener));
            mBinding.executePendingBindings();
        }
    }
}
