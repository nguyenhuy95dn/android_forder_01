package com.framgia.forder.screen.orderhistoryshop;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.framgia.forder.R;
import com.framgia.forder.data.model.ShopManagement;
import com.framgia.forder.data.source.OrderRepository;
import com.framgia.forder.data.source.remote.OrderRemoteDataSource;
import com.framgia.forder.data.source.remote.api.service.FOrderServiceClient;
import com.framgia.forder.databinding.FragmentOrderHistoryShopBinding;
import com.framgia.forder.widgets.dialog.DialogManager;

/**
 * OrderHistoryShop Screen.
 */
public class OrderHistoryShopFragment extends Fragment {
    private static final String EXTRA_ORDER_HISTORY = "EXTRA_ORDER_HISTORY";

    private OrderHistoryShopContract.ViewModel mViewModel;

    public static OrderHistoryShopFragment newInstance(ShopManagement shopManagement) {
        OrderHistoryShopFragment orderHistoryShopFragment = new OrderHistoryShopFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable(EXTRA_ORDER_HISTORY, shopManagement);
        orderHistoryShopFragment.setArguments(bundle);
        return orderHistoryShopFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ShopManagement shopManagement = (ShopManagement) getArguments().get(EXTRA_ORDER_HISTORY);
        OrderHistoryPageAdapter adapter =
                new OrderHistoryPageAdapter(getActivity(), getChildFragmentManager(),
                        shopManagement);
        DialogManager dialogManager = new DialogManager(getActivity());
        mViewModel = new OrderHistoryShopViewModel(adapter, dialogManager, shopManagement);

        OrderRepository orderRepository =
                new OrderRepository(new OrderRemoteDataSource(FOrderServiceClient.getInstance()));
        OrderHistoryShopContract.Presenter presenter =
                new OrderHistoryShopPresenter(mViewModel, orderRepository);
        mViewModel.setPresenter(presenter);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {

        FragmentOrderHistoryShopBinding binding =
                DataBindingUtil.inflate(inflater, R.layout.fragment_order_history_shop, container,
                        false);
        binding.setViewModel((OrderHistoryShopViewModel) mViewModel);
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
