package com.framgia.forder.screen.profilepage;

import com.framgia.forder.R;
import com.framgia.forder.data.model.User;
import com.framgia.forder.screen.domainmanagement.DomainManagementFragment;
import com.framgia.forder.screen.login.LoginActivity;
import com.framgia.forder.screen.notificationsetting.NotificationSettingFragment;
import com.framgia.forder.screen.orderhistory.OrderHistoryFragment;
import com.framgia.forder.screen.ordermanagement.OrderManagementActivity;
import com.framgia.forder.screen.profilepage.profiledetail.ProfileDetailFragment;
import com.framgia.forder.screen.shopmanagement.ShopManagementFragment;
import com.framgia.forder.utils.navigator.Navigator;

/**
 * Exposes the data to be used in the ProfileFragment screen.
 */

public class ProfilePageViewModel implements ProfilePageContract.ViewModel {
    private static final String TAG = "ProfileDetailFragment";

    private final Navigator mNavigator;
    private ProfilePageContract.Presenter mPresenter;
    private User mUser;

    ProfilePageViewModel(Navigator navigator) {
        mNavigator = navigator;
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
    public void setPresenter(ProfilePageContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void onGetProfileUserSuccess(User user) {
        if (user == null) {
            return;
        }
        mUser = user;
    }

    public String getAvatar() {
        if (mUser.getAvatar() != null && mUser.getAvatar().getImage() != null) {
            return mUser.getAvatar().getImage().getUrl();
        }
        return "";
    }

    public String getUsername() {
        return mUser != null ? mUser.getName() : "";
    }

    public String getEmail() {
        return mUser != null ? mUser.getEmail() : "";
    }

    public void onClickProfileDetail() {
        mNavigator.goNextChildFragment(R.id.layout_content, ProfileDetailFragment.newInstance(),
                true, Navigator.RIGHT_LEFT, TAG);
    }

    public void onClickHistoryOrder() {
        mNavigator.goNextChildFragment(R.id.layout_content, OrderHistoryFragment.newInstance(),
                true, Navigator.RIGHT_LEFT, TAG);
    }

    public void onClickYourOrder() {
        mNavigator.startActivity(OrderManagementActivity.class);
    }

    public void onClickShopManagement() {
        mNavigator.goNextChildFragment(R.id.layout_content, ShopManagementFragment.newInstance(),
                true, Navigator.RIGHT_LEFT, TAG);
    }

    public void onClickDomainManagement() {
        mNavigator.goNextChildFragment(R.id.layout_content, DomainManagementFragment.newInstance(),
                true, Navigator.RIGHT_LEFT, "DomainManagementFragment");
    }

    public void onLogoutClicked() {
        mPresenter.clearData();
        mNavigator.startActivity(LoginActivity.class);
        mNavigator.finishActivity();
    }

    public void onClickNotificationSetting() {
        mNavigator.goNextChildFragment(R.id.layout_content,
                NotificationSettingFragment.newInstance(), true, Navigator.RIGHT_LEFT,
                "NotificationSettingFragment");
    }
}
