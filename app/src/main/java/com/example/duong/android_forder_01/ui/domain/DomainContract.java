package com.example.duong.android_forder_01.ui.domain;

import com.example.duong.android_forder_01.ui.BasePresenter;
import com.example.duong.android_forder_01.ui.BaseView;

/**
 * Created by Vinh on 03/03/2017.
 */
public interface DomainContract {
    interface View extends BaseView<Presenter> {
        void showGetDataError();
    }

    interface Presenter extends BasePresenter {
    }
}
