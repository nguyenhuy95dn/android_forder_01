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
import com.example.duong.android_forder_01.data.model.ShoppingCard;
import com.example.duong.android_forder_01.ui.shoppingcard.ShoppingCardActionHandler;
import com.example.duong.android_forder_01.ui.shoppingcard.ShoppingCardContract;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Duong on 2/27/2017.
 */
public class ShoppingCardAdapter extends BaseExpandableListAdapter {
    private Context mContext;
    private List<ShoppingCard> mShoppingCards = new ArrayList<>();
    private ShoppingCardContract.Presenter mListener;

    public ShoppingCardAdapter(Context context, List<ShoppingCard> shoppingCards,
                               ShoppingCardContract.Presenter presenter) {
        mContext = context;
        mShoppingCards = shoppingCards;
        mListener = presenter;
    }

    @Override
    public int getGroupCount() {
        return mShoppingCards.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return mShoppingCards.get(groupPosition).getShoppingCardDetails().size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return mShoppingCards.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return mShoppingCards.get(groupPosition).getShoppingCardDetails().get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return mShoppingCards.get(groupPosition).getShopId();
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return mShoppingCards.get(groupPosition).getShoppingCardDetails().get(childPosition)
            .getProductId();
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
            convertView = layoutInflater.inflate(R.layout.item_parent_shopping_card, null);
        }
        ViewDataBinding mBinding = DataBindingUtil.bind(convertView);
        mBinding.setVariable(BR.shoppingCardShop, mShoppingCards.get(groupPosition));
        mBinding.setVariable(BR.actionHandler, new ShoppingCardActionHandler(mListener));
        mBinding.executePendingBindings();
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild,
                             View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) mContext
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.item_shopping_card, null);
        }
        ViewDataBinding mBinding = DataBindingUtil.bind(convertView);
        mBinding.setVariable(BR.shoppingCardItem, mShoppingCards.get(groupPosition)
            .getShoppingCardDetails().get(childPosition));
        mBinding.setVariable(BR.actionHandler, new ShoppingCardActionHandler(mListener));
        mBinding.executePendingBindings();
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }
}
