package com.framgia.forder.screen.shopindomain;

import com.framgia.forder.data.model.ShopInDomain;
import com.framgia.forder.data.model.User;
import com.framgia.forder.data.source.ShopRepository;
import com.framgia.forder.data.source.UserRepository;
import com.framgia.forder.data.source.remote.api.error.BaseException;
import com.framgia.forder.data.source.remote.api.error.SafetyError;
import com.framgia.forder.data.source.remote.api.response.DeleteShopInDomainResponse;
import java.util.List;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Listens to user actions from the UI ({@link ShopInDomainFragment}), retrieves the data and
 * updates
 * the UI as required.
 */
final class ShopInDomainPresenter implements ShopInDomainContract.Presenter {
    private static final String TAG = ShopInDomainPresenter.class.getName();

    private final ShopInDomainContract.ViewModel mViewModel;
    private final ShopRepository mShopRepository;
    private final CompositeSubscription mCompositeSubscription;
    private final UserRepository mUserRepository;

    ShopInDomainPresenter(ShopInDomainContract.ViewModel viewModel, ShopRepository shopRepository,
            UserRepository userRepository) {
        mViewModel = viewModel;
        mShopRepository = shopRepository;
        mUserRepository = userRepository;
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
    public void getListShopInDomain(int domainId) {
        Subscription subscription = mShopRepository.getListShopInDomain(domainId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<List<ShopInDomain>>() {
                    @Override
                    public void call(List<ShopInDomain> shops) {
                        mViewModel.onGetListShopInDomainSuccess(shops);
                    }
                }, new SafetyError() {
                    @Override
                    public void onSafetyError(BaseException error) {
                        mViewModel.onGetListShopInDomainError(error);
                    }
                });
        mCompositeSubscription.add(subscription);
    }

    @Override
    public void deleteShop(int domainId, int shopId) {
        Subscription subscription = mShopRepository.requestDeleteShopInDomain(domainId, shopId)
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
                .subscribe(new Action1<DeleteShopInDomainResponse>() {
                    @Override
                    public void call(DeleteShopInDomainResponse response) {
                        mViewModel.ondeleteShopInDomainSuccess();
                    }
                }, new SafetyError() {
                    @Override
                    public void onSafetyError(BaseException error) {
                        mViewModel.ondeleteShopInDomainError(error);
                    }
                });
        mCompositeSubscription.add(subscription);
    }

    @Override
    public boolean checkOwner(int ownerId) {
        User user = mUserRepository.getUser();
        return user.isOwner(ownerId);
    }
}
