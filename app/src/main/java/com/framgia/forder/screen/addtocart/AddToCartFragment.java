package com.framgia.forder.screen.addtocart;

import android.app.Dialog;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import com.framgia.forder.R;
import com.framgia.forder.data.model.Product;
import com.framgia.forder.databinding.FragmentAddToCartBinding;
import com.framgia.forder.screen.quickorder.DismissDialogListener;

/**
 * Add_to_cart Screen.
 */
public class AddToCartFragment extends DialogFragment implements DismissDialogListener {
    private static final String EXTRA_PRODUCT = "EXTRA_PRODUCT";
    private static final String EXTRA_PRODUCT_IN_CART = "EXTRA_PRODUCT_IN_CART";
    private static final String EXTRA_TOTAL_PRICE_IN_CART = "EXTRA_TOTAL_PRICE_IN_CART";

    private AddToCartContract.ViewModel mViewModel;

    public static AddToCartFragment newInstance(Product product, int productInCart,
            double totalPriceInCart) {
        AddToCartFragment addToCartFragment = new AddToCartFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable(EXTRA_PRODUCT, product);
        bundle.putInt(EXTRA_PRODUCT_IN_CART, productInCart);
        bundle.putDouble(EXTRA_TOTAL_PRICE_IN_CART, totalPriceInCart);
        addToCartFragment.setArguments(bundle);
        return addToCartFragment;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Product product = (Product) getArguments().get(EXTRA_PRODUCT);
        int productInCart = getArguments().getInt(EXTRA_PRODUCT_IN_CART);
        double totalPriceInCart = getArguments().getInt(EXTRA_TOTAL_PRICE_IN_CART);
        mViewModel = new AddToCartViewModel(product, productInCart, totalPriceInCart, this);

        AddToCartContract.Presenter presenter = new AddToCartPresenter(mViewModel);
        mViewModel.setPresenter(presenter);

        FragmentAddToCartBinding binding =
                DataBindingUtil.inflate(LayoutInflater.from(getContext()),
                        R.layout.fragment_add_to_cart, null, false);
        binding.setViewModel((AddToCartViewModel) mViewModel);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity(),
                R.style.Theme_AppCompat_DayNight_Dialog).setTitle(R.string.update_cart_successful)
                .setView(binding.getRoot());
        return builder.create();
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

    @Override
    public void onDialogDismiss() {
        dismiss();
    }
}
