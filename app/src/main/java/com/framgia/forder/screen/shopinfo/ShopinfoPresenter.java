package com.framgia.forder.screen.shopinfo;

import android.util.Log;
import com.framgia.forder.data.source.DomainRepository;
import com.framgia.forder.data.source.ShopRepository;
import com.framgia.forder.data.source.remote.api.error.BaseException;
import com.framgia.forder.data.source.remote.api.error.SafetyError;
import com.framgia.forder.data.source.remote.api.request.ApplyShopToDomainRequest;
import com.framgia.forder.data.source.remote.api.response.BaseResponse;
import com.framgia.forder.data.source.remote.api.response.DomainToRequestShopResponse;
import com.framgia.forder.data.source.remote.api.response.ManagerResponse;
import java.util.List;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Listens to user actions from the UI ({@link ShopinfoFragment}), retrieves the data and updates
 * the UI as required.
 */
final class ShopinfoPresenter implements ShopinfoContract.Presenter {
    private static final String TAG = ShopinfoPresenter.class.getName();

    private final ShopinfoContract.ViewModel mViewModel;
    private final CompositeSubscription mCompositeSubscription;
    private final ShopRepository mShopRepository;
    private final DomainRepository mDomainRepository;

    ShopinfoPresenter(ShopinfoContract.ViewModel viewModel, ShopRepository shopRepository,
            DomainRepository domainRepository) {
        mViewModel = viewModel;
        mShopRepository = shopRepository;
        mDomainRepository = domainRepository;
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
    public void getListManagerOfShop(int shopId) {
        Subscription subscription = mShopRepository.getListManagerOfShop(shopId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<List<ManagerResponse.ManagerDetail>>() {
                    @Override
                    public void call(List<ManagerResponse.ManagerDetail> managerDetails) {
                        mViewModel.onGetListManagerOfShopSuccess(managerDetails);
                    }
                }, new SafetyError() {
                    @Override
                    public void onSafetyError(BaseException error) {
                        mViewModel.onGetListManagerOfShopError(error);
                    }
                });
        mCompositeSubscription.add(subscription);
    }

    @Override
    public void onApplyToDomain(ApplyShopToDomainRequest applyShopToDomainRequest) {
        Subscription subscription =
                mShopRepository.requestApplyShopToDomain(applyShopToDomainRequest)
                        .subscribeOn(Schedulers.io())
                        .doOnSubscribe(new Action0() {
                            @Override
                            public void call() {
                                mViewModel.onShowProgressBarDialog();
                            }
                        })
                        .doAfterTerminate(new Action0() {
                            @Override
                            public void call() {
                                mViewModel.onHideProgressBarDialog();
                            }
                        })
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Action1<BaseResponse>() {
                            @Override
                            public void call(BaseResponse response) {
                                mViewModel.onApplyToDomainSuccess();
                            }
                        }, new SafetyError() {
                            @Override
                            public void onSafetyError(BaseException error) {
                                mViewModel.onApplyOrLeaveToDomainError(error);
                                Log.e(TAG, "onSafetyError: ", error);
                            }
                        });
        mCompositeSubscription.add(subscription);
    }

    @Override
    public void onLeaveToDomain(int domainId, int shopId, boolean leaveDomain) {
        Subscription subscription =
                mShopRepository.requestLeaveShopFromDomain(domainId, shopId, leaveDomain)
                        .subscribeOn(Schedulers.io())
                        .doOnSubscribe(new Action0() {
                            @Override
                            public void call() {
                                mViewModel.onShowProgressBarDialog();
                            }
                        })
                        .doAfterTerminate(new Action0() {
                            @Override
                            public void call() {
                                mViewModel.onHideProgressBarDialog();
                            }
                        })
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Action1<BaseResponse>() {
                            @Override
                            public void call(BaseResponse response) {
                                mViewModel.onLeaveToDomainSuccess();
                            }
                        }, new SafetyError() {
                            @Override
                            public void onSafetyError(BaseException error) {
                                mViewModel.onApplyOrLeaveToDomainError(error);
                            }
                        });
        mCompositeSubscription.add(subscription);
    }

    @Override
    public void getListDomainToRequestShop(int shopId) {
        Subscription subscription = mDomainRepository.getListDomainToRequestShop(shopId)
                .subscribeOn(Schedulers.io())
                .doOnSubscribe(new Action0() {
                    @Override
                    public void call() {
                        mViewModel.onShowProgressBarListDomain();
                    }
                })
                .doAfterTerminate(new Action0() {
                    @Override
                    public void call() {
                        mViewModel.onHideProgressBarListDomain();
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<List<DomainToRequestShopResponse.DomainToRequest>>() {
                    @Override
                    public void call(List<DomainToRequestShopResponse.DomainToRequest> domains) {
                        mViewModel.onGetListDomainSuccess(domains);
                    }
                }, new SafetyError() {
                    @Override
                    public void onSafetyError(BaseException error) {
                        mViewModel.onApplyOrLeaveToDomainError(error);
                    }
                });
        mCompositeSubscription.add(subscription);
    }
}
