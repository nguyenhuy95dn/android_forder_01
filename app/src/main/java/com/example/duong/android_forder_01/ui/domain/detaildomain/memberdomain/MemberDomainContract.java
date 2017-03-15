package com.example.duong.android_forder_01.ui.domain.detaildomain.memberdomain;

import com.example.duong.android_forder_01.data.model.User;
import com.example.duong.android_forder_01.ui.BasePresenter;
import com.example.duong.android_forder_01.ui.BaseView;

import java.util.List;

/**
 * Created by Vinh on 07/03/2017.
 */
public interface MemberDomainContract {
    interface View extends BaseView<Presenter> {
        void showAllMemberDomain(List<User> list);
        void showGetDataError();
    }

    interface Presenter extends BasePresenter {
        void getAllMemberDomain(int idDomain);
    }
}
