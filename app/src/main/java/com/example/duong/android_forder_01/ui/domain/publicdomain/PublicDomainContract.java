package com.example.duong.android_forder_01.ui.domain.publicdomain;

import com.example.duong.android_forder_01.data.model.Domain;
import com.example.duong.android_forder_01.data.model.User;
import com.example.duong.android_forder_01.ui.BasePresenter;
import com.example.duong.android_forder_01.ui.BaseView;

import java.util.List;

/**
 * Created by Vinh on 03/03/2017.
 */
public interface PublicDomainContract {
    interface View extends BaseView<Presenter> {
        void showAllPublicDomain(List<Domain> list);
        void showGetDataError();
    }

    interface Presenter extends BasePresenter {
        void getAllPublicDomain(User user);
    }
}
