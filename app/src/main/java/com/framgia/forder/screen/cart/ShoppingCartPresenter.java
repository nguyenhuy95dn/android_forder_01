package com.framgia.forder.screen.cart;

import com.framgia.forder.data.model.Cart;
import com.framgia.forder.data.model.CartItem;
import com.framgia.forder.data.source.ProductRepository;
import com.framgia.forder.data.source.UserRepository;
import com.framgia.forder.data.source.remote.api.error.BaseException;
import com.framgia.forder.data.source.remote.api.error.SafetyError;
import com.framgia.forder.data.source.remote.api.request.OrderRequest;
import com.framgia.forder.data.source.remote.api.response.OrderCartResponse;
import com.framgia.forder.utils.Utils;
import java.util.ArrayList;
import java.util.List;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;
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
    private final ProductRepository mProductRepository;
    private final UserRepository mUserRepository;

    ShoppingCartPresenter(ShoppingCartContract.ViewModel viewModel,
            ProductRepository productRepository, UserRepository userRepository) {
        mViewModel = viewModel;
        mProductRepository = productRepository;
        mUserRepository = userRepository;
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
        Subscription subscriptions =
                mProductRepository.getAllShoppingCart().subscribe(new Action1<List<Cart>>() {
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
    public void orderOneShop(final OrderRequest orderRequest) {
        for (Cart cart : orderRequest.getCartList()) {
            cart.setUserId(mUserRepository.getUser().getId());
        }
        Subscription subscription = mProductRepository.orderCart(orderRequest)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<OrderCartResponse>() {
                    @Override
                    public void call(OrderCartResponse orderCartResponse) {
                        mViewModel.onOrderOneShopSuccess();
                    }
                }, new SafetyError() {
                    @Override
                    public void onSafetyError(BaseException error) {
                        mViewModel.onOrderOneShopError(error);
                    }
                });
        mCompositeSubscription.add(subscription);
    }

    @Override
    public void orderAllShop(final OrderRequest orderRequest) {
        for (Cart cart : orderRequest.getCartList()) {
            cart.setUserId(mUserRepository.getUser().getId());
        }
        Subscription subscription = mProductRepository.orderCart(orderRequest)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<OrderCartResponse>() {
                    @Override
                    public void call(OrderCartResponse orderCartResponse) {
                        mViewModel.onOrderAllShopSuccess();
                    }
                }, new SafetyError() {
                    @Override
                    public void onSafetyError(BaseException error) {
                        mViewModel.onOrderAllShopError(error);
                    }
                });
        mCompositeSubscription.add(subscription);
    }

    @Override
    public void removeOneShop(Cart cart) {
        Subscription subscription = mProductRepository.removeOrderOneShop(cart.getShopId())
                .subscribe(new Subscriber<Void>() {
                    @Override
                    public void onCompleted() {
                        mViewModel.onRemoveShopSuccessful();
                    }

                    @Override
                    public void onError(Throwable e) {
                        mViewModel.onRemoveShopError(e);
                    }

                    @Override
                    public void onNext(Void aVoid) {
                        // No-Op
                    }
                });
        mCompositeSubscription.add(subscription);
    }

    @Override
    public void removeAllShop() {
        Subscription subscription =
                mProductRepository.removeAllOrder().subscribe(new Subscriber<Void>() {
                    @Override
                    public void onCompleted() {
                        mViewModel.onRemoveShopSuccessful();
                    }

                    @Override
                    public void onError(Throwable e) {
                        mViewModel.onRemoveShopError(e);
                    }

                    @Override
                    public void onNext(Void aVoid) {
                        // No-Op
                    }
                });
        mCompositeSubscription.add(subscription);
    }

    @Override
    public void upQuantity(CartItem cartItem) {
        if (cartItem == null) {
            return;
        }
        Subscription subscriptions = mProductRepository.upQuantity(cartItem.getProductId())
                .subscribe(new Subscriber<Void>() {
                    @Override
                    public void onCompleted() {
                        mViewModel.onUpQuantitySuccess();
                    }

                    @Override
                    public void onError(Throwable e) {
                        mViewModel.onUpQuantityError(e);
                    }

                    @Override
                    public void onNext(Void aVoid) {
                        // No-Op
                    }
                });
        mCompositeSubscription.add(subscriptions);
    }

    @Override
    public void downQuantity(CartItem cartItem) {
        if (cartItem == null) {
            return;
        }
        Subscription subscriptions = mProductRepository.downQuantity(cartItem.getProductId())
                .subscribe(new Subscriber<Void>() {
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
    public void deleteProduct(CartItem cartItem) {
        if (cartItem == null) {
            return;
        }
        Subscription subscriptions = mProductRepository.deleteCart(cartItem.getProductId())
                .subscribe(new Subscriber<Void>() {
                    @Override
                    public void onCompleted() {
                        mViewModel.onDeleteProductSuccess();
                    }

                    @Override
                    public void onError(Throwable e) {
                        mViewModel.onDeleteProductError(e);
                    }

                    @Override
                    public void onNext(Void aVoid) {
                        // No-Op
                    }
                });
        mCompositeSubscription.add(subscriptions);
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
    public OrderRequest getOrderRequest(List<Cart> carts) {
        OrderRequest orderRequest = new OrderRequest();
        List<Cart> cartList = new ArrayList<>();
        for (Cart cart : carts) {
            boolean isTimeOut = false;
            List<CartItem> cartItemList = new ArrayList<>();
            for (CartItem cartItem : cart.getCartItemList()) {
                if (!Utils.DateTimeUntils.isProductTimeOut(cartItem.getStartHour(),
                        cartItem.getEndHour())) {
                    cartItemList.add(cartItem);
                    isTimeOut = true;
                }
            }
            if (isTimeOut) {
                cart.setCartItemList(cartItemList);
                cartList.add(cart);
            }
        }
        orderRequest.setCartList(cartList);
        return orderRequest;
    }
}
