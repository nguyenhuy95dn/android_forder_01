package com.framgia.forder.screen.listacceptorder;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.framgia.forder.R;
import com.framgia.forder.data.model.OrderDetail;
import com.framgia.forder.data.source.OrderRepository;
import com.framgia.forder.data.source.remote.OrderRemoteDataSource;
import com.framgia.forder.data.source.remote.api.service.FOrderServiceClient;
import com.framgia.forder.databinding.FragmentListAcceptOrderBinding;
import com.framgia.forder.utils.navigator.Navigator;
import com.framgia.forder.widgets.dialog.DialogManager;
import java.util.ArrayList;
import java.util.List;

/**
 * Listacceptorder Screen.
 */
public class ListAcceptOrderFragment extends Fragment {

    private static final String EXTRA_ORDER = "EXTRA_ORDER";
    private static final String EXTRA_SHOP_ID = "EXTRA_SHOP_ID";

    private ListAcceptOrderContract.ViewModel mViewModel;

    public static ListAcceptOrderFragment newInstance(List<OrderDetail> orderDetails, int shopId) {
        ListAcceptOrderFragment listAcceptOrderFragment = new ListAcceptOrderFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList(EXTRA_ORDER, (ArrayList<? extends Parcelable>) orderDetails);
        bundle.putInt(EXTRA_SHOP_ID, shopId);
        listAcceptOrderFragment.setArguments(bundle);
        return listAcceptOrderFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {
        List<OrderDetail> orderDetails = (List<OrderDetail>) getArguments().get(EXTRA_ORDER);
        int shopId = (int) getArguments().get(EXTRA_SHOP_ID);
        ListAcceptOrderAdapter adapter =
                new ListAcceptOrderAdapter(getActivity().getApplicationContext());
        adapter.updateData(orderDetails);
        Navigator navigator = new Navigator(getParentFragment());
        DialogManager dialogManager = new DialogManager(getActivity());
        mViewModel = new ListAcceptOrderViewModel(adapter, navigator, dialogManager, shopId);

        OrderRepository orderRepository =
                new OrderRepository(new OrderRemoteDataSource(FOrderServiceClient.getInstance()));
        ListAcceptOrderContract.Presenter presenter =
                new ListAcceptOrderPresenter(mViewModel, orderRepository);
        mViewModel.setPresenter(presenter);

        FragmentListAcceptOrderBinding binding =
                DataBindingUtil.inflate(inflater, R.layout.fragment_list_accept_order, container,
                        false);
        binding.setViewModel((ListAcceptOrderViewModel) mViewModel);
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
