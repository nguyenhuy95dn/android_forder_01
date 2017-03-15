package com.example.duong.android_forder_01.ui.shopmanagementdetail.ordershop.closeorder;

import android.databinding.DataBindingUtil;
import android.databinding.ObservableField;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.duong.android_forder_01.R;
import com.example.duong.android_forder_01.data.model.OrderDetail;
import com.example.duong.android_forder_01.data.model.Shop;
import com.example.duong.android_forder_01.databinding.FragmentCloseOrderBinding;
import com.example.duong.android_forder_01.ui.adapter.CloseOrderAdapter;

import java.util.ArrayList;
import java.util.List;

import static com.example.duong.android_forder_01.utils.Const.KeyIntent.EXTRA_SHOP;

/**
 * Created by Duong on 3/10/2017.
 */
public class CloseOrderFragment extends Fragment implements CloseOrderContract.View {
    private FragmentCloseOrderBinding mBinding;
    private CloseOrderContract.Presenter mPresenter;
    private List<OrderDetail> mOrderDetails = new ArrayList<>();
    private ObservableField<CloseOrderAdapter> mCloseOrderAdapter = new ObservableField<>();
    private String mTotalPrice;

    public static CloseOrderFragment newInstance(Shop shop) {
        CloseOrderFragment fragment = new CloseOrderFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(EXTRA_SHOP, shop);
        fragment.setArguments(bundle);
        return fragment;
    }

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_close_order, container, false);
        setPresenter(new CloseOrderPresenter(this));
        mPresenter.start();
        return mBinding.getRoot();
    }

    @Override
    public void start() {
        mBinding.setCloseOrder(this);
        mBinding.setActionHandler(new CloseOrderActionHandler(mPresenter));
        mCloseOrderAdapter.set(new CloseOrderAdapter(getContext(), mOrderDetails));
    }

    @Override
    public void setPresenter(CloseOrderContract.Presenter presenter) {
        mPresenter = presenter;
    }

    public ObservableField<CloseOrderAdapter> getCloseOrderAdapter() {
        return mCloseOrderAdapter;
    }

    public String getTotalPrice() {
        return mTotalPrice;
    }
}
