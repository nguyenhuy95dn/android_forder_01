package com.example.duong.android_forder_01.ui.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.duong.android_forder_01.R;

/**
 * Created by FuckYou on 03/03/2017.
 */
public class DomainViewPagerAdapter extends FragmentPagerAdapter {
    private final int TAB_NUMBER = 2;
    private final int PRIVATE_DOMAIN_POSITION = 0;
    private final int PUBLIC_DOMAIN_POSITION = 1;
    private Context mContext;

    public DomainViewPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        mContext = context;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case PRIVATE_DOMAIN_POSITION:
                //TODO: Return PrivateDomainFragment
            case PUBLIC_DOMAIN_POSITION:
                //TODO: return PublicDomainFragment
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
            case PRIVATE_DOMAIN_POSITION:
                return mContext.getString(R.string.title_private_domain);
            case PUBLIC_DOMAIN_POSITION:
                return mContext.getString(R.string.title_public_domain);
            default:
                return null;
        }
    }
}
