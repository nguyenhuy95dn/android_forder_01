package com.framgia.forder.screen.addtocart;

import com.framgia.forder.data.model.Product;
import com.framgia.forder.data.source.ProductRepository;
import com.framgia.forder.data.source.remote.api.error.BaseException;
import com.framgia.forder.data.source.remote.api.error.SafetyError;
import rx.Subscriber;
import rx.Subscription;
import rx.functions.Action1;
import rx.subscriptions.CompositeSubscription;

/**
 * Listens to user actions from the UI ({@link AddToCartFragment}), retrieves the data and updates
 * the UI as required.
 */
final class AddToCartPresenter implements AddToCartContract.Presenter {
    private static final String TAG = AddToCartPresenter.class.getName();

    private final AddToCartContract.ViewModel mViewModel;
    private final ProductRepository mProductRepository;
    private final CompositeSubscription mCompositeSubscription;

    AddToCartPresenter(AddToCartContract.ViewModel viewModel, ProductRepository productRepository) {
        mViewModel = viewModel;
        mProductRepository = productRepository;
        mCompositeSubscription = new CompositeSubscription();
    }

    @Override
    public void onStart() {
    }

    @Override
    public void onStop() {
        mCompositeSubscription.clear();
    }

    @Override
    public void getTotalPrice() {
        Subscription subscriptions =
                mProductRepository.getTotalPrice().subscribe(new Action1<Double>() {
                    @Override
                    public void call(Double totalPrice) {
                        mViewModel.onGetTotalPriceSuccess(totalPrice);
                    }
                }, new SafetyError() {
                    @Override
                    public void onSafetyError(BaseException error) {
                        mViewModel.onGetTotalPriceError(error);
                    }
                });
        mCompositeSubscription.add(subscriptions);
    }

    @Override
    public void addToCart(Product product) {
        if (product == null) {
            return;
        }
        Subscription subscription =
                mProductRepository.addToCart(product).subscribe(new Subscriber<Void>() {
                    @Override
                    public void onCompleted() {
                        mViewModel.onAddToCartSuccess();
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
    public void downQuantity(Product product) {
        if (product == null) {
            return;
        }
        Subscription subscriptions =
                mProductRepository.downQuantity(product.getId()).subscribe(new Subscriber<Void>() {
                    @Override
                    public void onCompleted() {
                        mViewModel.onDownQuantitySuccess();
                    }

                    @Override
                    public void onError(Throwable e) {
                        mViewModel.onDownQuantityError(e);
                    }

                    @Override
                    public void onNext(Void aVoid) {
                        // No-Op
                    }
                });
        mCompositeSubscription.add(subscriptions);
    }

    @Override
    public void deleteProduct(Product product) {
        if (product == null) {
            return;
        }
        Subscription subscriptions =
                mProductRepository.deleteCart(product.getId()).subscribe(new Subscriber<Void>() {
                    @Override
                    public void onCompleted() {
                        mViewModel.onDeleteProductInCartSuccess();
                    }

                    @Override
                    public void onError(Throwable e) {
                        mViewModel.onDeleteProductInCartError(e);
                    }

                    @Override
                    public void onNext(Void aVoid) {
                        // No-Op
                    }
                });
        mCompositeSubscription.add(subscriptions);
    }
}
