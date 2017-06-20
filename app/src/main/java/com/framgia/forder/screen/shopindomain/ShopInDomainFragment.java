package com.framgia.forder.screen.shopindomain;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.framgia.forder.R;
import com.framgia.forder.data.model.DomainManagement;
import com.framgia.forder.databinding.FragmentShopInDomainBinding;

/**
 * Shopindomain Screen.
 */
public class ShopInDomainFragment extends Fragment {
    private static final String EXTRA_DOMAIN = "EXTRA_DOMAIN";

    private ShopInDomainContract.ViewModel mViewModel;

    public static ShopInDomainFragment newInstance(DomainManagement domainManagement) {
        ShopInDomainFragment shopInDomainFragment = new ShopInDomainFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable(EXTRA_DOMAIN, domainManagement);
        shopInDomainFragment.setArguments(bundle);
        return shopInDomainFragment;
    }

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DomainManagement domainManagement = (DomainManagement) getArguments().get(EXTRA_DOMAIN);

        mViewModel = new ShopInDomainViewModel();

        ShopInDomainContract.Presenter presenter = new ShopInDomainPresenter(mViewModel);
        mViewModel.setPresenter(presenter);

        FragmentShopInDomainBinding binding =
                DataBindingUtil.inflate(inflater, R.layout.fragment_shop_in_domain, container,
                        false);
        binding.setViewModel((ShopInDomainViewModel) mViewModel);
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
