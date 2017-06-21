package com.framgia.forder.screen.shopindomain;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.framgia.forder.R;
import com.framgia.forder.data.model.DomainManagement;
import com.framgia.forder.data.source.ShopRepository;
import com.framgia.forder.data.source.UserRepository;
import com.framgia.forder.data.source.local.UserLocalDataSource;
import com.framgia.forder.data.source.local.sharedprf.SharedPrefsApi;
import com.framgia.forder.data.source.local.sharedprf.SharedPrefsImpl;
import com.framgia.forder.data.source.remote.ShopRemoteDataSource;
import com.framgia.forder.data.source.remote.api.service.FOrderServiceClient;
import com.framgia.forder.databinding.FragmentShopInDomainBinding;
import com.framgia.forder.utils.navigator.Navigator;
import com.framgia.forder.widgets.dialog.DialogManager;

/**
 * Shopindomain Screen.
 */
public class ShopInDomainFragment extends Fragment {
    private static final String EXTRA_DOMAIN = "EXTRA_DOMAIN";

    private ShopInDomainContract.ViewModel mViewModel;

    public static ShopInDomainFragment newInstance(DomainManagement domainManagement) {
        ShopInDomainFragment shopInDomainFragment = new ShopInDomainFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable(EXTRA_DOMAIN, domainManagement);
        shopInDomainFragment.setArguments(bundle);
        return shopInDomainFragment;
    }

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ShopInDomainAdapter adapter = new ShopInDomainAdapter(getActivity());
        Navigator navigator = new Navigator(getParentFragment());
        DomainManagement domainManagement = (DomainManagement) getArguments().get(EXTRA_DOMAIN);
        DialogManager dialogManager = new DialogManager(getActivity());

        mViewModel = new ShopInDomainViewModel(adapter, navigator, domainManagement, dialogManager);

        ShopRepository shopRepository =
                new ShopRepository(new ShopRemoteDataSource(FOrderServiceClient.getInstance()));
        SharedPrefsApi prefsApi = new SharedPrefsImpl(getActivity());
        UserRepository userRepository = new UserRepository(null, new UserLocalDataSource(prefsApi));

        ShopInDomainContract.Presenter presenter =
                new ShopInDomainPresenter(mViewModel, shopRepository, userRepository);
        mViewModel.setPresenter(presenter);

        FragmentShopInDomainBinding binding =
                DataBindingUtil.inflate(inflater, R.layout.fragment_shop_in_domain, container,
                        false);
        binding.setViewModel((ShopInDomainViewModel) mViewModel);
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
