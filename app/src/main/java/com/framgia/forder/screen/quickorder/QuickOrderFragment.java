package com.framgia.forder.screen.quickorder;

import android.app.Dialog;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import com.framgia.forder.R;
import com.framgia.forder.data.model.Product;
import com.framgia.forder.databinding.FragmentQuickOrderBinding;

/**
 * Quickorder Screen.
 */
public class QuickOrderFragment extends DialogFragment implements DismissDialogListener {
    private static final String EXTRA_PRODUCT = "EXTRA_PRODUCT";
    private QuickOrderContract.ViewModel mViewModel;
    private QuickOrderListener mQuickOrderListener;

    public static QuickOrderFragment newInstance(Product product) {
        QuickOrderFragment quickOrderFragment = new QuickOrderFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable(EXTRA_PRODUCT, product);
        quickOrderFragment.setArguments(bundle);
        return quickOrderFragment;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Product product = (Product) getArguments().get(EXTRA_PRODUCT);
        mViewModel = new QuickOrderViewModel(product, mQuickOrderListener, this);

        QuickOrderContract.Presenter presenter = new QuickOrderPresenter(mViewModel);
        mViewModel.setPresenter(presenter);

        FragmentQuickOrderBinding binding =
                DataBindingUtil.inflate(LayoutInflater.from(getContext()),
                        R.layout.fragment_quick_order, null, false);
        binding.setViewModel((QuickOrderViewModel) mViewModel);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity(),
                R.style.Theme_AppCompat_DayNight_Dialog).setTitle(R.string.quick_order)
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

    public void setQuickOrderListener(QuickOrderListener listener) {
        mQuickOrderListener = listener;
    }

    @Override
    public void onDialogDismiss() {
        dismiss();
    }
}
