package com.framgia.forder.screen;

/**
 * Created by le.quang.dao on 10/03/2017.
 */

/**
 * BaseView
 */
public interface BaseViewModel<T extends BasePresenter> {

    void onStart();

    void onStop();

    void setPresenter(T presenter);
}
