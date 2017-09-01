package com.framgia.forder.screen.addtocart;

import android.app.Activity;
import android.app.Dialog;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import com.framgia.forder.R;
import com.framgia.forder.data.model.Product;
import com.framgia.forder.data.source.DomainRepository;
import com.framgia.forder.data.source.ProductRepository;
import com.framgia.forder.data.source.local.DomainLocalDataSource;
import com.framgia.forder.data.source.local.ProductLocalDataSource;
import com.framgia.forder.data.source.local.UserLocalDataSource;
import com.framgia.forder.data.source.local.realm.RealmApi;
import com.framgia.forder.data.source.local.sharedprf.SharedPrefsApi;
import com.framgia.forder.data.source.local.sharedprf.SharedPrefsImpl;
import com.framgia.forder.data.source.remote.DomainRemoteDataSource;
import com.framgia.forder.data.source.remote.ProductRemoteDataSource;
import com.framgia.forder.data.source.remote.api.service.FOrderServiceClient;
import com.framgia.forder.databinding.FragmentAddToCartBinding;
import com.framgia.forder.screen.main.LoadCartListener;
import com.framgia.forder.screen.quickorder.DismissDialogListener;

/**
 * Add_to_cart Screen.
 */
public class AddToCartFragment extends DialogFragment implements DismissDialogListener {

    private static final String EXTRA_PRODUCT = "EXTRA_PRODUCT";
    private static final String EXTRA_PRODUCT_IN_CART = "EXTRA_PRODUCT_IN_CART";
    private static final String EXTRA_QUANTITY = "EXTRA_QUANTITY";

    private AddToCartContract.ViewModel mViewModel;
    private LoadCartListener mLoadCartListener;

    public static AddToCartFragment newInstance(Product product, int productInCart, int quantity) {
        AddToCartFragment addToCartFragment = new AddToCartFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable(EXTRA_PRODUCT, product);
        bundle.putInt(EXTRA_PRODUCT_IN_CART, productInCart);
        bundle.putInt(EXTRA_QUANTITY, quantity);
        addToCartFragment.setArguments(bundle);
        return addToCartFragment;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Product product = (Product) getArguments().get(EXTRA_PRODUCT);
        int productInCart = getArguments().getInt(EXTRA_PRODUCT_IN_CART);
        int quantity = getArguments().getInt(EXTRA_QUANTITY);
        mViewModel =
                new AddToCartViewModel(product, productInCart, quantity, this,
                        mLoadCartListener);

        RealmApi realmApi = new RealmApi();
        SharedPrefsApi prefsApi = new SharedPrefsImpl(getActivity());

        DomainRepository domainRepository =
                new DomainRepository(new DomainRemoteDataSource(FOrderServiceClient.getInstance()),
                        new DomainLocalDataSource(prefsApi, new UserLocalDataSource(prefsApi)));
        int currentDomainId = domainRepository.getCurrentDomain().getId();
        ProductRepository productRepository = new ProductRepository(
                new ProductRemoteDataSource(FOrderServiceClient.getInstance()),
                new ProductLocalDataSource(realmApi), currentDomainId);

        AddToCartContract.Presenter presenter =
                new AddToCartPresenter(mViewModel, productRepository);
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

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mLoadCartListener = (LoadCartListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + " must implement LoadCartListener");
        }
    }
}
