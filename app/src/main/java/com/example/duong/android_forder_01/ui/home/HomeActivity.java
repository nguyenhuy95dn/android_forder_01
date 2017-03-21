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
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.duong.android_forder_01.R;
import com.example.duong.android_forder_01.data.model.Category;
import com.example.duong.android_forder_01.data.model.Domain;
import com.example.duong.android_forder_01.data.model.User;
import com.example.duong.android_forder_01.data.source.CategoryRepository;
import com.example.duong.android_forder_01.data.source.DomainReposity;
import com.example.duong.android_forder_01.data.source.ShoppingCardRepository;
import com.example.duong.android_forder_01.databinding.ActivityHomeBinding;
import com.example.duong.android_forder_01.ui.adapter.CategoryAdapter;
import com.example.duong.android_forder_01.ui.adapter.DomainAdapter;
import com.example.duong.android_forder_01.ui.adapter.ViewPagerAdapter;
import com.example.duong.android_forder_01.ui.domain.DomainActivity;
import com.example.duong.android_forder_01.ui.listproduct.ListProductActivity;
import com.example.duong.android_forder_01.ui.login.LoginActivity;
import com.example.duong.android_forder_01.ui.notification.NotificationActivity;
import com.example.duong.android_forder_01.ui.shopmanagement.ShopManagementActivity;
import com.example.duong.android_forder_01.ui.shoppingcard.ShoppingCardActivity;
import com.example.duong.android_forder_01.utils.SharedPreferencesUtils;

import java.util.ArrayList;
import java.util.List;

import static com.example.duong.android_forder_01.utils.Const.ID_DOMAIN;
import static com.example.duong.android_forder_01.utils.SharedPreferencesUtils.deleteUser;
import static com.example.duong.android_forder_01.utils.SharedPreferencesUtils.getCurrentDomain;
import static com.example.duong.android_forder_01.utils.SharedPreferencesUtils.loadUser;
import static com.example.duong.android_forder_01.utils.SharedPreferencesUtils.saveCurrentDomain;

public class HomeActivity extends AppCompatActivity implements HomeContract.View {
    public static TextView sTextNumberItem;
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
    private ObservableField<ArrayAdapter> mSpinnerAdapter = new ObservableField<>();
    private ObservableField<DomainAdapter> mDomainAdapter = new ObservableField<>();
    private Domain mDomain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivityHomeBinding = DataBindingUtil.setContentView(this, R.layout.activity_home);
        setPresenter(new HomePresenter(this, DomainReposity.getInstance(),
            CategoryRepository.getInstance(), ShoppingCardRepository.getInstance(this)));
        mHomPresenter.start();
    }

    @Override
    public void start() {
        mHomPresenter.getAllCategory(ID_DOMAIN, SharedPreferencesUtils.loadUser(this));
        mActivityHomeBinding.setActivityHome(this);
        initToolbar();
        initViewPager();
        mHomPresenter.getDomain(SharedPreferencesUtils.loadUser(this));
        initCategoryRecyclerView();
    }

    @Override
    public void setPresenter(HomeContract.Presenter presenter) {
        mHomPresenter = presenter;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_home, menu);
        initSpinner();
        MenuItem itemUser = menu.findItem(R.id.item_username);
        User user = loadUser(this);
        itemUser.setTitle(user != null ? user.getFullName() : "");
        View card = MenuItemCompat.getActionView(menu.findItem(R.id.item_shopping_card));
        sTextNumberItem = (TextView) card.findViewById(R.id.text_shopping_card);
        FrameLayout frameLayout = (FrameLayout) card.findViewById(R.id.frame_card);
        frameLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), ShoppingCardActivity.class));
            }
        });
        return true;
    }

    private void initSpinner() {
        mSpinnerAdapter
            .set(new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, mDomains));
        mActivityHomeBinding.spinner.setOnItemSelectedListener(
            new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int position,
                                           long l) {
                    //TODO: Load shop, category, product in domain
                    mDomain = mDomains.get(position);
                    saveCurrentDomain(getApplicationContext(), mDomain);
                    mHomPresenter.getCardItem(getCurrentDomain(getApplicationContext()).getId());
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {
                    // not require
                }
            });
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
            case R.id.item_shopping_card:
                startActivity(new Intent(this, ShoppingCardActivity.class));
                break;
            case R.id.item_logout:
                deleteUser(this);
                finish();
                startActivity(new Intent(this, LoginActivity.class));
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
        mDomainAdapter.set(new DomainAdapter(mDomains, this, mHomPresenter));
    }

    @Override
    public void showAllCategory(List<Category> list) {
        if (list == null) return;
        mCategories.addAll(list);
        mCategoryAdapter.get().notifyDataSetChanged();
    }

    @Override
    public void showDomain(List<Domain> domainList) {
        if (domainList == null) return;
        mDomains.clear();
        mDomains.addAll(domainList);
        mSpinnerAdapter.get().notifyDataSetChanged();
        mDomainAdapter.get().notifyDataSetChanged();
    }

    @Override
    public void showGetDataError() {
        // TODO show get data error
    }

    @Override
    public void showListProduct(Category categoryId) {
        startActivity(ListProductActivity.getListProductIntent(this, categoryId));
    }

    @Override
    public void showDomainPublic(Domain domain) {
        // TODO open domain public
    }

    public void updateCard(int numberItem) {
        sTextNumberItem.setText(String.valueOf(numberItem));
    }

    public ObservableField<CategoryAdapter> getCategoryAdapter() {
        return mCategoryAdapter;
    }

    public ObservableField<DomainAdapter> getDomainAdapter() {
        return mDomainAdapter;
    }

    public ObservableField<ArrayAdapter> getSpinnerAdapter() {
        return mSpinnerAdapter;
    }
}
