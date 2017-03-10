package com.example.duong.android_forder_01.ui.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.duong.android_forder_01.R;
import com.example.duong.android_forder_01.data.model.Domain;

/**
 * Created by Vinh on 07/03/2017.
 */
public class DetailDomainViewPageAdapter extends FragmentPagerAdapter {
    private final int TAB_NUMBER = 3;
    private final int MEMBER_DOMAIN_POSITION = 0;
    private final int SHOP_DOMAIN_POSITION = 1;
    private final int REQUEST_DOMAIN_POSITION = 2;
    private Context mContext;
    private Domain mDomain;

    public DetailDomainViewPageAdapter(FragmentManager fm, Context context, Domain domain) {
        super(fm);
        mContext = context;
        mDomain = domain;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case MEMBER_DOMAIN_POSITION:
                //TODO: return MemberDomainFragment.newInstance(mDomain);
            case SHOP_DOMAIN_POSITION:
                //TODO: return ShopDomainFragment.newInstance(mDomain);
            case REQUEST_DOMAIN_POSITION:
                //TODO: RequestDomainFragment.newInstance(mDomain);
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return TAB_NUMBER;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case MEMBER_DOMAIN_POSITION:
                return mContext.getString(R.string.title_member_domain);
            case SHOP_DOMAIN_POSITION:
                return mContext.getString(R.string.title_shop_domain);
            case REQUEST_DOMAIN_POSITION:
                return mContext.getString(R.string.title_request_domain);
            default:
                return null;
        }
    }
}