package com.example.duong.android_forder_01.ui.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;

import com.example.duong.android_forder_01.BR;
import com.example.duong.android_forder_01.R;
import com.example.duong.android_forder_01.data.model.OrderDetail;
import com.example.duong.android_forder_01.data.model.Orders;
import com.example.duong.android_forder_01.ui.shopmanagementdetail.ordershop.checkorder.CheckOrderActionHandler;
import com.example.duong.android_forder_01.ui.shopmanagementdetail.ordershop.checkorder.CheckOrderContract;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Duong on 3/10/2017.
 */
public class CheckOrderAdapter extends BaseExpandableListAdapter {
    private Context mContext;
    private List<Orders> mOrders = new ArrayList<>();
    private CheckOrderContract.Presenter mListener;

    public CheckOrderAdapter(Context context, List<Orders> orders,
                             CheckOrderContract.Presenter presenter) {
        mContext = context;
        mOrders = orders;
        mListener = presenter;
    }

    @Override
    public int getGroupCount() {
        return mOrders== null ? 0: mOrders.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return getGroup(groupPosition).getOrderDetails().size();
    }

    @Override
    public Orders getGroup(int groupPosition) {
        return mOrders.get(groupPosition);
    }

    @Override
    public OrderDetail getChild(int groupPosition, int childPosition) {
        return getGroup(groupPosition).getOrderDetails().get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return getGroup(groupPosition).getId();
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return getChild(groupPosition, childPosition).getId();
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView,
                             ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) mContext.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.item_parent_check_order, null);
        }
        ViewDataBinding mBinding = DataBindingUtil.bind(convertView);
        mBinding.setVariable(BR.order, getGroup(groupPosition));
        mBinding.executePendingBindings();
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild,
                             View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) mContext
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.item_check_order, null);
        }
        ViewDataBinding mBinding = DataBindingUtil.bind(convertView);
        mBinding.setVariable(BR.orderDetail, getChild(groupPosition, childPosition));
        mBinding.setVariable(BR.actionHandler, new CheckOrderActionHandler(mListener));
        mBinding.executePendingBindings();
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }
}
