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
import com.example.duong.android_forder_01.data.model.ShopManagement;
import com.example.duong.android_forder_01.ui.shopmanagement.ShopManagementActivityActionHandler;
import com.example.duong.android_forder_01.ui.shopmanagement.ShopManagementContract;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tri on 02/03/2017.
 */
public class ShopManagementAdapter extends BaseExpandableListAdapter {
    private Context mContext;
    private List<ShopManagement> mList = new ArrayList<>();
    private ShopManagementContract.Presenter mListener;

    public ShopManagementAdapter(Context context, List<ShopManagement> shopManagementList,
                                 ShopManagementContract
                                     .Presenter presenter) {
        mContext = context;
        mList = shopManagementList;
        mListener = presenter;
    }

    @Override
    public int getGroupCount() {
        return mList.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return mList.get(groupPosition).getShopInfoList().size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return mList.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return mList.get(groupPosition).getShopInfoList().get(childPosition);
    }

    public Object getChildShopDomain(int groupPosition, int childPosition) {
        return mList.get(groupPosition).getShopDomainList().get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return mList.get(groupPosition).getShop().getId();
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return mList.get(groupPosition).getShopInfoList().get(childPosition).getDomainId();
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView,
                             ViewGroup viewGroup) {
        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) mContext.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.item_parent_shop_management, null);
        }
        ViewDataBinding binding = DataBindingUtil.bind(convertView);
        binding.setVariable(BR.shopMamagement, getGroup(groupPosition));
        binding.setVariable(BR.actionHandler, new ShopManagementActivityActionHandler(mListener));
        binding.executePendingBindings();
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isExpanded,
                             View convertView, ViewGroup viewGroup) {
        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) mContext
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.item_shop_management, null);
        }
        ViewDataBinding binding = DataBindingUtil.bind(convertView);
        binding.setVariable(BR.shopInfo, getChild(groupPosition, childPosition));
        binding.setVariable(BR.shopDomain, getChildShopDomain(groupPosition, childPosition));
        binding.setVariable(BR.actionHandler, new ShopManagementActivityActionHandler(mListener));
        binding.executePendingBindings();
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }
}
