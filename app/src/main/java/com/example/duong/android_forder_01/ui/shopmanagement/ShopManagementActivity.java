package com.example.duong.android_forder_01.ui.shopmanagement;

import android.databinding.DataBindingUtil;
import android.databinding.ObservableField;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.duong.android_forder_01.R;
import com.example.duong.android_forder_01.data.model.ShopManagement;
import com.example.duong.android_forder_01.databinding.ActivityShopManagementBinding;
import com.example.duong.android_forder_01.ui.adapter.ShopManagementAdapter;
import com.example.duong.android_forder_01.utils.BaseActivity;

import java.util.ArrayList;
import java.util.List;

public class ShopManagementActivity extends BaseActivity implements ShopManagementContract.View {
    private ShopManagementContract.Presenter mPresenter;
    private ActivityShopManagementBinding mBinding;
    private List<ShopManagement> mShopManagementList = new ArrayList<>();
    private ObservableField<ShopManagementAdapter> mShopManagementAdapter = new ObservableField<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout
            .activity_shop_management);
        setPresenter(new ShopManagementPresenter(this));
        mPresenter.start();
    }

    @Override
    public void showDetailShop(ShopManagement shopManagement) {
        // TODO open DetailShop Activity
    }

    @Override
    public void showAllShop(List<ShopManagement> list) {
        if (list == null) return;
        mShopManagementList.addAll(list);
    }

    @Override
    public void showGetDataError() {
        // TODO show get data Error
    }

    @Override
    public void start() {
        getSupportActionBar().setTitle(getResources().getString((R.string.title_your_shop)));
        mBinding.setShopManagement(this);
        mShopManagementAdapter
            .set(new ShopManagementAdapter(this, mShopManagementList, mPresenter));
    }

    @Override
    public void setPresenter(ShopManagementContract.Presenter presenter) {
        mPresenter = presenter;
    }

    public ObservableField<ShopManagementAdapter> getShopManagementAdapter() {
        return mShopManagementAdapter;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_shop_management, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.item_shop_management) {
            // TODO open Activity request shop into domain
        }
        return super.onOptionsItemSelected(item);
    }
}
