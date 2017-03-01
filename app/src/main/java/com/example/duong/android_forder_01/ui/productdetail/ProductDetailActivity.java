package com.example.duong.android_forder_01.ui.productdetail;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.databinding.ObservableField;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.example.duong.android_forder_01.R;
import com.example.duong.android_forder_01.data.model.Product;
import com.example.duong.android_forder_01.databinding.ActivityProductDetailBinding;
import com.example.duong.android_forder_01.ui.adapter.ProductRelateAdapter;
import com.example.duong.android_forder_01.utils.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import static com.example.duong.android_forder_01.utils.Const.KeyIntent.EXTRA_PRODUCT;

public class ProductDetailActivity extends BaseActivity implements ProductDetailContract.View {
    private ActivityProductDetailBinding mBinding;
    private ProductDetailContract.Presenter mPresenter;
    private Toolbar mToolbar;
    private Product mProduct;
    private ObservableField<ProductRelateAdapter> mProductAdapter = new ObservableField<>();
    private List<Product> mProducts = new ArrayList<>();

    public static Intent getProductDetailIntent(Context context, Product product) {
        Intent intent = new Intent(context, ProductDetailActivity.class);
        intent.putExtra(EXTRA_PRODUCT, product);
        return intent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_product_detail);
        setPresenter(new ProductDetailPresenter(this));
        mPresenter.start();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_home, menu);
        MenuItem item = menu.findItem(R.id.item_spinner);
        item.setVisible(false);
        return true;
    }

    @Override
    public void initToolbar() {
        mToolbar = mBinding.toolbarProductDetail;
        mToolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public void initRecyclerRelateProduct() {
        mProductAdapter.set(new ProductRelateAdapter(mProducts, this, mPresenter));
    }

    @Override
    public void start() {
        mProduct = (Product) getIntent().getSerializableExtra(EXTRA_PRODUCT);
        mBinding.setProductDetail(this);
        mBinding.setActionHandler(new ProductDetailActionHandler
            (mPresenter));
        initToolbar();
        initRecyclerRelateProduct();
    }

    @Override
    public void setPresenter(ProductDetailContract.Presenter presenter) {
        mPresenter = presenter;
    }

    public ObservableField<ProductRelateAdapter> getProductAdapter() {
        return mProductAdapter;
    }

    public Product getProduct() {
        return mProduct;
    }
}
