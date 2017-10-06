package com.framgia.forder.screen.requestshopindomain;

import com.framgia.forder.data.source.ShopRepository;
import com.framgia.forder.data.source.remote.api.error.BaseException;
import com.framgia.forder.data.source.remote.api.error.SafetyError;
import com.framgia.forder.data.source.remote.api.response.BaseResponse;
import com.framgia.forder.data.source.remote.api.response.ShopRequestResponse;
import java.util.List;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Listens to user actions from the UI ({@link RequestShopInDomainFragment}), retrieves the data and
 * updates
 * the UI as required.
 */
final class RequestShopInDomainPresenter implements RequestShopInDomainContract.Presenter {
    private static final String TAG = RequestShopInDomainPresenter.class.getName();

    private final RequestShopInDomainContract.ViewModel mViewModel;
    private final ShopRepository mShopRepository;
    private final CompositeSubscription mCompositeSubscription;

    RequestShopInDomainPresenter(RequestShopInDomainContract.ViewModel viewModel,
            ShopRepository shopRepository) {
        mViewModel = viewModel;
        mShopRepository = shopRepository;
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
    public void getListRequestShop(int domainId) {
        Subscription subscription = mShopRepository.getListShopRequest(domainId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<List<ShopRequestResponse.ShopContain>>() {
                    @Override
                    public void call(List<ShopRequestResponse.ShopContain> shops) {
                        mViewModel.onGetListRequestShopSuccess(shops);
                    }
                }, new SafetyError() {
                    @Override
                    public void onSafetyError(BaseException error) {
                        mViewModel.onGetListRequestShopError(error);
                    }
                });
        mCompositeSubscription.add(subscription);
    }

    @Override
    public void requestToAcceptRejectShopToDomain(int domainId, int shopId, String status) {
        Subscription subscription =
                mShopRepository.requestToAcceptRejectShopToDomain(domainId, shopId, status)
                        .subscribeOn(Schedulers.io()).doOnSubscribe(new Action0() {
                    @Override
                    public void call() {
                        mViewModel.showProgressBarDialog();
                    }
                }).doAfterTerminate(new Action0() {
                    @Override
                    public void call() {
                        mViewModel.hideProgressBarDialog();
                    }
                })
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Action1<BaseResponse>() {
                            @Override
                            public void call(BaseResponse response) {
                                mViewModel.onRequestShopSuccess();
                            }
                        }, new SafetyError() {
                            @Override
                            public void onSafetyError(BaseException error) {
                                mViewModel.onGetMessageError(error);
                            }
                        });
        mCompositeSubscription.add(subscription);
    }
}
