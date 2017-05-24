package com.framgia.forder.screen.shopupdate;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.framgia.forder.R;
import com.framgia.forder.data.model.ShopManagement;
import com.framgia.forder.data.source.ShopRepository;
import com.framgia.forder.data.source.remote.ShopRemoteDataSource;
import com.framgia.forder.data.source.remote.api.service.FOrderServiceClient;
import com.framgia.forder.databinding.FragmentShopUpdateBinding;
import com.framgia.forder.utils.navigator.Navigator;
import com.framgia.forder.widgets.dialog.DialogManager;

/**
 * ShopUpdate Screen.
 */
public class ShopUpdateFragment extends Fragment {

    private static final String EXTRA_SHOP_UPDATE = "EXTRA_SHOP_UPDATE";
    private ShopUpdateContract.ViewModel mViewModel;

    public static ShopUpdateFragment newInstance(ShopManagement shopManagement) {
        ShopUpdateFragment shopUpdateFragment = new ShopUpdateFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable(EXTRA_SHOP_UPDATE, shopManagement);
        shopUpdateFragment.setArguments(bundle);
        return shopUpdateFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Navigator navigator = new Navigator(getParentFragment());
        DialogManager dialogManager = new DialogManager(getActivity());
        ShopManagement shopManagement = (ShopManagement) getArguments().get(EXTRA_SHOP_UPDATE);
        mViewModel =
                new ShopUpdateViewModel(getActivity(), dialogManager, navigator, shopManagement);

        ShopRepository shopRepository =
                new ShopRepository(new ShopRemoteDataSource(FOrderServiceClient.getInstance()));
        ShopUpdateContract.Presenter presenter =
                new ShopUpdatePresenter(mViewModel, shopRepository);
        mViewModel.setPresenter(presenter);
        FragmentShopUpdateBinding binding =
                DataBindingUtil.inflate(inflater, R.layout.fragment_shop_update, container, false);
        binding.setViewModel((ShopUpdateViewModel) mViewModel);
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
