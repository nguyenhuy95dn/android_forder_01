package com.framgia.forder.screen.cart;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.framgia.forder.R;
import com.framgia.forder.databinding.FragmentShoppingcartBinding;

/**
 * ShoppingCart Screen.
 */
public class ShoppingCartFragment extends Fragment {

    private ShoppingCartContract.ViewModel mViewModel;

    public static ShoppingCartFragment newInstance() {
        return new ShoppingCartFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewModel = new ShoppingCartViewModel();

        ShoppingCartContract.Presenter presenter = new ShoppingCartPresenter(mViewModel);
        mViewModel.setPresenter(presenter);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {

        FragmentShoppingcartBinding binding =
                DataBindingUtil.inflate(inflater, R.layout.fragment_shoppingcart, container, false);
        binding.setViewModel((ShoppingCartViewModel) mViewModel);
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
