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
import com.example.duong.android_forder_01.data.model.ShoppingCardDetail;
import com.example.duong.android_forder_01.ui.shoppingcard.ShoppingCardActionHandler;
import com.example.duong.android_forder_01.ui.shoppingcard.ShoppingCardContract;

import java.util.List;

public class ShoppingCardAdapter
    extends RecyclerView.Adapter<ShoppingCardAdapter.ShoppingCardViewHolder> {
    private List<ShoppingCardDetail> mShoppingCardDetails;
    private LayoutInflater mLayoutInflater;
    private ShoppingCardContract.Presenter mListener;

    public ShoppingCardAdapter(List<ShoppingCardDetail> list, Context context,
                               ShoppingCardContract.Presenter
                                   presenter) {
        mShoppingCardDetails = list;
        mLayoutInflater = LayoutInflater.from(context);
        mListener = presenter;
    }

    @Override
    public ShoppingCardViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView =
            mLayoutInflater.inflate(R.layout.item_shopping_card, parent, false);
        return new ShoppingCardViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ShoppingCardViewHolder holder, int position) {
        holder.bindData(mShoppingCardDetails.get(position));
    }

    @Override
    public int getItemCount() {
        return mShoppingCardDetails != null ? mShoppingCardDetails.size() : 0;
    }

    public class ShoppingCardViewHolder extends RecyclerView.ViewHolder {
        private ViewDataBinding mDataBinding;

        public ShoppingCardViewHolder(View view) {
            super(view);
            mDataBinding = DataBindingUtil.bind(view);
        }

        public void bindData(ShoppingCardDetail shoppingCardDetail) {
            if (shoppingCardDetail == null) return;
            mDataBinding.setVariable(BR.shoppingCardDetail, shoppingCardDetail);
            mDataBinding
                .setVariable(BR.actionHandler, new ShoppingCardActionHandler(mListener));
            mDataBinding.executePendingBindings();
        }
    }
}
