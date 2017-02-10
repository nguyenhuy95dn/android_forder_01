package com.example.duong.android_forder_01.ui.home;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Spinner;

import com.example.duong.android_forder_01.R;
import com.example.duong.android_forder_01.data.model.Category;
import com.example.duong.android_forder_01.databinding.ActivityHomeBinding;
import com.example.duong.android_forder_01.ui.adapter.CategoryAdapter;
import com.example.duong.android_forder_01.ui.adapter.ViewPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity implements HomeContract.View {
    private Toolbar mToolbar;
    private DrawerLayout mDrawerLayout;
    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private Spinner mSpinner;
    private RecyclerView mRecyclerViewCategory;
    private ActionBarDrawerToggle mDrawerToggle;
    private HomeContract.Presenter mHomPresenter;
    private ActivityHomeBinding mActivityHomeBinding;
    private CategoryAdapter mCategoryAdapter;
    private List<Category> mCategories = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivityHomeBinding = DataBindingUtil.setContentView(this, R.layout.activity_home);
        setPresenter(new HomePresenter(this));
        mHomPresenter.start();
    }

    @Override
    public void start() {
        initToolbar();
        initViewPager();
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
        mRecyclerViewCategory = mActivityHomeBinding.recyclerViewCategory;
        mCategoryAdapter = new CategoryAdapter(mCategories, this, mHomPresenter);
        mRecyclerViewCategory.setHasFixedSize(true);
        mRecyclerViewCategory.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerViewCategory.setItemAnimator(new DefaultItemAnimator());
        mRecyclerViewCategory.setAdapter(mCategoryAdapter);
    }
}
