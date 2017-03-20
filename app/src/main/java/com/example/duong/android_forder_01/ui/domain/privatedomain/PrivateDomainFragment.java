package com.example.duong.android_forder_01.ui.domain.privatedomain;

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
import com.example.duong.android_forder_01.databinding.FragmentPrivateDomainBinding;
import com.example.duong.android_forder_01.ui.adapter.PrivateDomainAdapter;
import com.example.duong.android_forder_01.ui.domain.detaildomain.DetailDomainActivity;
import com.example.duong.android_forder_01.utils.SharedPreferencesUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vinh on 03/03/2017.
 */
public class PrivateDomainFragment extends Fragment
    implements PrivateDomainContract.View {
    private PrivateDomainContract.Presenter mPresenter;
    private List<Domain> mDomains = new ArrayList<>();
    private ObservableField<PrivateDomainAdapter> mPrivateDomainAdapter = new ObservableField<>();
    private FragmentPrivateDomainBinding mBinding;

    public static PrivateDomainFragment newInstance() {
        return new PrivateDomainFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_private_domain, container, false);
        setPresenter(new PrivateDomainPresenter(this, DomainReposity.getInstance()));
        mPresenter.start();
        return mBinding.getRoot();
    }

    public void initRecyclerView() {
        mPrivateDomainAdapter.set(new PrivateDomainAdapter(mDomains, getActivity(), mPresenter));
    }

    @Override
    public void showAllPrivateDomain(List<Domain> list) {
        if (list == null) return;
        mDomains.clear();
        mDomains.addAll(list);
    }

    @Override
    public void showDomainDetail(Domain domain) {
        startActivity(DetailDomainActivity.getDomainDetailIntent(getActivity(), domain));
    }

    @Override
    public void showGetDataError() {
    }

    @Override
    public void start() {
        mBinding.setPrivateDomainFragment(this);
        initRecyclerView();
        mPresenter.getAllPrivateDomain(SharedPreferencesUtils.loadUser(getActivity()));
    }

    @Override
    public void setPresenter(PrivateDomainContract.Presenter presenter) {
        mPresenter = presenter;
    }

    public ObservableField<PrivateDomainAdapter> getPrivateDomainAdapter() {
        return mPrivateDomainAdapter;
    }
}
