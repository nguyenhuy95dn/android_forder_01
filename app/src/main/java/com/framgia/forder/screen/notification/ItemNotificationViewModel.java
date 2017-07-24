package com.framgia.forder.screen.notification;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.graphics.Color;
import com.framgia.forder.BR;
import com.framgia.forder.R;
import com.framgia.forder.data.model.Notification;
import com.framgia.forder.data.model.Product;
import com.framgia.forder.data.model.Shop;
import com.framgia.forder.data.model.ShopManagement;
import com.framgia.forder.screen.BaseRecyclerViewAdapter;
import com.framgia.forder.screen.domainmanagement.DomainManagementFragment;
import com.framgia.forder.screen.orderhistory.OrderHistoryFragment;
import com.framgia.forder.screen.orderhistoryshop.OrderHistoryShopFragment;
import com.framgia.forder.screen.ordershop.OrderShopFragment;
import com.framgia.forder.screen.productdetail.ProductDetailFragment;
import com.framgia.forder.screen.shopinfo.ShopinfoFragment;
import com.framgia.forder.screen.shopmanagement.ShopManagementFragment;
import com.framgia.forder.utils.navigator.Navigator;

import static com.framgia.forder.utils.StatusCode.NOTIFICATION_ORDER;
import static com.framgia.forder.utils.StatusCode.NOTIFICATION_ORDER_PRODUCT;
import static com.framgia.forder.utils.StatusCode.NOTIFICATION_PRODUCT;
import static com.framgia.forder.utils.StatusCode.NOTIFICATION_SHOP;
import static com.framgia.forder.utils.StatusCode.NOTIFICATION_SHOP_DOMAIN;
import static com.framgia.forder.utils.StatusCode.NOTIFICATION_USER;
import static com.framgia.forder.utils.StatusCode.NOTIFICATION_USER_DOMAIN;

/**
 * Created by levutantuan on 7/20/17.
 */

public class ItemNotificationViewModel extends BaseObservable {

    private static final String TAG = "NotificationPageFragment";

    private final Navigator mNavigator;
    private final Notification mNotification;
    private final BaseRecyclerViewAdapter.OnRecyclerViewItemClickListener<Object>
            mItemClickListener;
    private int mColorBackgroundNotification;

    ItemNotificationViewModel(Navigator navigator, Notification notification,
            BaseRecyclerViewAdapter.OnRecyclerViewItemClickListener<Object> itemClickListener) {
        mNavigator = navigator;
        mNotification = notification;
        mItemClickListener = itemClickListener;
        initColorNotification();
    }

    private void initColorNotification() {
        if (mNotification != null) {
            if (mNotification.isRead()) {
                setColorBackgroundNotification(Color.WHITE);
            } else {
                setColorBackgroundNotification(Color.WHITE);
            }
        }
    }

    public String getNotificationImage() {
        return "";
    }

    public String getNotificationTitle() {
        return mNotification.getMessage();
    }

    public String getNotificationTime() {
        return mNotification.getTimeNotificationFormat();
    }

    @Bindable
    public int getColorBackgroundNotification() {
        return mColorBackgroundNotification;
    }

    private void setColorBackgroundNotification(int colorBackgroundNotification) {
        mColorBackgroundNotification = colorBackgroundNotification;
        notifyPropertyChanged(BR.colorBackgroundNotification);
    }

    public void onItemClicked() {
        if (mItemClickListener == null) {
            return;
        }
        mItemClickListener.onItemRecyclerViewClick(mNotification);
        initSelectItemNotification();
    }

    private void initSelectItemNotification() {
        final ShopManagement shopManagement = new ShopManagement();
        final Shop shop = new Shop();
        final Product product = new Product();
        final int evenId = mNotification.getIdEvenTable();
        product.setId(evenId);
        shop.setId(evenId);
        shopManagement.setShop(shop);

        switch (mNotification.getTypeEvenTable()) {
            case NOTIFICATION_SHOP:
                mNavigator.goNextChildFragment(R.id.layout_content,
                        ShopManagementFragment.newInstance(), true, Navigator.RIGHT_LEFT, TAG);
                mNavigator.showToastCustomActivity(R.string.ok);
                break;
            case NOTIFICATION_ORDER:
                mNavigator.goNextChildFragment(R.id.layout_content,
                        OrderShopFragment.newInstance(shopManagement), true, Navigator.RIGHT_LEFT,
                        TAG);
                break;
            case NOTIFICATION_ORDER_PRODUCT:
                mNavigator.goNextChildFragment(R.id.layout_content,
                        OrderHistoryFragment.newInstance(), true, Navigator.RIGHT_LEFT, TAG);
                break;
            case NOTIFICATION_PRODUCT:
                mNavigator.goNextChildFragment(R.id.layout_content,
                        ProductDetailFragment.newInstance(product), true, Navigator.RIGHT_LEFT,
                        TAG);
                break;
            case NOTIFICATION_SHOP_DOMAIN:
                mNavigator.goNextChildFragment(R.id.layout_content,
                        ShopinfoFragment.newInstance(shopManagement), true, Navigator.RIGHT_LEFT,
                        TAG);
                break;
            case NOTIFICATION_USER:
                mNavigator.goNextChildFragment(R.id.layout_content,
                        OrderHistoryShopFragment.newInstance(shopManagement), true,
                        Navigator.RIGHT_LEFT, TAG);
                break;
            case NOTIFICATION_USER_DOMAIN:
                mNavigator.goNextChildFragment(R.id.layout_content,
                        DomainManagementFragment.newInstance(), true, Navigator.RIGHT_LEFT, TAG);
                break;
            default:
                break;
        }
    }
}
