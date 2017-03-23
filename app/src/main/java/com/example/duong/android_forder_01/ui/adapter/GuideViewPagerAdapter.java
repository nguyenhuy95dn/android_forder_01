package com.example.duong.android_forder_01.ui.adapter;

import android.content.Context;
import android.graphics.ColorFilter;
import android.graphics.LightingColorFilter;
import android.graphics.drawable.Drawable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.ImageSpan;

import com.example.duong.android_forder_01.R;
import com.example.duong.android_forder_01.data.model.Guide;
import com.example.duong.android_forder_01.ui.home.guide.GuideFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tri on 21/03/2017.
 */
public class GuideViewPagerAdapter extends FragmentPagerAdapter {
    private static final int SIZE = 70;
    private Context mContext;
    private List<Guide> mGuideList = new ArrayList<>();

    public GuideViewPagerAdapter(FragmentManager fm, Context context, List<Guide> guide) {
        super(fm);
        mContext = context;
        mGuideList = guide;
    }

    @Override
    public Fragment getItem(int position) {
        return GuideFragment.newInstance(mGuideList.get(position));
    }

    public SpannableStringBuilder customIconTabHost(Drawable drawable) {
        ImageSpan imageSpan;
        SpannableStringBuilder stringBuilder = new SpannableStringBuilder(" ");
        ColorFilter filter =
            new LightingColorFilter(mContext.getResources().getColor(R.color.color_light_orange),
                mContext.getResources().getColor(R.color.color_light_orange));
        drawable.setBounds(0, 0, SIZE, SIZE);
        drawable.setColorFilter(filter);
        imageSpan = new ImageSpan(drawable, ImageSpan.ALIGN_BASELINE);
        stringBuilder.setSpan(imageSpan, 0, 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        return stringBuilder;
    }

    @Override
    public int getCount() {
        return mGuideList != null ? mGuideList.size() : 0;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return customIconTabHost(
            mContext.getResources().obtainTypedArray(R.array.guide_icon).getDrawable(position));
    }
}
