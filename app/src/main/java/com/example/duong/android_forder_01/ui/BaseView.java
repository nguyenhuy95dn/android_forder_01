package com.example.duong.android_forder_01.ui;

/**
 * Created by Duong on 2/6/2017.
 */
public interface BaseView<T> {
    void start();
    void setPresenter(T presenter);
}
