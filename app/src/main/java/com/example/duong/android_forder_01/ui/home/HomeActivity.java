package com.example.duong.android_forder_01.ui.home;

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
import com.example.duong.android_forder_01.ui.adapter.ViewPagerAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeActivity extends AppCompatActivity implements HomeContract.View {
    @BindView(R.id.toolbar_home)
    Toolbar mToolbar;
    @BindView(R.id.drawer_layout)
    DrawerLayout mDrawerLayout;
    @BindView(R.id.tab_layout_home)
    TabLayout mTabLayout;
    @BindView(R.id.view_pager_home)
    private ViewPager mViewPager;
    private Spinner mSpinner;
    private ActionBarDrawerToggle mDrawerToggle;
    private HomeContract.Presenter mHomPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        setPresenter(new HomePresenter(this));
        mHomPresenter.start();
    }

    @Override
    public void start() {
        ButterKnife.bind(this);
        initToolbar();
        initViewPager();
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
        FragmentManager manager = getSupportFragmentManager();
        ViewPagerAdapter adapter = new ViewPagerAdapter(manager, this);
        mViewPager.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        mTabLayout.setupWithViewPager(mViewPager);
    }

    @Override
    public void initToolbar() {
        mToolbar.setTitle("");
        setSupportActionBar(mToolbar);
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.title_open, R.string
            .title_close);
        mDrawerLayout.addDrawerListener(mDrawerToggle);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mDrawerToggle.syncState();
    }
}
