package com.example.duong.android_forder_01.ui.shoppingcard;

import android.databinding.DataBindingUtil;
import android.databinding.ObservableField;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.example.duong.android_forder_01.R;
import com.example.duong.android_forder_01.data.model.ShoppingCard;
import com.example.duong.android_forder_01.data.source.ShoppingCardRepository;
import com.example.duong.android_forder_01.databinding.ActivityShoppingCardBinding;
import com.example.duong.android_forder_01.ui.adapter.ShoppingCardAdapter;

import java.util.ArrayList;
import java.util.List;

import static com.example.duong.android_forder_01.utils.Const.FORMAT_PRICE;
import static com.example.duong.android_forder_01.utils.SharedPreferencesUtils.getCurrentDomain;

public class ShoppingCardActivity extends AppCompatActivity implements ShoppingCardContract.View {
    private ActivityShoppingCardBinding mBinding;
    private ShoppingCardContract.Presenter mPresenter;
    private List<ShoppingCard> mShoppingCards = new ArrayList<>();
    private ObservableField<ShoppingCardAdapter> mShoppingCardAdapter = new ObservableField<>();
    private String mTotalPrice;
    private Toolbar mToolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout
            .activity_shopping_card);
        setPresenter(new ShoppingCardPresenter(this, ShoppingCardRepository.getInstance(this)));
        mPresenter.start();
    }

    @Override
    public void start() {
        initRecyclerView();
        initToolbar();
        mPresenter.loadShoppingCard(getCurrentDomain(this).getId());
        mBinding.setShoppingCard(this);
        mBinding.setActionHandler(new ShoppingCardActionHandler
            (mPresenter, this));
    }

    @Override
    public void setPresenter(ShoppingCardContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void initRecyclerView() {
        mShoppingCardAdapter
            .set(new ShoppingCardAdapter(this, mShoppingCards, mPresenter));
    }

    @Override
    public void initToolbar() {
        mToolbar = mBinding.toolbarShoppingCard;
        mToolbar.setTitle(R.string.title_shopping_card);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public void loadShoppingCardCompleted(List<ShoppingCard> list, double totalPrice) {
        mShoppingCards.clear();
        if (list == null) return;
        mShoppingCards.addAll(list);
        mShoppingCardAdapter.get().notifyDataSetChanged();
        mTotalPrice = String.format(FORMAT_PRICE, totalPrice);
    }

    @Override
    public void loadShoppingCardError() {
        Snackbar.make(findViewById(android.R.id.content),
            getString(R.string.title_shopping_card_error), Snackbar
                .LENGTH_LONG)
            .show();
    }

    public ObservableField<ShoppingCardAdapter> getShoppingCardAdapter() {
        return mShoppingCardAdapter;
    }

    public String getTotalPrice() {
        return mTotalPrice;
    }

    public void setTotalPrice(String totalPrice) {
        mTotalPrice = totalPrice;
    }
}
