package com.framgia.forder.screen.shopinfo.productshopinfo;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.framgia.forder.R;
import com.framgia.forder.databinding.FragmentProductShopInfoBinding;

/**
 * ProductShopInfo Screen.
 */
public class ProductShopInfoFragment extends Fragment {

    private ProductShopInfoContract.ViewModel mViewModel;

    public static ProductShopInfoFragment newInstance() {
        return new ProductShopInfoFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewModel = new ProductShopInfoViewModel();

        ProductShopInfoContract.Presenter presenter = new ProductShopInfoPresenter(mViewModel);
        mViewModel.setPresenter(presenter);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {

        FragmentProductShopInfoBinding binding =
                DataBindingUtil.inflate(inflater, R.layout.fragment_product_shop_info, container,
                        false);
        binding.setViewModel((ProductShopInfoViewModel) mViewModel);
        return binding.getRoot();
    }

    @Override
    public void onStart() {
        super.onStart();
        mViewModel.onStart();
    }

    @Override
    public void onStop() {
        mViewModel.onStop();
        super.onStop();
    }
}
