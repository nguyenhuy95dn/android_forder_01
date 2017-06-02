package com.framgia.forder.screen.shopDetail;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.framgia.forder.R;
import com.framgia.forder.data.model.Shop;
import com.framgia.forder.databinding.FragmentShopDetailBinding;

/**
 * DetailShop Screen.
 */
public class ShopDetailFragment extends Fragment {
    private static final String EXTRA_SHOP = "EXTRA_SHOP";
    private ShopDetailContract.ViewModel mViewModel;

    public static ShopDetailFragment newInstance(Shop shop) {
        ShopDetailFragment shopDetailFragment = new ShopDetailFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable(EXTRA_SHOP, shop);
        shopDetailFragment.setArguments(bundle);
        return shopDetailFragment;
    }

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Shop shop = (Shop) getArguments().get(EXTRA_SHOP);
        mViewModel = new ShopDetailViewModel(shop);
        ShopDetailContract.Presenter presenter = new ShopDetailPresenter(mViewModel);

        mViewModel.setPresenter(presenter);
        FragmentShopDetailBinding binding =
                DataBindingUtil.inflate(inflater, R.layout.fragment_shop_detail, container, false);
        binding.setViewModel((ShopDetailViewModel) mViewModel);
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
