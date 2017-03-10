package com.example.duong.android_forder_01.ui.domain.detaildomain;

import com.example.duong.android_forder_01.ui.BasePresenter;
import com.example.duong.android_forder_01.ui.BaseView;

/**
 * Created by Vinh on 07/03/2017.
 */
public interface DetailDomainContract {
    interface View extends BaseView<Presenter> {
        void showGetDataError();
    }

    interface Presenter extends BasePresenter {
    }
}
