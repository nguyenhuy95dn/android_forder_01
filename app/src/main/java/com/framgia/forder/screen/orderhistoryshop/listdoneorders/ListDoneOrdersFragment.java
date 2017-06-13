package com.framgia.forder.screen.orderhistoryshop.listdoneorders;

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
import com.framgia.forder.databinding.FragmentListDoneOrdersBinding;
import com.framgia.forder.utils.navigator.Navigator;

/**
 * ListDoneOrders Screen.
 */
public class ListDoneOrdersFragment extends Fragment {
    private static final String EXTRA_ORDER_SHOP = "EXTRA_ORDER_SHOP";

    private ListDoneOrdersContract.ViewModel mViewModel;

    public static ListDoneOrdersFragment newInstance(ShopManagement shopManagement) {
        ListDoneOrdersFragment listDoneOrdersFragment = new ListDoneOrdersFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable(EXTRA_ORDER_SHOP, shopManagement);
        listDoneOrdersFragment.setArguments(bundle);
        return listDoneOrdersFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Navigator navigator = new Navigator(getParentFragment());
        ShopManagement shopManagement = (ShopManagement) getArguments().get(EXTRA_ORDER_SHOP);
        OrderHistory orderHistory = new OrderHistory();
        ListDoneOrderAdapter adapter = new ListDoneOrderAdapter(getActivity());

        mViewModel = new ListDoneOrdersViewModel(getContext().getApplicationContext(), navigator,
                orderHistory, adapter,
                shopManagement);

        OrderRepository orderRepository =
                new OrderRepository(new OrderRemoteDataSource(FOrderServiceClient.getInstance()));
        ListDoneOrdersContract.Presenter presenter =
                new ListDoneOrdersPresenter(mViewModel, orderRepository);
        mViewModel.setPresenter(presenter);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {

        FragmentListDoneOrdersBinding binding =
                DataBindingUtil.inflate(inflater, R.layout.fragment_list_done_orders, container,
                        false);
        binding.setViewModel((ListDoneOrdersViewModel) mViewModel);
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
