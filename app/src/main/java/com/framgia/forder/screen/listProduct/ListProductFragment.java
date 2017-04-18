package com.framgia.forder.screen.listProduct;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.framgia.forder.R;
import com.framgia.forder.databinding.FragmentListproductBinding;
import com.framgia.forder.utils.navigator.Navigator;

/**
 * Productpage Screen.
 */
public class ListProductFragment extends Fragment {

    private ListProductContract.ViewModel mViewModel;

    public static ListProductFragment newInstance() {
        return new ListProductFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Navigator navigator = new Navigator(getParentFragment());
        mViewModel = new ListProductViewModel(navigator);
        ListProductContract.Presenter presenter = new ListProductPresenter(mViewModel);
        mViewModel.setPresenter(presenter);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {

        FragmentListproductBinding binding =
                DataBindingUtil.inflate(inflater, R.layout.fragment_listproduct, container, false);
        binding.setViewModel((ListProductViewModel) mViewModel);
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
