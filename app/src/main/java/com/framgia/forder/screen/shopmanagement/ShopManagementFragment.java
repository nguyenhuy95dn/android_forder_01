package com.framgia.forder.screen.shopmanagement;

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
import com.framgia.forder.data.source.UserRepository;
import com.framgia.forder.data.source.local.UserLocalDataSource;
import com.framgia.forder.data.source.local.sharedprf.SharedPrefsApi;
import com.framgia.forder.data.source.local.sharedprf.SharedPrefsImpl;
import com.framgia.forder.data.source.remote.ShopRemoteDataSource;
import com.framgia.forder.data.source.remote.api.service.FOrderServiceClient;
import com.framgia.forder.databinding.FragmentShopmanagementBinding;
import com.framgia.forder.utils.navigator.Navigator;
import java.util.ArrayList;
import java.util.List;

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
        List<ShopManagement> shopManagements = new ArrayList<>();
        ListShopManagementAdapter listShopManagementAdapter =
                new ListShopManagementAdapter(getActivity(), shopManagements);
        Navigator navigator = new Navigator(getParentFragment());
        mViewModel = new ShopManagementViewModel(navigator, listShopManagementAdapter);

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
}
