package com.framgia.forder.screen.notification;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import com.framgia.forder.R;
import com.framgia.forder.data.model.Notification;
import com.framgia.forder.databinding.ItemListNotificationBinding;
import com.framgia.forder.screen.BaseRecyclerViewAdapter;
import com.framgia.forder.utils.navigator.Navigator;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ASUS on 24-04-2017.
 */

public class NotificationPageAdapter
        extends BaseRecyclerViewAdapter<NotificationPageAdapter.ItemViewHolder> {

    private final Navigator mNavigator;
    private OnRecyclerViewItemClickListener<Object> mItemClickListener;
    private final List<Notification> mNotifications;

    NotificationPageAdapter(@NonNull Context context, Navigator navigator,
            List<Notification> notifications) {
        super(context);
        mNavigator = navigator;
        mNotifications = new ArrayList<>();
        if (notifications == null) {
            return;
        }
        mNotifications.addAll(notifications);
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemListNotificationBinding itemListNotificationBinding =
                DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                        R.layout.item_list_notification, parent, false);
        return new NotificationPageAdapter.ItemViewHolder(mNavigator, itemListNotificationBinding,
                mItemClickListener);
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {
        holder.bind(mNotifications.get(position));
    }

    @Override
    public int getItemCount() {
        return mNotifications.size();
    }

    void setItemClickListener(OnRecyclerViewItemClickListener<Object> itemClickListener) {
        mItemClickListener = itemClickListener;
    }

    void updateData(List<Notification> notifications) {
        if (notifications == null) {
            return;
        }
        mNotifications.clear();
        mNotifications.addAll(notifications);
        notifyDataSetChanged();
    }

    static class ItemViewHolder extends RecyclerView.ViewHolder {
        private final Navigator mNavigator;
        private final ItemListNotificationBinding mBinding;
        private final OnRecyclerViewItemClickListener<Object> mItemClickListener;

        ItemViewHolder(Navigator navigator, ItemListNotificationBinding binding,
                BaseRecyclerViewAdapter.OnRecyclerViewItemClickListener<Object> listener) {
            super(binding.getRoot());
            mNavigator = navigator;
            mBinding = binding;
            mItemClickListener = listener;
        }

        void bind(Notification notification) {
            mBinding.setViewModel(
                    new ItemNotificationViewModel(mNavigator, notification, mItemClickListener));
            mBinding.executePendingBindings();
        }
    }
}
