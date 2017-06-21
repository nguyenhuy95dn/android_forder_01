package com.framgia.forder.screen.mainpage.shop;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.framgia.forder.R;
import com.framgia.forder.data.model.Shop;
import com.framgia.forder.databinding.FragmentCustomSlideShowBinding;
import com.framgia.forder.utils.navigator.Navigator;

/**
 * Created by ths on 21/06/2017.
 */

public class ShopPageFragment extends Fragment {

    private static final String EXTRA_SHOPPAGE = "EXTRA_SHOPPAGE";
    private ShopPageContract.ViewModel mViewModel;

    public static ShopPageFragment newInstance(Shop shop) {
        ShopPageFragment shopPageFragment = new ShopPageFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable(EXTRA_SHOPPAGE, shop);
        shopPageFragment.setArguments(bundle);
        return shopPageFragment;
    }

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {
        Shop shop = (Shop) getArguments().get(EXTRA_SHOPPAGE);
        Navigator navigator = new Navigator(getParentFragment().getParentFragment());
        mViewModel = new ShopPageViewModel(shop, navigator);
        ShopPageContract.Presenter presenter = new ShopPagePresenter(mViewModel);
        mViewModel.setPresenter(presenter);

        FragmentCustomSlideShowBinding binding =
                DataBindingUtil.inflate(inflater, R.layout.fragment_custom_slide_show, container,
                        false);
        binding.setViewModel((ShopPageViewModel) mViewModel);
        return binding.getRoot();
    }
}
