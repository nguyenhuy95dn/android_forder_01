package com.framgia.forder.screen.updateProduct;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.framgia.forder.R;
import com.framgia.forder.data.model.Product;
import com.framgia.forder.databinding.FragmentUpdateProductBinding;

/**
 * UpdateProduct Screen.
 */
public class UpdateProductFragment extends Fragment {
    private static final String EXTRA_PRODUCT = "EXTRA_PRODUCT";
    private UpdateProductContract.ViewModel mViewModel;

    public static UpdateProductFragment newInstance(Product product) {
        UpdateProductFragment updateProductFragment = new UpdateProductFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable(EXTRA_PRODUCT, product);
        updateProductFragment.setArguments(bundle);
        return updateProductFragment;
    }

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Product product = (Product) getArguments().get(EXTRA_PRODUCT);
        mViewModel = new UpdateProductViewModel(getActivity(), product);
        UpdateProductContract.Presenter presenter = new UpdateProductPresenter(mViewModel);
        mViewModel.setPresenter(presenter);

        FragmentUpdateProductBinding binding =
                DataBindingUtil.inflate(inflater, R.layout.fragment_update_product, container,
                        false);
        binding.setViewModel((UpdateProductViewModel) mViewModel);
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
