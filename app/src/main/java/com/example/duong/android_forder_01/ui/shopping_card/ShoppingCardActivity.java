package com.example.duong.android_forder_01.ui.shopping_card;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.duong.android_forder_01.BR;
import com.example.duong.android_forder_01.R;
import com.example.duong.android_forder_01.data.model.ShoppingCard;
import com.example.duong.android_forder_01.data.model.ShoppingCardDetail;
import com.example.duong.android_forder_01.databinding.ActivityShoppingCardBinding;
import com.example.duong.android_forder_01.ui.adapter.ShoppingCardAdapter;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCardActivity extends AppCompatActivity implements ShoppingCardContract.View {
    private ActivityShoppingCardBinding mBinding;
    private ShoppingCardContract.Presenter mPresenter;
    private RecyclerView mRecyclerView;
    private ShoppingCard mShoppingCard;
    private ShoppingCardAdapter mShoppingCardAdapter;
    private List<ShoppingCardDetail> mShoppingCardDetailList = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout
            .activity_shopping_card);
        setPresenter(new ShoppingCardPresenter(this));
        mPresenter.start();
    }

    @Override
    public void start() {
        mBinding.setVariable(BR.actionHandler, new ShoppingCardActionHandler
            (mPresenter));
        mBinding.setVariable(BR.shoppingCard, mShoppingCard);
        initRecyclerView();
    }

    @Override
    public void setPresenter(ShoppingCardContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void initRecyclerView() {
        mRecyclerView = mBinding.recyclerViewShoppingCard;
        mShoppingCardAdapter = new ShoppingCardAdapter(mShoppingCardDetailList, this, mPresenter);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setAdapter(mShoppingCardAdapter);
    }
}
