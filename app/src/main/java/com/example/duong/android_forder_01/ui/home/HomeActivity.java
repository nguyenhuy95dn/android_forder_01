package com.example.duong.android_forder_01.ui.home;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.databinding.ObservableField;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Spinner;

import com.example.duong.android_forder_01.R;
import com.example.duong.android_forder_01.data.model.Category;
import com.example.duong.android_forder_01.data.model.Domain;
import com.example.duong.android_forder_01.data.source.CategoryRepository;
import com.example.duong.android_forder_01.data.source.DomainReposity;
import com.example.duong.android_forder_01.databinding.ActivityHomeBinding;
import com.example.duong.android_forder_01.ui.adapter.CategoryAdapter;
import com.example.duong.android_forder_01.ui.adapter.ViewPagerAdapter;
import com.example.duong.android_forder_01.ui.domain.DomainActivity;
import com.example.duong.android_forder_01.ui.listproduct.ListProductActivity;
import com.example.duong.android_forder_01.ui.notification.NotificationActivity;
import com.example.duong.android_forder_01.ui.shopmanagement.ShopManagementActivity;

import java.util.ArrayList;
import java.util.List;

import static com.example.duong.android_forder_01.utils.Const.ID_DOMAIN;
import static com.example.duong.android_forder_01.utils.Const.ID_USER;

public class HomeActivity extends AppCompatActivity implements HomeContract.View {
    private Toolbar mToolbar;
    private DrawerLayout mDrawerLayout;
    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private Spinner mSpinner;
    private ActionBarDrawerToggle mDrawerToggle;
    private HomeContract.Presenter mHomPresenter;
    private ActivityHomeBinding mActivityHomeBinding;
    private List<Category> mCategories = new ArrayList<>();
    private ObservableField<CategoryAdapter> mCategoryAdapter = new ObservableField<>();
    private List<Domain> mDomains = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivityHomeBinding = DataBindingUtil.setContentView(this, R.layout.activity_home);
        setPresenter(new HomePresenter(this, DomainReposity.getInstance(),
            CategoryRepository.getInstance()));
        mHomPresenter.start();
    }

    @Override
    public void start() {
        mHomPresenter.getAllCategory(ID_DOMAIN);
        mActivityHomeBinding.setActivityHome(this);
        initToolbar();
        initViewPager();
        mHomPresenter.getDomain(ID_USER);
        initCategoryRecyclerView();
    }

    @Override
    public void setPresenter(HomeContract.Presenter presenter) {
        mHomPresenter = presenter;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_home, menu);
        MenuItem item = menu.findItem(R.id.item_spinner);
        mSpinner = (Spinner) MenuItemCompat.getActionView(item);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (mDrawerToggle.onOptionsItemSelected(item)) return true;
        switch (item.getItemId()) {
            case R.id.item_notification:
                startActivity(new Intent(this, NotificationActivity.class));
                break;
            case R.id.item_shop_management:
                startActivity(new Intent(this, ShopManagementActivity.class));
                break;
            case R.id.item_domain:
                startActivity(new Intent(this, DomainActivity.class));
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void initViewPager() {
        mTabLayout = mActivityHomeBinding.tabLayoutHome;
        mViewPager = mActivityHomeBinding.viewPagerHome;
        FragmentManager manager = getSupportFragmentManager();
        ViewPagerAdapter adapter = new ViewPagerAdapter(manager, this);
        mViewPager.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        mTabLayout.setupWithViewPager(mViewPager);
    }

    @Override
    public void initToolbar() {
        mToolbar = mActivityHomeBinding.toolbarHome;
        mDrawerLayout = mActivityHomeBinding.drawerLayout;
        mToolbar.setTitle("");
        setSupportActionBar(mToolbar);
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.title_open, R.string
            .title_close);
        mDrawerLayout.addDrawerListener(mDrawerToggle);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mDrawerToggle.syncState();
    }

    @Override
    public void initCategoryRecyclerView() {
        mCategoryAdapter.set(new CategoryAdapter(mCategories, this, mHomPresenter));
    }

    @Override
    public void showAllCategory(List<Category> list) {
        if (list == null) return;
        mCategories.addAll(list);
    }

    @Override
    public void showDomain(List<Domain> domainList) {
        if (domainList == null) return;
        mDomains.clear();
        mDomains.addAll(domainList);
    }

    @Override
    public void showGetDataError() {
        // TODO show get data error
    }

    @Override
    public void showListProduct(int categoryId) {
        startActivity(ListProductActivity.getListProductIntent(this, categoryId));
    }

    public ObservableField<CategoryAdapter> getCategoryAdapter() {
        return mCategoryAdapter;
    }
}
