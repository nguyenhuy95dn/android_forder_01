package com.framgia.forder.screen.productdetail;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.framgia.forder.R;
import com.framgia.forder.data.model.Comment;
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
import com.framgia.forder.databinding.FragmentProductDetailBinding;
import com.framgia.forder.screen.productdetail.adapter.CommentAdapter;
import com.framgia.forder.screen.productdetail.adapter.ProductShopAdapter;
import com.framgia.forder.utils.navigator.Navigator;
import com.framgia.forder.widgets.dialog.DialogManager;
import java.util.ArrayList;
import java.util.List;

/**
 * Detailproduct Screen.
 */
public class ProductDetailFragment extends Fragment {

    private static final String EXTRA_PRODUCT = "EXTRA_PRODUCT";

    private ProductDetailContract.ViewModel mViewModel;

    public static ProductDetailFragment newInstance(Product product) {
        ProductDetailFragment productDetailFragment = new ProductDetailFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable(EXTRA_PRODUCT, product);
        productDetailFragment.setArguments(bundle);
        return productDetailFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Product product = (Product) getArguments().get(EXTRA_PRODUCT);
        List<Product> products = new ArrayList<>();
        List<Comment> comments = new ArrayList<>();
        DialogManager dialogManager = new DialogManager(getContext());
        ProductShopAdapter productAdapter = new ProductShopAdapter(getActivity(), products);
        CommentAdapter commentInProductAdapter = new CommentAdapter(getActivity(), comments);
        Navigator navigator = new Navigator(getParentFragment());
        mViewModel = new ProductDetailViewModel(product, productAdapter, commentInProductAdapter,
                navigator, dialogManager);
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
        ProductDetailContract.Presenter presenter =
                new ProductDetailPresenter(mViewModel, productRepository, domainRepository,
                        userRepository);
        mViewModel.setPresenter(presenter);

        FragmentProductDetailBinding binding =
                DataBindingUtil.inflate(inflater, R.layout.fragment_product_detail, container,
                        false);
        binding.setViewModel((ProductDetailViewModel) mViewModel);
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
    public void setUserVisibleHint(boolean isVisibleToComment) {
        super.setUserVisibleHint(isVisibleToComment);
        if (!isAdded() || !isVisibleToComment) {
            return;
        }
        mViewModel.onReLoadData();
    }
}
