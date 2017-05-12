package com.framgia.forder.screen.shopmanagement;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import com.framgia.forder.R;
import com.framgia.forder.data.model.ShopManagement;
import com.framgia.forder.data.source.remote.api.request.ApplyShopToDomainRequest;
import com.framgia.forder.data.source.remote.api.request.LeaveShopToDomainRequest;
import com.framgia.forder.databinding.ItemJoinDomainBinding;
import com.framgia.forder.databinding.ItemShopManagementBinding;
import com.framgia.forder.widgets.dialog.DialogManager;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by levutantuan on 5/3/17.
 */

public class ListShopManagementAdapter extends BaseExpandableListAdapter {

    private final Context mContext;
    private final List<ShopManagement> mShopManagements;
    private ShopDomainManagementListener mShopDomainManagementListener;
    private final DialogManager mDialogManager;

    ListShopManagementAdapter(@NonNull Context context) {
        this.mContext = context;
        mShopManagements = new ArrayList<>();
        mDialogManager = new DialogManager(context);
    }

    public void setShopDomainManagementListener(
            ShopDomainManagementListener shopDomainManagementListener) {
        mShopDomainManagementListener = shopDomainManagementListener;
    }

    public void updateData(List<ShopManagement> shopManagements) {
        if (shopManagements == null) {
            return;
        }
        mShopManagements.clear();
        mShopManagements.addAll(shopManagements);
        notifyDataSetChanged();
    }

    @Override
    public int getGroupCount() {
        return mShopManagements.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        if (mShopManagements.get(groupPosition) != null
                && mShopManagements.get(groupPosition).getShopInfos() != null) {
            return mShopManagements.get(groupPosition).getShopInfos().size();
        }
        return 0;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return mShopManagements.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        if (mShopManagements.get(groupPosition) != null
                && mShopManagements.get(groupPosition).getShopInfos() != null) {
            return mShopManagements.get(groupPosition);
        }
        return "";
    }

    @Override
    public long getGroupId(int groupPosition) {
        if (mShopManagements.get(groupPosition) != null
                && mShopManagements.get(groupPosition).getShop() != null) {
            return mShopManagements.get(groupPosition).getShop().getId();
        }
        return 0;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        if (mShopManagements.get(groupPosition) != null
                && mShopManagements.get(groupPosition).getShopInfos() != null
                && mShopManagements.get(groupPosition).getShopInfos().get(childPosition) != null) {
            return Long.parseLong(String.valueOf(mShopManagements.get(groupPosition)
                    .getShopInfos()
                    .get(childPosition)
                    .getDomainId()));
        }
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView,
            ViewGroup parent) {
        ItemShopManagementBinding binding =
                DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                        R.layout.item_shop_management, parent, false);
        ItemShopManagementViewModel viewModel =
                new ItemShopManagementViewModel((ShopManagement) getGroup(groupPosition));
        binding.setViewModel(viewModel);
        binding.executePendingBindings();
        return binding.getRoot();
    }

    @Override
    public View getChildView(int groupPosition, final int childPosition, boolean isLastChild,
            View convertView, ViewGroup parent) {
        ItemJoinDomainBinding binding =
                DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                        R.layout.item_join_domain, parent, false);
        final ItemShopInfoViewModel viewModel = new ItemShopInfoViewModel(mContext,
                (ShopManagement) getChild(groupPosition, childPosition), childPosition,
                mShopDomainManagementListener, mDialogManager);
        binding.setViewModel(viewModel);
        binding.executePendingBindings();
        return binding.getRoot();
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }

    public interface ShopDomainManagementListener {
        void onRequestJoinDomain(ApplyShopToDomainRequest applyShopToDomainRequest);

        void onCancleJoinDomain(LeaveShopToDomainRequest leaveShopToDomainRequest);
    }
}
