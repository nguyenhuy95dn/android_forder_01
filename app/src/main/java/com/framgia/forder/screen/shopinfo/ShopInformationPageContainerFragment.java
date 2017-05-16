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
import com.framgia.forder.databinding.FragmentShopInfomationPageContainerBinding;

/**
 * shopinfo Screen.
 */
public class ShopInformationPageContainerFragment extends Fragment {

    private static final String EXTRA_SHOP = "EXTRA_SHOP";
    private ShopInformationPageContainerContract.ViewModel mViewModel;

    public static ShopInformationPageContainerFragment newInstance(ShopManagement shopManagement) {
        ShopInformationPageContainerFragment shopinfoFragment =
                new ShopInformationPageContainerFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable(EXTRA_SHOP, shopManagement);
        shopinfoFragment.setArguments(bundle);
        return shopinfoFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ShopManagement shopManagement = (ShopManagement) getArguments().get(EXTRA_SHOP);
        ShopInformationPageAdapter adapter =
                new ShopInformationPageAdapter(getActivity(), getChildFragmentManager(),
                        shopManagement);
        mViewModel = new ShopInformationPageContainerViewModel(adapter);

        ShopInformationPageContainerContract.Presenter presenter =
                new ShopInformationPageContainerPresenter(mViewModel);
        mViewModel.setPresenter(presenter);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {

        FragmentShopInfomationPageContainerBinding binding =
                DataBindingUtil.inflate(inflater, R.layout.fragment_shop_infomation_page_container,
                        container, false);
        binding.setViewModel((ShopInformationPageContainerViewModel) mViewModel);
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
