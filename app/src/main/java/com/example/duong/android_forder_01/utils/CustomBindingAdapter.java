package com.example.duong.android_forder_01.utils;

import android.databinding.BindingAdapter;
import android.graphics.Color;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;

import com.example.duong.android_forder_01.data.model.Domain;
import com.squareup.picasso.Picasso;

public class CustomBindingAdapter {
    @BindingAdapter("bind:imageUrl")
    public static void loadImage(ImageView imageView, String url) {
        Picasso.with(imageView.getContext()).load(url).into(imageView);
    }

    @BindingAdapter({"adapter"})
    public static void setAdapter(RecyclerView view,
                                  RecyclerView.Adapter adapter) {
        view.setAdapter(adapter);
        view.setHasFixedSize(true);
    }

    @BindingAdapter("layoutManager")
    public static void setLayoutManager(RecyclerView recyclerView,
                                        LayoutManagers.LayoutManagerFactory layoutManagerFactory) {
        recyclerView.setLayoutManager(layoutManagerFactory.create(recyclerView));
    }

    @BindingAdapter({"bind:adapter"})
    public static void bindViewPagerAdapter(final ViewPager view,
                                            final FragmentPagerAdapter adapter) {
        view.setAdapter(adapter);
    }

    @BindingAdapter({"bind:pager"})
    public static void bindViewPagerTabs(final TabLayout view, final ViewPager pagerView) {
        view.setupWithViewPager(pagerView, true);
    }

    @BindingAdapter("bind:activity")
    public static void setSupportActionBar(Toolbar toolbar, AppCompatActivity activity) {
        toolbar.setTitleTextColor(Color.WHITE);
        activity.setSupportActionBar(toolbar);
    }

    @BindingAdapter({"spinner"})
    public static void bindSpinner(Spinner spinner, ArrayAdapter<Domain> arrayAdapter) {
        spinner.setAdapter(arrayAdapter);
    }
}
