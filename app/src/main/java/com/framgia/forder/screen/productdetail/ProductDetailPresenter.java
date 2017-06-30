package com.framgia.forder.screen.productdetail;

import com.framgia.forder.data.model.Cart;
import com.framgia.forder.data.model.Comment;
import com.framgia.forder.data.model.Product;
import com.framgia.forder.data.source.DomainRepository;
import com.framgia.forder.data.source.ProductRepository;
import com.framgia.forder.data.source.UserRepository;
import com.framgia.forder.data.source.remote.api.error.BaseException;
import com.framgia.forder.data.source.remote.api.error.SafetyError;
import com.framgia.forder.data.source.remote.api.request.CommentRequest;
import com.framgia.forder.data.source.remote.api.request.OrderRequest;
import com.framgia.forder.data.source.remote.api.response.BaseResponse;
import com.framgia.forder.data.source.remote.api.response.OrderCartResponse;
import java.util.List;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Listens to user actions from the UI ({@link ProductDetailFragment}), retrieves the data and
 * updates
 * the UI as required.
 */
final class ProductDetailPresenter implements ProductDetailContract.Presenter {

    private static final String TAG = ProductDetailPresenter.class.getName();

    private final ProductDetailContract.ViewModel mViewModel;
    private final CompositeSubscription mCompositeSubscription;
    private final ProductRepository mProductRepository;
    private final UserRepository mUserRepository;
    private final DomainRepository mDomainRepository;

    ProductDetailPresenter(ProductDetailContract.ViewModel viewModel,
            ProductRepository productRepository, DomainRepository domainRepository,
            UserRepository userRepository) {
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
    }

    @Override
    public void addToCart(Product product) {
        Subscription subscription =
                mProductRepository.addToCart(product).subscribe(new Action1<Void>() {
                    @Override
                    public void call(Void aVoid) {
                        mViewModel.onAddToCartSuccess();
                    }
                }, new SafetyError() {
                    @Override
                    public void onSafetyError(BaseException error) {
                        mViewModel.onShowMessageError(error);
                    }
                });
        mCompositeSubscription.add(subscription);
    }

    @Override
    public void getListProductInShop(int shopId) {
        Subscription subscription = mProductRepository.getListProductInShop(shopId)
                .subscribeOn(Schedulers.io())
                .doOnSubscribe(new Action0() {
                    @Override
                    public void call() {
                        mViewModel.onShowProgressBarListProduct();
                    }
                })
                .doAfterTerminate(new Action0() {
                    @Override
                    public void call() {
                        mViewModel.onHideProgressBarListProduct();
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<List<Product>>() {
                    @Override
                    public void call(List<Product> products) {
                        mViewModel.onGetListProductShopSuccess(products);
                    }
                }, new SafetyError() {
                    @Override
                    public void onSafetyError(BaseException error) {
                        mViewModel.onShowMessageError(error);
                    }
                });
        mCompositeSubscription.add(subscription);
    }

    @Override
    public void getListCommentInProduct(int productId) {

        Subscription subscription = mProductRepository.getListCommentInProduct(productId)
                .subscribeOn(Schedulers.io())
                .doOnSubscribe(new Action0() {
                    @Override
                    public void call() {
                        mViewModel.onShowProgressBarListComment();
                    }
                })
                .doAfterTerminate(new Action0() {
                    @Override
                    public void call() {
                        mViewModel.onHideProgressBarListComment();
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<List<Comment>>() {
                    @Override
                    public void call(List<Comment> comments) {
                        mViewModel.onGetListCommentInProductSusscess(comments);
                    }
                }, new SafetyError() {
                    @Override
                    public void onSafetyError(BaseException error) {
                        mViewModel.onShowMessageError(error);
                    }
                });
        mCompositeSubscription.add(subscription);
    }

    @Override
    public void sendComment(CommentRequest request) {
        Subscription subscription = mProductRepository.sendComment(request)
                .subscribeOn(Schedulers.io())
                .doOnSubscribe(new Action0() {
                    @Override
                    public void call() {
                        mViewModel.onShowProgressBarComment();
                    }
                })
                .doAfterTerminate(new Action0() {
                    @Override
                    public void call() {
                        mViewModel.onHideProgressBarComment();
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<BaseResponse>() {
                    @Override
                    public void call(BaseResponse sendComment) {
                        mViewModel.onCommentSuccess();
                    }
                }, new SafetyError() {
                    @Override
                    public void onSafetyError(BaseException error) {
                        mViewModel.onShowMessageError(error);
                    }
                });
        mCompositeSubscription.add(subscription);
    }

    @Override
    public void orderNow(OrderRequest orderRequest) {
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
                        mViewModel.onOrderNowSuccess();
                    }
                }, new SafetyError() {
                    @Override
                    public void onSafetyError(BaseException error) {
                        mViewModel.onShowMessageError(error);
                    }
                });
        mCompositeSubscription.add(subscription);
    }
}
