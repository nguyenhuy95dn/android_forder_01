package com.framgia.forder.screen.shopinfo;

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
import com.framgia.forder.databinding.FragmentShopinfoBinding;
import com.framgia.forder.utils.navigator.Navigator;

/**
 * Shopinfo Screen.
 */
public class ShopinfoFragment extends Fragment {

    private static final String EXTRA_SHOP = "EXTRA_SHOP";
    private ShopinfoContract.ViewModel mViewModel;

    public static ShopinfoFragment newInstance(ShopManagement shopManagement) {
        ShopinfoFragment shopinfoFragment = new ShopinfoFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable(EXTRA_SHOP, shopManagement);
        shopinfoFragment.setArguments(bundle);
        return shopinfoFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Navigator navigator = new Navigator(getParentFragment().getParentFragment());

        ManagerShopInfoAdapter adapter = new ManagerShopInfoAdapter(getActivity());
        ShopManagement shopManagement = (ShopManagement) getArguments().get(EXTRA_SHOP);
        mViewModel = new ShopinfoViewModel(navigator, shopManagement, adapter);

        ShopRepository shopRepository =
                new ShopRepository(new ShopRemoteDataSource(FOrderServiceClient.getInstance()));
        ShopinfoContract.Presenter presenter = new ShopinfoPresenter(mViewModel, shopRepository);
        mViewModel.setPresenter(presenter);

        FragmentShopinfoBinding binding =
                DataBindingUtil.inflate(inflater, R.layout.fragment_shopinfo, container, false);
        binding.setViewModel((ShopinfoViewModel) mViewModel);
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
