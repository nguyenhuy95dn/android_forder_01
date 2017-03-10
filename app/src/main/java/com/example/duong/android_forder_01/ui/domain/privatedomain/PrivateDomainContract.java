package com.example.duong.android_forder_01.ui.domain.privatedomain;

import com.example.duong.android_forder_01.data.model.Domain;
import com.example.duong.android_forder_01.ui.BasePresenter;
import com.example.duong.android_forder_01.ui.BaseView;

import java.util.List;

/**
 * Created by Vinh on 03/03/2017.
 */
public interface PrivateDomainContract {
    interface View extends BaseView<Presenter> {
        void showAllPrivateDomain(List<Domain> list);
        void showDomainDetail(Domain domain);
        void showGetDataError();
    }

    interface Presenter extends BasePresenter {
        void getAllPrivateDomain(int idUser);
        void getDomainDetail(Domain domain);
    }
}
