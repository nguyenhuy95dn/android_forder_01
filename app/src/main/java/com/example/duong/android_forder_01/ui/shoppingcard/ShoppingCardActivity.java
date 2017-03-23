package com.example.duong.android_forder_01.ui.shoppingcard;

import android.content.DialogInterface;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.databinding.ObservableField;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.example.duong.android_forder_01.R;
import com.example.duong.android_forder_01.data.model.ShoppingCard;
import com.example.duong.android_forder_01.data.source.ShoppingCardRepository;
import com.example.duong.android_forder_01.databinding.ActivityShoppingCardBinding;
import com.example.duong.android_forder_01.ui.adapter.ShoppingCardAdapter;
import com.example.duong.android_forder_01.ui.home.HomeActivity;
import com.example.duong.android_forder_01.ui.yourorder.YourOrderActivity;

import java.util.ArrayList;
import java.util.List;

import static com.example.duong.android_forder_01.utils.Const.FORMAT_PRICE;
import static com.example.duong.android_forder_01.utils.Const.UNIT_MONEY;
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
        setPresenter(new ShoppingCardPresenter(this, ShoppingCardRepository.getInstance(this),
            this));
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
    }

    @Override
    public void loadShoppingCardCompleted(List<ShoppingCard> list, double totalPrice) {
        if (list == null) return;
        mShoppingCards.clear();
        mBinding.textTotal.setText("");
        mShoppingCards.addAll(list);
        mShoppingCardAdapter.get().notifyDataSetChanged();
        mTotalPrice = String.format(FORMAT_PRICE, totalPrice) + UNIT_MONEY;
        mBinding.textTotal.setText(mTotalPrice);
    }

    @Override
    public void loadShoppingCardError() {
        Snackbar.make(findViewById(android.R.id.content),
            getString(R.string.title_shopping_card_error), Snackbar
                .LENGTH_LONG)
            .show();
    }

    @Override
    public void showConfirmOrder(String message) {
        mPresenter.loadShoppingCard(getCurrentDomain(this).getId());
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.title_confirm_order)
            .setCancelable(true)
            .setMessage(message)
            .setPositiveButton(getString(R.string.action_continue),
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        mPresenter.acceptOrder(mShoppingCards);
                        mPresenter.deleteShoppingCardOfDomain(
                            getCurrentDomain(getApplicationContext()).getId());
                        mPresenter
                            .loadShoppingCard(getCurrentDomain(getApplicationContext()).getId());
                        startActivity(new Intent(getApplicationContext(), YourOrderActivity.class));
                    }
                })
            .setNegativeButton(getString(R.string.action_cancel),
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mPresenter
                            .loadShoppingCard(getCurrentDomain(getApplicationContext()).getId());
                    }
                })
            .show();
    }

    @Override
    public void updateCard(int numberItem) {
        HomeActivity.sTextNumberItem.setText(String.valueOf(numberItem));
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

    public List<ShoppingCard> getShoppingCards() {
        return mShoppingCards;
    }
}
