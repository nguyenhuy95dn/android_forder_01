package com.framgia.forder.screen.mainpage;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.framgia.forder.R;
import com.framgia.forder.data.model.Product;
import com.framgia.forder.data.model.Shop;
import com.framgia.forder.data.source.ProductRepository;
import com.framgia.forder.data.source.local.realm.RealmApi;
import com.framgia.forder.data.source.local.ProductLocalDataSource;
import com.framgia.forder.databinding.FragmentMainPageBinding;
import com.framgia.forder.screen.mainpage.product.ProductAdapter;
import com.framgia.forder.screen.mainpage.shop.ShopAdapter;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Duong on 4/13/2017.
 */

public class MainPageFragment extends Fragment {
    private MainPageContract.ViewModel mViewModel;

    public static MainPageFragment newInstance() {
        return new MainPageFragment();
    }

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {

        List<Product> products = new ArrayList<>();
        List<Shop> shops = new ArrayList<>();
        ProductAdapter productAdapter = new ProductAdapter(getActivity(), products);
        ShopAdapter shopAdapter = new ShopAdapter(getActivity(), shops);
        mViewModel = new MainPageViewModel(productAdapter, shopAdapter);

        RealmApi realmApi = new RealmApi();
        ProductRepository repository =
                new ProductRepository(new ProductLocalDataSource(realmApi));
        MainPageContract.Presenter presenter = new MainPagePresenter(mViewModel, repository);

        mViewModel.setPresenter(presenter);
        FragmentMainPageBinding binding =
                DataBindingUtil.inflate(inflater, R.layout.fragment_main_page, container, false);
        binding.setViewModel((MainPageViewModel) mViewModel);
        binding.setMainPage(this);
        return binding.getRoot();
    }
}
