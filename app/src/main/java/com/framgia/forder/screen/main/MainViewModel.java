package com.framgia.forder.screen.main;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.support.annotation.IntDef;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.framgia.forder.BR;
import com.framgia.forder.R;

/**
 * Exposes the data to be used in the Main screen.
 */

public class MainViewModel extends BaseObservable implements MainContract.ViewModel {

    private MainContract.Presenter mPresenter;
    private MainViewPagerAdapter mViewPagerAdapter;
    @Tab
    private int mCurrentTab;

    public MainViewModel(MainViewPagerAdapter mainViewPagerAdapter) {
        mViewPagerAdapter = mainViewPagerAdapter;
    }

    @Override
    public void onStart() {
        mPresenter.onStart();
    }

    @Override
    public void onStop() {
        mPresenter.onStop();
    }

    @Override
    public void setPresenter(MainContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Bindable
    public int getCurrentTab() {
        return mCurrentTab;
    }

    public void setCurrentTab(@Tab int tab) {
        mCurrentTab = tab;
        notifyPropertyChanged(BR.currentTab);
    }

    public void onFooterClicked(View view) {
        switch (view.getId()) {
            case R.id.layout_home:
                setCurrentTab(Tab.TAB_HOME);
                break;
            case R.id.layout_seach:
                setCurrentTab(Tab.TAB_SEARCH);
                break;
            case R.id.img_top:
                setCurrentTab(Tab.TAB_CART);
                break;
            case R.id.layout_notify:
                setCurrentTab(Tab.TAB_NOTIFICATION);
                break;
            case R.id.layout_profile:
                setCurrentTab(Tab.TAB_PROFILE);
                break;
            default:
                break;
        }
        setTabSelected(view);
    }

    private void setTabSelected(View view) {
        ViewGroup viewGroup = null;
        if (view instanceof ImageView && !view.isSelected()) {
            view.setSelected(!view.isSelected());
            viewGroup = (ViewGroup) ((ViewGroup) view.getParent()).getChildAt(0);
        } else if (!(view instanceof ImageView)) {
            viewGroup = (ViewGroup) view.getParent();
            ((ViewGroup) viewGroup.getParent()).getChildAt(1).setSelected(false);
        }
        if (viewGroup == null) return;
        for (int i = 0; i < viewGroup.getChildCount(); i++) {
            View tab = viewGroup.getChildAt(i);
            tab.setSelected(tab == view);
        }
    }

    public MainViewPagerAdapter getViewPagerAdapter() {
        return mViewPagerAdapter;
    }

    @IntDef({ Tab.TAB_HOME, Tab.TAB_SEARCH, Tab.TAB_CART, Tab.TAB_NOTIFICATION, Tab.TAB_PROFILE })
    public @interface Tab {
        int TAB_HOME = 0;
        int TAB_SEARCH = 1;
        int TAB_CART = 2;
        int TAB_NOTIFICATION = 3;
        int TAB_PROFILE = 4;
    }
}
