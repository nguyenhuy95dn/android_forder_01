package com.framgia.forder.screen.shopinfo;

import com.framgia.forder.data.model.User;
import com.framgia.forder.data.source.ShopRepository;
import com.framgia.forder.data.source.remote.api.error.BaseException;
import com.framgia.forder.data.source.remote.api.error.SafetyError;
import java.util.List;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
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

    ShopinfoPresenter(ShopinfoContract.ViewModel viewModel, ShopRepository shopRepository) {
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
    public void getListManagerOfShop(int shopId) {
        Subscription subscription = mShopRepository.getListManagerOfShop(shopId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<List<User>>() {
                    @Override
                    public void call(List<User> users) {
                        mViewModel.onGetListManagerOfShopSuccess(users);
                    }
                }, new SafetyError() {
                    @Override
                    public void onSafetyError(BaseException error) {
                        mViewModel.onGetListManagerOfShopError(error);
                    }
                });
        mCompositeSubscription.add(subscription);
    }
}
