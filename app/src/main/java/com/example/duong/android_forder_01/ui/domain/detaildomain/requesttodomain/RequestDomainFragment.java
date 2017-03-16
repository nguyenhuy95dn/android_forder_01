package com.example.duong.android_forder_01.ui.domain.detaildomain.requesttodomain;

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
import com.example.duong.android_forder_01.data.model.Shop;
import com.example.duong.android_forder_01.data.source.ShopRepository;
import com.example.duong.android_forder_01.databinding.FragmentRequestShopdomainBinding;
import com.example.duong.android_forder_01.ui.adapter.RequestShopDomainAdapter;

import java.util.ArrayList;
import java.util.List;

import static com.example.duong.android_forder_01.utils.Const.KeyIntent.EXTRA_DOMAIN;

/**
 * Created by Vinh on 07/03/2017.
 */
public class RequestDomainFragment extends Fragment implements RequestDomainContract.View {
    private RequestDomainContract.Presenter mPresenter;
    private List<Shop> mShopList = new ArrayList<>();
    private ObservableField<RequestShopDomainAdapter> mRequestShopDomainAdapter =
        new ObservableField<>();
    private FragmentRequestShopdomainBinding mBinding;
    private Domain mDomain;

    public static RequestDomainFragment newInstance(Domain domain) {
        RequestDomainFragment fragment = new RequestDomainFragment();
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
            inflater, R.layout.fragment_request_shopdomain, container, false);
        setPresenter(new RequestDomainPresent(this, ShopRepository.getInstance()));
        mPresenter.start();
        return mBinding.getRoot();
    }

    public void initRecyclerView() {
        mRequestShopDomainAdapter
            .set(new RequestShopDomainAdapter(getActivity(), mShopList, mPresenter));
    }

    @Override
    public void start() {
        mDomain = (Domain) getArguments().getSerializable(EXTRA_DOMAIN);
        mShopList.clear();
        mShopList.addAll(mDomain.getShop());
        mBinding.setRequestShopDomainFragment(this);
        initRecyclerView();
    }

    @Override
    public void setPresenter(RequestDomainContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void showAllShopRequestDomain(List<Shop> list) {
        if (list == null) return;
        mShopList.clear();
        mShopList.addAll(list);
    }

    @Override
    public void showGetDataError() {
    }

    public ObservableField<RequestShopDomainAdapter> getRequestShopDomainAdapter() {
        return mRequestShopDomainAdapter;
    }
}
