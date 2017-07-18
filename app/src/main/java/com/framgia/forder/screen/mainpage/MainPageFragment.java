package com.framgia.forder.screen.mainpage;

import android.app.Activity;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.framgia.forder.R;
import com.framgia.forder.data.model.Category;
import com.framgia.forder.data.model.Product;
import com.framgia.forder.data.source.CategoryRepository;
import com.framgia.forder.data.source.DomainRepository;
import com.framgia.forder.data.source.ProductRepository;
import com.framgia.forder.data.source.ShopRepository;
import com.framgia.forder.data.source.UserRepository;
import com.framgia.forder.data.source.local.DomainLocalDataSource;
import com.framgia.forder.data.source.local.ProductLocalDataSource;
import com.framgia.forder.data.source.local.UserLocalDataSource;
import com.framgia.forder.data.source.local.realm.RealmApi;
import com.framgia.forder.data.source.local.sharedprf.SharedPrefsApi;
import com.framgia.forder.data.source.local.sharedprf.SharedPrefsImpl;
import com.framgia.forder.data.source.remote.CategoryRemoteDataSource;
import com.framgia.forder.data.source.remote.DomainRemoteDataSource;
import com.framgia.forder.data.source.remote.ProductRemoteDataSource;
import com.framgia.forder.data.source.remote.ShopRemoteDataSource;
import com.framgia.forder.data.source.remote.UserRemoteDataSource;
import com.framgia.forder.data.source.remote.api.service.FOrderServiceClient;
import com.framgia.forder.databinding.FragmentMainPageBinding;
import com.framgia.forder.screen.main.LoadCartListener;
import com.framgia.forder.screen.mainpage.category.CategoryAdapter;
import com.framgia.forder.screen.mainpage.product.ProductAdapter;
import com.framgia.forder.screen.mainpage.shop.ShopPageAdapter;
import com.framgia.forder.utils.navigator.Navigator;
import com.framgia.forder.widgets.dialog.DialogManager;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Duong on 4/13/2017.
 */

public class MainPageFragment extends Fragment {
    private MainPageContract.ViewModel mViewModel;
    private LoadCartListener mLoadCartListener;

    public static MainPageFragment newInstance() {
        return new MainPageFragment();
    }

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {

        List<Product> products = new ArrayList<>();
        ProductAdapter productAdapter = new ProductAdapter(getActivity(), products);
        List<Category> categories = new ArrayList<>();
        CategoryAdapter categoryAdapter = new CategoryAdapter(getActivity(), categories);
        Navigator navigator = new Navigator(getParentFragment());
        ShopPageAdapter shopPageAdapter = new ShopPageAdapter(getFragmentManager());
        DialogManager dialogManager = new DialogManager(getActivity());

        mViewModel = new MainPageViewModel(getContext().getApplicationContext(), productAdapter,
                navigator, categoryAdapter, shopPageAdapter, dialogManager, mLoadCartListener);

        RealmApi realmApi = new RealmApi();
        SharedPrefsApi prefsApi = new SharedPrefsImpl(getActivity());
        DomainRepository domainRepository =
                new DomainRepository(new DomainRemoteDataSource(FOrderServiceClient.getInstance()),
                        new DomainLocalDataSource(prefsApi, new UserLocalDataSource(prefsApi)));
        int currentDomainId = domainRepository.getCurrentDomain().getId();
        ProductRepository productRepository = new ProductRepository(
                new ProductRemoteDataSource(FOrderServiceClient.getInstance()),
                new ProductLocalDataSource(realmApi), currentDomainId);
        ShopRepository shopRepository =
                new ShopRepository(new ShopRemoteDataSource(FOrderServiceClient.getInstance()));
        CategoryRepository categoryRepository = new CategoryRepository(
                new CategoryRemoteDataSource(FOrderServiceClient.getInstance()));
        UserRepository userRepository =
                new UserRepository(new UserRemoteDataSource(FOrderServiceClient.getInstance()),
                        new UserLocalDataSource(prefsApi));

        MainPageContract.Presenter presenter =
                new MainPagePresenter(mViewModel, productRepository, domainRepository,
                        shopRepository, categoryRepository, userRepository);
        mViewModel.setPresenter(presenter);

        FragmentMainPageBinding binding =
                DataBindingUtil.inflate(inflater, R.layout.fragment_main_page, container, false);
        binding.setViewModel((MainPageViewModel) mViewModel);
        binding.setMainPage(this);
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

    public void reloadData() {
        mViewModel.reloadData();
    }
}
