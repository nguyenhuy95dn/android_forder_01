package com.example.duong.android_forder_01.ui.domain.detaildomain.memberdomain;

import android.support.annotation.NonNull;

import com.example.duong.android_forder_01.data.source.UserDataSource;

/**
 * Created by Vinh on 07/03/2017.
 */
public class MemberDomainPresenter implements MemberDomainContract.Presenter {
    private MemberDomainContract.View mView;
    private UserDataSource mUserDataRepository;

    public MemberDomainPresenter(@NonNull MemberDomainContract.View view,
                                 UserDataSource userDataRepository) {
        mView = view;
        view.setPresenter(this);
        mUserDataRepository = userDataRepository;
    }

    @Override
    public void start() {
        mView.start();
    }

    @Override
    public void getAllMemberDomain(int idDomain) {
        //TODO: get member in domain
    }
}
