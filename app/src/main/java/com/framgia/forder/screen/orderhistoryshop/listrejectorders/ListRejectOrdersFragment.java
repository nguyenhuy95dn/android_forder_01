package com.framgia.forder.screen.orderhistoryshop.listrejectorders;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.framgia.forder.R;
import com.framgia.forder.data.model.OrderHistory;
import com.framgia.forder.data.model.ShopManagement;
import com.framgia.forder.data.source.OrderRepository;
import com.framgia.forder.data.source.remote.OrderRemoteDataSource;
import com.framgia.forder.data.source.remote.api.service.FOrderServiceClient;
import com.framgia.forder.databinding.FragmentListRejectOrdersBinding;
import com.framgia.forder.utils.navigator.Navigator;

/**
 * ListRejectOrders Screen.
 */
public class ListRejectOrdersFragment extends Fragment {
    private static final String EXTRA_ORDER_SHOP = "EXTRA_ORDER_SHOP";

    private ListRejectOrdersContract.ViewModel mViewModel;

    public static ListRejectOrdersFragment newInstance(ShopManagement shopManagement) {
        ListRejectOrdersFragment listRejectOrdersFragment = new ListRejectOrdersFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable(EXTRA_ORDER_SHOP, shopManagement);
        listRejectOrdersFragment.setArguments(bundle);
        return listRejectOrdersFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Navigator navigator = new Navigator(getParentFragment());
        OrderHistory orderHistory = new OrderHistory();
        ListRejectOrdersAdapter adapter = new ListRejectOrdersAdapter(getActivity());
        ShopManagement shopManagement = (ShopManagement) getArguments().get(EXTRA_ORDER_SHOP);
        mViewModel = new ListRejectOrdersViewModel(getContext().getApplicationContext(), navigator,
                adapter, orderHistory, shopManagement);

        OrderRepository orderRepository =
                new OrderRepository(new OrderRemoteDataSource(FOrderServiceClient.getInstance()));
        ListRejectOrdersContract.Presenter presenter =
                new ListRejectOrdersPresenter(mViewModel, orderRepository);
        mViewModel.setPresenter(presenter);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {

        FragmentListRejectOrdersBinding binding =
                DataBindingUtil.inflate(inflater, R.layout.fragment_list_reject_orders, container,
                        false);
        binding.setViewModel((ListRejectOrdersViewModel) mViewModel);
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
