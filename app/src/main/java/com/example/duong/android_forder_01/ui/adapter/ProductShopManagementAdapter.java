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
import com.example.duong.android_forder_01.data.model.Product;
import com.example.duong.android_forder_01.ui.shopmanagementdetail.productshop.ProductShopFragmentActionHandler;
import com.example.duong.android_forder_01.ui.shopmanagementdetail.productshop.ProductShopFragmentContract;

import java.util.List;

/**
 * Created by tri on 07/03/2017.
 */
public class ProductShopManagementAdapter
    extends RecyclerView.Adapter<ProductShopManagementAdapter.ProductViewHolder> {
    private List<Product> mListProduct;
    private LayoutInflater mLayoutInflater;
    private ProductShopFragmentContract.Presenter mListener;

    public ProductShopManagementAdapter(List<Product> list, Context context,
                                        ProductShopFragmentContract.Presenter presenter) {
        mListProduct = list;
        mLayoutInflater = LayoutInflater.from(context);
        mListener = presenter;
    }

    @Override
    public ProductShopManagementAdapter.ProductViewHolder onCreateViewHolder(ViewGroup parent,
                                                                             int viewType) {
        View itemView =
            mLayoutInflater.inflate(R.layout.item_product_shop_management, parent, false);
        return new ProductShopManagementAdapter.ProductViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ProductViewHolder holder, int position) {
        holder.bindData(mListProduct.get(position));
    }

    @Override
    public int getItemCount() {
        return mListProduct != null ? mListProduct.size() : 0;
    }

    public class ProductViewHolder extends RecyclerView.ViewHolder {
        private ViewDataBinding mDataBinding;

        public ProductViewHolder(View view) {
            super(view);
            mDataBinding = DataBindingUtil.bind(view);
        }

        public void bindData(Product product) {
            if (product == null) return;
            mDataBinding.setVariable(BR.product, product);
            mDataBinding
                .setVariable(BR.actionHandler, new ProductShopFragmentActionHandler(mListener));
            mDataBinding.executePendingBindings();
        }
    }
}
