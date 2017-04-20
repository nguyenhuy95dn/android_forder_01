package com.framgia.forder.screen.listshop;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.framgia.forder.R;
import com.framgia.forder.data.model.Product;
import com.framgia.forder.data.model.Shop;
import com.framgia.forder.databinding.FragmentListShopBinding;
import com.framgia.forder.screen.mainpage.shop.ShopAdapter;
import com.framgia.forder.utils.navigator.Navigator;
import java.util.ArrayList;
import java.util.List;

/**
 * ShopFragment Screen.
 */
public class ListShopFragment extends Fragment {

    private ListShopContract.ViewModel mViewModel;

    public static ListShopFragment newInstance() {
        return new ListShopFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        List<Shop> shops = new ArrayList<>();
        ShopAdapter shopAdapter = new ShopAdapter(getActivity(), shops);
        Navigator navigator = new Navigator(getParentFragment());
        mViewModel = new ListShopViewModel(getActivity(), shopAdapter, navigator);
        ListShopContract.Presenter presenter = new ListShopPresenter(mViewModel);
        mViewModel.setPresenter(presenter);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {

        FragmentListShopBinding binding =
                DataBindingUtil.inflate(inflater, R.layout.fragment_list_shop, container, false);
        binding.setViewModel((ListShopViewModel) mViewModel);
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
