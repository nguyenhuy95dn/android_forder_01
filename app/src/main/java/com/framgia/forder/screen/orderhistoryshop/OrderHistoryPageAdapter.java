package com.framgia.forder.screen.orderhistoryshop;

import android.content.Context;
import android.support.annotation.IntDef;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import com.framgia.forder.R;
import com.framgia.forder.screen.orderhistoryshop.listdoneorders.ListDoneOrdersFragment;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by levutantuan on 6/11/17.
 */

public class OrderHistoryPageAdapter extends FragmentPagerAdapter {

    private static final int TAB_NUMBER = 2;
    private final Context mContext;
    private final List<Fragment> mFragments;

    OrderHistoryPageAdapter(Context context, FragmentManager fragmentManager) {
        super(fragmentManager);
        mContext = context;
        mFragments = new ArrayList<>();
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        switch (position) {
            case OrderHistoryPageAdapter.OrderHistoryPageResultsTab.TAB_ORDER_ACCEPTED:
                fragment = ListDoneOrdersFragment.newInstance();
                mFragments.add(OrderHistoryPageResultsTab.TAB_ORDER_ACCEPTED, fragment);
                break;
            case OrderHistoryPageAdapter.OrderHistoryPageResultsTab.TAB_ORDER_REJECT:
                //TODO Fragment List Orders Reject (Edit later)
                fragment = ListDoneOrdersFragment.newInstance();
                mFragments.add(OrderHistoryPageResultsTab.TAB_ORDER_ACCEPTED, fragment);
                break;
            default:
                break;
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return TAB_NUMBER;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case OrderHistoryPageAdapter.OrderHistoryPageResultsTab.TAB_ORDER_ACCEPTED:
                return mContext.getString(R.string.done_orders);
            case OrderHistoryPageAdapter.OrderHistoryPageResultsTab.TAB_ORDER_REJECT:
                return mContext.getString(R.string.rejected_orders);
            default:
                return null;
        }
    }

    public Fragment getFragment(@OrderHistoryPageAdapter.OrderHistoryPageResultsTab int position) {
        return mFragments.get(position);
    }

    @IntDef({
            OrderHistoryPageAdapter.OrderHistoryPageResultsTab.TAB_ORDER_ACCEPTED,
            OrderHistoryPageAdapter.OrderHistoryPageResultsTab.TAB_ORDER_REJECT
    })
    public @interface OrderHistoryPageResultsTab {
        int TAB_ORDER_ACCEPTED = 0;
        int TAB_ORDER_REJECT = 1;
    }
}
