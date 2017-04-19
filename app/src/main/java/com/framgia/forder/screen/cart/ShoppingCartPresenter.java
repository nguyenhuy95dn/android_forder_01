package com.framgia.forder.screen.cart;

import com.framgia.forder.data.model.Cart;
import com.framgia.forder.data.model.CartItem;
import com.framgia.forder.data.model.Domain;
import com.framgia.forder.data.source.DomainRepository;
import com.framgia.forder.data.source.ProductRepository;
import com.framgia.forder.data.source.remote.api.error.BaseException;
import com.framgia.forder.data.source.remote.api.error.SafetyError;
import java.util.List;
import rx.Subscription;
import rx.functions.Action1;
import rx.subscriptions.CompositeSubscription;

/**
 * Listens to user actions from the UI ({@link ShoppingCartFragment}), retrieves the data and
 * updates
 * the UI as required.
 */
final class ShoppingCartPresenter implements ShoppingCartContract.Presenter {
    private static final String TAG = ShoppingCartPresenter.class.getName();
    private final CompositeSubscription mCompositeSubscription;
    private final ShoppingCartContract.ViewModel mViewModel;
    private ProductRepository mProductRepository;
    private DomainRepository mDomainRepository;

    public ShoppingCartPresenter(ShoppingCartContract.ViewModel viewModel,
            ProductRepository productRepository, DomainRepository domainRepository) {
        mViewModel = viewModel;
        mProductRepository = productRepository;
        mDomainRepository = domainRepository;
        mCompositeSubscription = new CompositeSubscription();
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

    @Override
    public void getListCart() {
        Domain domain = mDomainRepository.getCurrentDomain();
        if (domain == null) {
            return;
        }
        Subscription subscriptions = mProductRepository.getAllShoppingCart(domain.getId())
                .subscribe(new Action1<List<Cart>>() {
                    @Override
                    public void call(List<Cart> carts) {
                        mViewModel.onGetListCartSuccess(carts);
                    }
                }, new SafetyError() {
                    @Override
                    public void onSafetyError(BaseException error) {
                        mViewModel.onGetListCartError(error);
                    }
                });
        mCompositeSubscription.add(subscriptions);
    }

    @Override
    public void orderItem(Cart cart) {
        if (cart == null) {
            return;
        }
        //TODO order item in cart
    }

    @Override
    public void upQuantity(CartItem cartItem) {
        Domain domain = mDomainRepository.getCurrentDomain();
        if (cartItem == null || domain == null) {
            return;
        }
        Subscription subscriptions =
                mProductRepository.upQuantity(cartItem.getProductId(), domain.getId())
                        .subscribe(new Action1<Void>() {
                            @Override
                            public void call(Void aVoid) {
                                mViewModel.onUpQuantitySuccess();
                            }
                        }, new SafetyError() {
                            @Override
                            public void onSafetyError(BaseException error) {
                                mViewModel.onUpQuantityError(error);
                            }
                        });
        mCompositeSubscription.add(subscriptions);
    }

    @Override
    public void downQuantity(CartItem cartItem) {
        Domain domain = mDomainRepository.getCurrentDomain();
        if (cartItem == null || domain == null) {
            return;
        }
        Subscription subscriptions =
                mProductRepository.downQuantity(cartItem.getProductId(), domain.getId())
                        .subscribe(new Action1<Void>() {
                            @Override
                            public void call(Void aVoid) {
                                mViewModel.onDownQuantitySuccess();
                            }
                        }, new SafetyError() {
                            @Override
                            public void onSafetyError(BaseException error) {
                                mViewModel.onDownQuantityError(error);
                            }
                        });
        mCompositeSubscription.add(subscriptions);
    }

    @Override
    public void deleteProduct(CartItem cartItem) {
        Domain domain = mDomainRepository.getCurrentDomain();
        if (cartItem == null || domain == null) {
            return;
        }
        Subscription subscriptions =
                mProductRepository.deleteCart(cartItem.getProductId(), domain.getId())
                        .subscribe(new Action1<Void>() {
                            @Override
                            public void call(Void aVoid) {
                                mViewModel.onDeleteProductSuccess();
                            }
                        }, new SafetyError() {
                            @Override
                            public void onSafetyError(BaseException error) {
                                mViewModel.onDeleteProductError(error);
                            }
                        });
        mCompositeSubscription.add(subscriptions);
    }
}
