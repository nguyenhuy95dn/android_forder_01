package com.framgia.forder.screen.ordershop;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import com.framgia.forder.R;
import com.framgia.forder.data.model.ShopManagement;
import com.framgia.forder.data.source.DomainRepository;
import com.framgia.forder.data.source.OrderRepository;
import com.framgia.forder.data.source.UserDataSource;
import com.framgia.forder.data.source.local.DomainLocalDataSource;
import com.framgia.forder.data.source.local.UserLocalDataSource;
import com.framgia.forder.data.source.local.sharedprf.SharedPrefsApi;
import com.framgia.forder.data.source.local.sharedprf.SharedPrefsImpl;
import com.framgia.forder.data.source.remote.DomainRemoteDataSource;
import com.framgia.forder.data.source.remote.OrderRemoteDataSource;
import com.framgia.forder.data.source.remote.api.service.FOrderServiceClient;
import com.framgia.forder.databinding.FragmentOrdershopBinding;
import com.framgia.forder.utils.navigator.Navigator;
import java.util.ArrayList;
import java.util.List;

/**
 * OrderShop Screen.
 */
public class OrderShopFragment extends Fragment {
    private static final String EXTRA_ORDER_SHOP = "EXTRA_ORDER_SHOP";

    private OrderShopContract.ViewModel mViewModel;

    public static OrderShopFragment newInstance(ShopManagement shopManagement) {
        OrderShopFragment orderShopFragment = new OrderShopFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable(EXTRA_ORDER_SHOP, shopManagement);
        orderShopFragment.setArguments(bundle);
        return orderShopFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {
        Navigator navigator = new Navigator(getParentFragment());
        ShopManagement shopManagement = (ShopManagement) getArguments().get(EXTRA_ORDER_SHOP);
        OrderShopAdapter adapter = new OrderShopAdapter(getActivity());
        List<String> domains = new ArrayList<>();
        ArrayAdapter<String> adapterDomain =
                new ArrayAdapter<>(getActivity(), android.R.layout.simple_selectable_list_item,
                        domains);
        mViewModel =
                new OrderShopViewModel(getContext().getApplicationContext(), navigator, adapter,
                        shopManagement, adapterDomain);

        OrderRepository orderRepository =
                new OrderRepository(new OrderRemoteDataSource(FOrderServiceClient.getInstance()));

        SharedPrefsApi prefsApi = new SharedPrefsImpl(getActivity().getApplicationContext());
        UserDataSource.LocalDataSource userLocalDataSource = new UserLocalDataSource(prefsApi);
        DomainRepository repository =
                new DomainRepository(new DomainRemoteDataSource(FOrderServiceClient.getInstance()),
                        new DomainLocalDataSource(prefsApi, userLocalDataSource));

        OrderShopContract.Presenter presenter =
                new OrderShopPresenter(mViewModel, orderRepository, repository);
        mViewModel.setPresenter(presenter);
        FragmentOrdershopBinding binding =
                DataBindingUtil.inflate(inflater, R.layout.fragment_ordershop, container, false);
        binding.setViewModel((OrderShopViewModel) mViewModel);
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
