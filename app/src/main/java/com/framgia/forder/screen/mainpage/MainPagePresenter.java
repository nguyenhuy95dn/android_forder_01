package com.framgia.forder.screen.mainpage;

import com.framgia.forder.data.model.Product;
import com.framgia.forder.data.source.DomainRepository;
import com.framgia.forder.data.source.ProductRepository;
import com.framgia.forder.data.source.remote.api.error.BaseException;
import com.framgia.forder.data.source.remote.api.error.SafetyError;
import java.util.List;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

final class MainPagePresenter implements MainPageContract.Presenter {
    private static final String TAG = MainPagePresenter.class.getName();
    private final MainPageContract.ViewModel mViewModel;
    private final CompositeSubscription mCompositeSubscription;
    private ProductRepository mProductRepository;
    private DomainRepository mDomainRepository;

    public MainPagePresenter(MainPageContract.ViewModel viewModel,
            ProductRepository productRepository, DomainRepository domainRepository) {
        mViewModel = viewModel;
        mProductRepository = productRepository;
        mCompositeSubscription = new CompositeSubscription();
        mDomainRepository = domainRepository;
    }

    @Override
    public void addToCart(Product product) {
        if (product == null) {
            return;
        }
        mProductRepository.addToCart(product);
        mCompositeSubscription.clear();
    }

    @Override
    public void getListProduct() {
        Subscription subscription =
                mProductRepository.getListProduct(mDomainRepository.getDomainId())
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Action1<List<Product>>() {
                            @Override
                            public void call(List<Product> products) {
                                mViewModel.onGetListProductSuccess(products);
                            }
                        }, new SafetyError() {
                            @Override
                            public void onSafetyError(BaseException error) {
                                mViewModel.onGetListProductError(error);
                            }
                        });
        mCompositeSubscription.add(subscription);
    }

    @Override
    public void onStart() {
        mProductRepository.openTransaction();
    }

    @Override
    public void onStop() {
        mCompositeSubscription.clear();
        mProductRepository.closeTransaction();
    }
}
