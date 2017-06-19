package com.framgia.forder.screen.searchproduct;

import com.framgia.forder.data.model.Product;
import com.framgia.forder.data.source.ProductRepository;
import rx.Subscriber;
import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * Listens to user actions from the UI ({@link ProductSearchResultFragment}), retrieves the data and
 * updates
 * the UI as required.
 */
final class ProductSearchResultPresenter implements ProductSearchResultContract.Presenter {
    private static final String TAG = ProductSearchResultPresenter.class.getName();

    private final ProductSearchResultContract.ViewModel mViewModel;
    private final CompositeSubscription mCompositeSubscription;
    private final ProductRepository mProductRepository;

    ProductSearchResultPresenter(ProductSearchResultContract.ViewModel viewModel,
            ProductRepository productRepository) {
        mViewModel = viewModel;
        mProductRepository = productRepository;
        mCompositeSubscription = new CompositeSubscription();
    }

    @Override
    public void onStart() {
    }

    @Override
    public void onStop() {
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
}
