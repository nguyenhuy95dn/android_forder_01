package com.example.duong.android_forder_01.ui.domain.publicdomain;

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
import com.example.duong.android_forder_01.data.source.DomainReposity;
import com.example.duong.android_forder_01.databinding.FragmentPublicDomainBinding;
import com.example.duong.android_forder_01.ui.adapter.PublicDomainAdapter;
import com.example.duong.android_forder_01.utils.SharedPreferencesUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vinh on 03/03/2017.
 */
public class PublicDomainFragment extends Fragment implements PublicDomainContract.View {
    private PublicDomainContract.Presenter mPresenter;
    private List<Domain> mDomains = new ArrayList<>();
    private ObservableField<PublicDomainAdapter> mPublicDomainAdapter = new ObservableField<>();
    private FragmentPublicDomainBinding mBinding;

    public static PublicDomainFragment newInstance() {
        return new PublicDomainFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_public_domain, container, false);
        setPresenter(new PublicDomainPresenter(this, DomainReposity.getInstance()));
        mPresenter.start();
        return mBinding.getRoot();
    }

    public void initRecyclerView() {
        mPublicDomainAdapter.set(new PublicDomainAdapter(mDomains, getActivity(), mPresenter));
    }

    @Override
    public void showAllPublicDomain(List<Domain> list) {
        if (list == null) return;
        mDomains.clear();
        mDomains.addAll(list);
    }

    @Override
    public void showGetDataError() {
    }

    @Override
    public void start() {
        mBinding.setPublicDomainFragment(this);
        initRecyclerView();
        mPresenter.getAllPublicDomain(SharedPreferencesUtils.loadUser(getActivity()));
    }

    @Override
    public void setPresenter(PublicDomainContract.Presenter presenter) {
        mPresenter = presenter;
    }

    public ObservableField<PublicDomainAdapter> getPublicDomainAdapter() {
        return mPublicDomainAdapter;
    }
}
