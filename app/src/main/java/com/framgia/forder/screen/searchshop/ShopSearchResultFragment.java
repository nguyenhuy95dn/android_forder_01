package com.framgia.forder.screen.searchshop;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.framgia.forder.R;
import com.framgia.forder.databinding.FragmentShopSearchResultBinding;

/**
 * Searchshop Screen.
 */
public class ShopSearchResultFragment extends Fragment {

    private ShopSearchResultContract.ViewModel mViewModel;

    public static ShopSearchResultFragment newInstance() {
        return new ShopSearchResultFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {
        mViewModel = new ShopSearchResultViewModel();

        ShopSearchResultContract.Presenter presenter = new ShopSearchResultPresenter(mViewModel);
        mViewModel.setPresenter(presenter);

        FragmentShopSearchResultBinding binding =
                DataBindingUtil.inflate(inflater, R.layout.fragment_shop_search_result, container,
                        false);
        binding.setViewModel((ShopSearchResultViewModel) mViewModel);
        return binding.getRoot();
    }
}
