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
import com.example.duong.android_forder_01.ui.home.product.ProductContract;
import com.example.duong.android_forder_01.ui.home.product.ProductItemActionHandler;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {
    private List<Product> mListProduct;
    private LayoutInflater mLayoutInflater;
    private ProductContract.Presenter mListener;

    public ProductAdapter(List<Product> list, Context context,
                          ProductContract.Presenter presenter) {
        mListProduct = list;
        mLayoutInflater = LayoutInflater.from(context);
        mListener = presenter;
    }

    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView =
            mLayoutInflater.inflate(R.layout.item_product, parent, false);
        return new ProductViewHolder(itemView);
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
            mDataBinding.setVariable(BR.actionHandler, new ProductItemActionHandler(mListener));
            mDataBinding.executePendingBindings();
        }
    }
}
