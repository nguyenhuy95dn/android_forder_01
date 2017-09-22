package com.framgia.forder.screen.productinshop;

import com.framgia.forder.data.model.Cart;
import com.framgia.forder.data.model.Product;
import com.framgia.forder.data.source.DomainRepository;
import com.framgia.forder.data.source.ProductRepository;
import com.framgia.forder.data.source.UserRepository;
import com.framgia.forder.data.source.remote.api.error.BaseException;
import com.framgia.forder.data.source.remote.api.error.SafetyError;
import com.framgia.forder.data.source.remote.api.request.OrderRequest;
import com.framgia.forder.data.source.remote.api.response.OrderCartResponse;
import java.util.List;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Listens to user actions from the UI ({@link ProductInShopFragment}), retrieves the data and
 * updates
 * the UI as required.
 */
final class ProductInShopPresenter implements ProductInShopContract.Presenter {
    private static final String TAG = ProductInShopPresenter.class.getName();

    private final ProductInShopContract.ViewModel mViewModel;
    private final ProductRepository mProductRepository;
    private final CompositeSubscription mCompositeSubscription;
    private final UserRepository mUserRepository;
    private final DomainRepository mDomainRepository;

    ProductInShopPresenter(ProductInShopContract.ViewModel viewModel,
            ProductRepository productRepository, UserRepository userRepository,
            DomainRepository domainRepository) {
        mViewModel = viewModel;
        mProductRepository = productRepository;
        mUserRepository = userRepository;
        mDomainRepository = domainRepository;
        mCompositeSubscription = new CompositeSubscription();
    }

    @Override
    public void onStart() {
        mProductRepository.openTransaction();
    }

    @Override
    public void onStop() {
        mProductRepository.closeTransaction();
        mCompositeSubscription.clear();
    }

    @Override
    public void addToCart(final Product product) {
        if (product == null) {
            return;
        }
        Subscription subscription =
                mProductRepository.addToCart(product).subscribe(new Subscriber<Void>() {
                    @Override
                    public void onCompleted() {
                        mViewModel.onAddToCartSuccess(product);
                    }

                    @Override
                    public void onError(Throwable e) {
                        mViewModel.onAddToCartError(e);
                    }

                    @Override
                    public void onNext(Void aVoid) {
                        // No-Op
                    }
                });
        mCompositeSubscription.add(subscription);
    }

    @Override
    public void getListProductInShop(int shopId) {
        Subscription subscription = mProductRepository.getListProductInShop(shopId)
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
                        mViewModel.onGetMessageError(error);
                    }
                });
        mCompositeSubscription.add(subscription);
    }

    @Override
    public void orderProduct(final OrderRequest orderRequest) {
        for (Cart cart : orderRequest.getCartList()) {
            cart.setUserId(mUserRepository.getUser().getId());
            cart.setDomainId(mDomainRepository.getCurrentDomain().getId());
        }
        Subscription subscription = mProductRepository.orderCart(orderRequest)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<OrderCartResponse>() {
                    @Override
                    public void call(OrderCartResponse orderCartResponse) {
                        mViewModel.onOrderProductSuccess();
                    }
                }, new SafetyError() {
                    @Override
                    public void onSafetyError(BaseException error) {
                        mViewModel.onGetMessageError(error);
                    }
                });
        mCompositeSubscription.add(subscription);
    }

    @Override
    public void getListCart(final Product product) {
        Subscription subscriptions =
                mProductRepository.getAllShoppingCart().subscribe(new Action1<List<Cart>>() {
                    @Override
                    public void call(List<Cart> carts) {
                        mViewModel.onGetListCartSuccess(carts, product);
                    }
                }, new SafetyError() {
                    @Override
                    public void onSafetyError(BaseException error) {
                        mViewModel.onGetListCartError(error);
                    }
                });
        mCompositeSubscription.add(subscriptions);
    }
}
