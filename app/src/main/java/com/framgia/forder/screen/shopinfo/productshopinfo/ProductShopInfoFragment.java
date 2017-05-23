package com.framgia.forder.screen.shopinfo.productshopinfo;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.framgia.forder.R;
import com.framgia.forder.data.model.ShopManagement;
import com.framgia.forder.data.source.ProductRepository;
import com.framgia.forder.data.source.local.ProductLocalDataSource;
import com.framgia.forder.data.source.local.realm.RealmApi;
import com.framgia.forder.data.source.remote.ProductRemoteDataSource;
import com.framgia.forder.data.source.remote.api.service.FOrderServiceClient;
import com.framgia.forder.databinding.FragmentProductShopInfoBinding;
import com.framgia.forder.utils.navigator.Navigator;

/**
 * ProductShopInfo Screen.
 */
public class ProductShopInfoFragment extends Fragment {
    private static final String EXTRA_SHOPMANAGEMENT = "EXTRA_SHOPMANAGEMENT";

    private ProductShopInfoContract.ViewModel mViewModel;

    public static ProductShopInfoFragment newInstance(ShopManagement shopManagement) {
        ProductShopInfoFragment productShopInfoFragment = new ProductShopInfoFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable(EXTRA_SHOPMANAGEMENT, shopManagement);
        productShopInfoFragment.setArguments(bundle);
        return productShopInfoFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Navigator navigator = new Navigator(getParentFragment().getParentFragment());
        ShopManagement shopManagement = (ShopManagement) getArguments().get(EXTRA_SHOPMANAGEMENT);
        ProductShopInformationAdapter adapter = new ProductShopInformationAdapter(getActivity());
        mViewModel = new ProductShopInfoViewModel(navigator, adapter, shopManagement);

        RealmApi realmApi = new RealmApi();
        ProductRepository productRepository = new ProductRepository(
                new ProductRemoteDataSource(FOrderServiceClient.getInstance()),
                new ProductLocalDataSource(realmApi));

        ProductShopInfoContract.Presenter presenter =
                new ProductShopInfoPresenter(mViewModel, productRepository);
        mViewModel.setPresenter(presenter);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {

        FragmentProductShopInfoBinding binding =
                DataBindingUtil.inflate(inflater, R.layout.fragment_product_shop_info, container,
                        false);
        binding.setViewModel((ProductShopInfoViewModel) mViewModel);
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
