package com.example.duong.android_forder_01.ui.home.guide;

/**
 * Created by tri on 21/03/2017.
 */
public class GuidePresenter implements GuideContract.Presenter {
    private GuideContract.View mView;

    public GuidePresenter(GuideContract.View view) {
        mView = view;
        mView.setPresenter(this);
    }

    @Override
    public void start() {
        mView.start();
    }
}
