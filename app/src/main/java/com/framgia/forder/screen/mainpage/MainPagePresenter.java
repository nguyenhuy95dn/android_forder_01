package com.framgia.forder.screen.mainpage;

import com.framgia.forder.data.model.Cart;
import com.framgia.forder.data.model.Category;
import com.framgia.forder.data.model.Domain;
import com.framgia.forder.data.model.Product;
import com.framgia.forder.data.model.Shop;
import com.framgia.forder.data.source.CategoryRepository;
import com.framgia.forder.data.source.DomainRepository;
import com.framgia.forder.data.source.ProductRepository;
import com.framgia.forder.data.source.ShopRepository;
import com.framgia.forder.data.source.UserRepository;
import com.framgia.forder.data.source.remote.api.error.BaseException;
import com.framgia.forder.data.source.remote.api.error.SafetyError;
import com.framgia.forder.data.source.remote.api.request.OrderRequest;
import com.framgia.forder.data.source.remote.api.response.OrderCartResponse;
import java.util.List;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

final class MainPagePresenter implements MainPageContract.Presenter {
    private static final String TAG = MainPagePresenter.class.getName();
    private final MainPageContract.ViewModel mViewModel;
    private final CompositeSubscription mCompositeSubscription;
    private final ProductRepository mProductRepository;
    private final ShopRepository mShopRepository;
    private final DomainRepository mDomainRepository;
    private final CategoryRepository mCategoryRepository;
    private final UserRepository mUserRepository;
    private static final int START_SUB_LIST = 0;
    private static final int END_SUB_LIST = 6;

    MainPagePresenter(MainPageContract.ViewModel viewModel, ProductRepository productRepository,
            DomainRepository domainRepository, ShopRepository shopRepository,
            CategoryRepository categoryRepository, UserRepository userRepository) {
        mViewModel = viewModel;
        mProductRepository = productRepository;
        mCompositeSubscription = new CompositeSubscription();
        mDomainRepository = domainRepository;
        mShopRepository = shopRepository;
        mCategoryRepository = categoryRepository;
        mUserRepository = userRepository;
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
                        // No Option
                    }
                });
        mCompositeSubscription.add(subscription);
    }

    @Override
    public void getListProduct() {
        Subscription subscription = mProductRepository.getListProduct()
                .subscribeOn(Schedulers.io())
                .doOnSubscribe(new Action0() {
                    @Override
                    public void call() {
                        mViewModel.onShowProgressbarProduct();
                    }
                })
                .doAfterTerminate(new Action0() {
                    @Override
                    public void call() {
                        mViewModel.onHideProgressbarProduct();
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<List<Product>>() {
                    @Override
                    public void call(List<Product> products) {
                        mViewModel.onGetListProductSuccess(
                                products.subList(START_SUB_LIST, END_SUB_LIST));
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
    public void getListShop() {
        Domain domain = mDomainRepository.getCurrentDomain();
        if (domain == null) {
            return;
        }
        Subscription subscription = mShopRepository.getListShop(domain.getId())
                .subscribeOn(Schedulers.io())
                .doOnSubscribe(new Action0() {
                    @Override
                    public void call() {
                        mViewModel.onShowProgressbarShop();
                    }
                })
                .doAfterTerminate(new Action0() {
                    @Override
                    public void call() {
                        mViewModel.onHideProgressbarShop();
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<List<Shop>>() {
                    @Override
                    public void call(List<Shop> shops) {
                        mViewModel.onGetListShopSuccess(
                                shops.subList(START_SUB_LIST, END_SUB_LIST));
                    }
                }, new SafetyError() {
                    @Override
                    public void onSafetyError(BaseException error) {
                        mViewModel.onGetListShopError(error);
                    }
                });
        mCompositeSubscription.add(subscription);
    }

    @Override
    public void getListCategory() {
        Domain domain = mDomainRepository.getCurrentDomain();
        if (domain == null) {
            return;
        }
        Subscription subscription = mCategoryRepository.getListCategory(domain.getId())
                .subscribeOn(Schedulers.io())
                .doOnSubscribe(new Action0() {
                    @Override
                    public void call() {
                        mViewModel.onShowProgressbarCategory();
                    }
                })
                .doAfterTerminate(new Action0() {
                    @Override
                    public void call() {
                        mViewModel.onHideProgressbarCategory();
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<List<Category>>() {
                    @Override
                    public void call(List<Category> categories) {
                        mViewModel.onGetListCategorySuccess(categories);
                    }
                }, new SafetyError() {
                    @Override
                    public void onSafetyError(BaseException error) {
                        mViewModel.onGetListCategoryError(error);
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
                .doOnSubscribe(new Action0() {
                    @Override
                    public void call() {
                        mViewModel.onShowProgressDialog();
                    }
                })
                .doAfterTerminate(new Action0() {
                    @Override
                    public void call() {
                        mViewModel.onHideProgressDialog();
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<OrderCartResponse>() {
                    @Override
                    public void call(OrderCartResponse orderCartResponse) {
                        mViewModel.onOrderProductSuccess();
                    }
                }, new SafetyError() {
                    @Override
                    public void onSafetyError(BaseException error) {
                        mViewModel.onOrderProductError(error);
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
