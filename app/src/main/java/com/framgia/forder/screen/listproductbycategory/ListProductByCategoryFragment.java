package com.framgia.forder.screen.listproductbycategory;

import android.app.Activity;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.framgia.forder.R;
import com.framgia.forder.data.model.Product;
import com.framgia.forder.data.source.DomainRepository;
import com.framgia.forder.data.source.ProductRepository;
import com.framgia.forder.data.source.UserRepository;
import com.framgia.forder.data.source.local.DomainLocalDataSource;
import com.framgia.forder.data.source.local.ProductLocalDataSource;
import com.framgia.forder.data.source.local.UserLocalDataSource;
import com.framgia.forder.data.source.local.realm.RealmApi;
import com.framgia.forder.data.source.local.sharedprf.SharedPrefsApi;
import com.framgia.forder.data.source.local.sharedprf.SharedPrefsImpl;
import com.framgia.forder.data.source.remote.DomainRemoteDataSource;
import com.framgia.forder.data.source.remote.ProductRemoteDataSource;
import com.framgia.forder.data.source.remote.UserRemoteDataSource;
import com.framgia.forder.data.source.remote.api.service.FOrderServiceClient;
import com.framgia.forder.databinding.FragmentListProductByCategoryBinding;
import com.framgia.forder.screen.listProduct.adapter.ListProductAdapter;
import com.framgia.forder.screen.main.LoadCartListener;
import com.framgia.forder.utils.navigator.Navigator;
import com.framgia.forder.widgets.dialog.DialogManager;
import java.util.ArrayList;
import java.util.List;

/**
 * Listproductbycategory Screen.
 */
public class ListProductByCategoryFragment extends Fragment {

    private static final String CATEGORY_ID = "CATEGORY_ID";

    private ListProductByCategoryContract.ViewModel mViewModel;
    private LoadCartListener mLoadCartListener;

    public static ListProductByCategoryFragment newInstance(int categoryId) {
        ListProductByCategoryFragment listProductByCategoryFragment =
                new ListProductByCategoryFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(CATEGORY_ID, categoryId);
        listProductByCategoryFragment.setArguments(bundle);
        return listProductByCategoryFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {
        int categoryId = getArguments().getInt(CATEGORY_ID);
        List<Product> products = new ArrayList<>();
        ListProductAdapter productAdapter = new ListProductAdapter(getActivity(), products);
        Navigator navigator = new Navigator(getParentFragment());
        DialogManager dialogManager = new DialogManager(getActivity());
        mViewModel = new ListProductByCategoryViewModel(productAdapter, navigator, dialogManager,
                mLoadCartListener, categoryId);

        RealmApi realmApi = new RealmApi();

        SharedPrefsApi prefsApi = new SharedPrefsImpl(getActivity());
        DomainRepository domainRepository =
                new DomainRepository(new DomainRemoteDataSource(FOrderServiceClient.getInstance()),
                        new DomainLocalDataSource(prefsApi, new UserLocalDataSource(prefsApi)));
        int currentDomainId = domainRepository.getCurrentDomain().getId();
        ProductRepository productRepository = new ProductRepository(
                new ProductRemoteDataSource(FOrderServiceClient.getInstance()),
                new ProductLocalDataSource(realmApi), currentDomainId);
        UserRepository userRepository =
                new UserRepository(new UserRemoteDataSource(FOrderServiceClient.getInstance()),
                        new UserLocalDataSource(prefsApi));
        ListProductByCategoryContract.Presenter presenter =
                new ListProductByCategoryPresenter(mViewModel, productRepository, userRepository,
                        domainRepository);
        mViewModel.setPresenter(presenter);

        FragmentListProductByCategoryBinding binding =
                DataBindingUtil.inflate(inflater, R.layout.fragment_list_product_by_category,
                        container, false);
        binding.setViewModel((ListProductByCategoryViewModel) mViewModel);
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
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mLoadCartListener = (LoadCartListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + " must implement LoadCartListener");
        }
    }
}
