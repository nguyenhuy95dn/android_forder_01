package com.framgia.forder.screen.shopmanagement;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.framgia.forder.R;
import com.framgia.forder.data.source.ShopRepository;
import com.framgia.forder.data.source.UserRepository;
import com.framgia.forder.data.source.local.UserLocalDataSource;
import com.framgia.forder.data.source.local.sharedprf.SharedPrefsApi;
import com.framgia.forder.data.source.local.sharedprf.SharedPrefsImpl;
import com.framgia.forder.data.source.remote.ShopRemoteDataSource;
import com.framgia.forder.data.source.remote.api.service.FOrderServiceClient;
import com.framgia.forder.databinding.FragmentShopmanagementBinding;
import com.framgia.forder.utils.navigator.Navigator;
import com.framgia.forder.widgets.dialog.DialogManager;

/**
 * ShopManagement Screen.
 */
public class ShopManagementFragment extends Fragment {

    private ShopManagementContract.ViewModel mViewModel;

    public static ShopManagementFragment newInstance() {
        return new ShopManagementFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Navigator navigator = new Navigator(getParentFragment());
        DialogManager dialogManager = new DialogManager(getActivity());
        ListShopManagementAdapter listShopManagementAdapter =
                new ListShopManagementAdapter(getActivity().getApplicationContext());
        mViewModel =
                new ShopManagementViewModel(navigator, listShopManagementAdapter, dialogManager);

        SharedPrefsApi prefsApi = new SharedPrefsImpl(getActivity().getApplicationContext());
        UserRepository userRepository = new UserRepository(null, new UserLocalDataSource(prefsApi));
        ShopRepository shopRepository =
                new ShopRepository(new ShopRemoteDataSource(FOrderServiceClient.getInstance()));
        ShopManagementContract.Presenter presenter =
                new ShopManagementPresenter(mViewModel, userRepository, shopRepository);
        mViewModel.setPresenter(presenter);
        FragmentShopmanagementBinding binding =
                DataBindingUtil.inflate(inflater, R.layout.fragment_shopmanagement, container,
                        false);
        binding.setViewModel((ShopManagementViewModel) mViewModel);
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

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (!isAdded() || !isVisibleToUser) {
            return;
        }
        mViewModel.onReLoadData();
    }
}
