package com.framgia.forder.screen.createProduct;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.framgia.forder.R;
import com.framgia.forder.databinding.FragmentCreateProductBinding;

/**
 * Createproduct Screen.
 */
public class CreateProductFragment extends Fragment {

    private CreateProductContract.ViewModel mViewModel;

    public static CreateProductFragment newInstance() {
        return new CreateProductFragment();
    }

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewModel = new CreateProductViewModel();
        CreateProductContract.Presenter presenter = new CreateProductPresenter(mViewModel);
        mViewModel.setPresenter(presenter);
        FragmentCreateProductBinding binding =
                DataBindingUtil.inflate(inflater, R.layout.fragment_create_product, container,
                        false);
        binding.setViewModel((CreateProductViewModel) mViewModel);
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
