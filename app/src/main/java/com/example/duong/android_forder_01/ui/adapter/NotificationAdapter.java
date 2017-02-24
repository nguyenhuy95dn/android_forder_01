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
import com.example.duong.android_forder_01.data.model.Notification;

import java.util.List;

/**
 * Created by tri on 23/02/2017.
 */
public class NotificationAdapter
    extends RecyclerView.Adapter<NotificationAdapter.NotificationViewHolder> {
    private List<Notification> mListNotifications;
    private LayoutInflater mLayoutInflater;

    public NotificationAdapter(List<Notification> list, Context context) {
        mListNotifications = list;
        mLayoutInflater = LayoutInflater.from(context);
    }

    @Override
    public NotificationViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView =
            mLayoutInflater.inflate(R.layout.item_notification, parent, false);
        return new NotificationViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(NotificationViewHolder holder, int position) {
        holder.bindData(mListNotifications.get(position));
    }

    @Override
    public int getItemCount() {
        return mListNotifications != null ? mListNotifications.size() : 0;
    }

    public class NotificationViewHolder extends RecyclerView.ViewHolder {
        private ViewDataBinding mDataBinding;

        public NotificationViewHolder(View view) {
            super(view);
            mDataBinding = DataBindingUtil.bind(view);
        }

        public void bindData(Notification notification) {
            if (notification == null) return;
            mDataBinding.setVariable(BR.notification, notification);
            mDataBinding.executePendingBindings();
        }
    }
}
