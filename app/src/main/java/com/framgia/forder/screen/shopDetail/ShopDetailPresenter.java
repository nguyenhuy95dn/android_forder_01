package com.framgia.forder.screen.shopDetail;

import com.framgia.forder.data.model.Product;
import com.framgia.forder.data.source.ProductRepository;
import com.framgia.forder.data.source.ShopRepository;
import com.framgia.forder.data.source.remote.api.error.BaseException;
import com.framgia.forder.data.source.remote.api.error.SafetyError;
import com.framgia.forder.data.source.remote.api.response.BaseResponse;
import com.framgia.forder.data.source.remote.api.response.CheckFollowShopResponse;
import java.util.List;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Listens to user actions from the UI ({@link ShopDetailFragment}), retrieves the data and updates
 * the UI as required.
 */
final class ShopDetailPresenter implements ShopDetailContract.Presenter {

    private static final String TAG = ShopDetailPresenter.class.getName();

    private final ShopDetailContract.ViewModel mViewModel;
    private final CompositeSubscription mCompositeSubscription;
    private final ProductRepository mProductRepository;
    private final ShopRepository mShopRepository;

    ShopDetailPresenter(ShopDetailContract.ViewModel viewModel, ProductRepository productRepository,
            ShopRepository shopRepository) {
        mViewModel = viewModel;
        mProductRepository = productRepository;
        mShopRepository = shopRepository;
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
    public void getListAllProductShop(int shopId) {
        Subscription subscription = mProductRepository.getListProductInShopInformation(shopId)
                .subscribeOn(Schedulers.io())
                .doOnSubscribe(new Action0() {
                    @Override
                    public void call() {
                        mViewModel.onShowProgressBar();
                    }
                })
                .doAfterTerminate(new Action0() {
                    @Override
                    public void call() {
                        mViewModel.onHideProgressBar();
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<List<Product>>() {
                    @Override
                    public void call(List<Product> products) {
                        mViewModel.onGetListAllProductShopSuccess(products);
                    }
                }, new SafetyError() {
                    @Override
                    public void onSafetyError(BaseException error) {
                        mViewModel.onGetListAllProductShopError(error);
                    }
                });
        mCompositeSubscription.add(subscription);
    }

    @Override
    public void requestFollowShop(int shopId, String type) {
        Subscription subscription = mShopRepository.requestFollowShop(shopId, type)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<BaseResponse>() {
                    @Override
                    public void call(BaseResponse response) {
                        mViewModel.onFollowShopSuccess();
                    }
                }, new SafetyError() {
                    @Override
                    public void onSafetyError(BaseException error) {
                        mViewModel.onGetListAllProductShopError(error);
                    }
                });
        mCompositeSubscription.add(subscription);
    }

    @Override
    public void requestRateShop(int shopId, float ratePoint) {
        Subscription subscription = mShopRepository.requestRateShop(shopId, ratePoint)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<BaseResponse>() {
                    @Override
                    public void call(BaseResponse response) {
                        mViewModel.onRateShopSuccess();
                    }
                }, new SafetyError() {
                    @Override
                    public void onSafetyError(BaseException error) {
                        mViewModel.onGetListAllProductShopError(error);
                    }
                });
        mCompositeSubscription.add(subscription);
    }

    @Override
    public void checkFollowShop(int shopId) {
        Subscription subscription = mShopRepository.checkFollowShop(shopId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<CheckFollowShopResponse>() {
                    @Override
                    public void call(CheckFollowShopResponse checkFollowShopResponse) {
                        mViewModel.onCheckFollowSuccess(checkFollowShopResponse.isFollow());
                    }
                }, new SafetyError() {
                    @Override
                    public void onSafetyError(BaseException error) {
                        mViewModel.onGetListAllProductShopError(error);
                    }
                });
        mCompositeSubscription.add(subscription);
    }
}
