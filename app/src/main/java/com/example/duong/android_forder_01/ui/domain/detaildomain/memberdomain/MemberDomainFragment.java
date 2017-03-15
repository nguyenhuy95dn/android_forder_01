package com.example.duong.android_forder_01.ui.domain.detaildomain.memberdomain;

import android.databinding.DataBindingUtil;
import android.databinding.ObservableField;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.duong.android_forder_01.R;
import com.example.duong.android_forder_01.data.model.Domain;
import com.example.duong.android_forder_01.data.model.User;
import com.example.duong.android_forder_01.data.source.UserRepository;
import com.example.duong.android_forder_01.databinding.FragmentUserBinding;
import com.example.duong.android_forder_01.ui.adapter.UserAdapter;

import java.util.ArrayList;
import java.util.List;

import static com.example.duong.android_forder_01.utils.Const.KeyIntent.EXTRA_DOMAIN;

/**
 * Created by Vinh on 07/03/2017.
 */
public class MemberDomainFragment extends Fragment
    implements MemberDomainContract.View {
    private MemberDomainContract.Presenter mPresenter;
    private List<User> mUsers = new ArrayList<>();
    private ObservableField<UserAdapter> mUserAdapter = new ObservableField<>();
    private FragmentUserBinding mBinding;
    private Domain mDomain;

    public static MemberDomainFragment newInstance(Domain domain) {
        MemberDomainFragment fragment = new MemberDomainFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(EXTRA_DOMAIN, domain);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_user, container, false);
        setPresenter(new MemberDomainPresenter(this, UserRepository.getInstance(getContext())));
        mPresenter.start();
        return mBinding.getRoot();
    }

    public void initRecyclerView() {
        mUserAdapter.set(new UserAdapter(mUsers, getActivity(), mPresenter));
    }

    @Override
    public void showAllMemberDomain(List<User> list) {
        if (list == null) return;
        mUsers.clear();
        mUsers.addAll(list);
    }

    @Override
    public void showGetDataError() {
    }

    @Override
    public void start() {
        mBinding.setUserFragment(this);
        mDomain = (Domain) getArguments().getSerializable(EXTRA_DOMAIN);
        mUsers.clear();
        mUsers.addAll(mDomain.getUser());
        initRecyclerView();
    }

    @Override
    public void setPresenter(MemberDomainContract.Presenter presenter) {
        mPresenter = presenter;
    }

    public ObservableField<UserAdapter> getUserAdapter() {
        return mUserAdapter;
    }
}