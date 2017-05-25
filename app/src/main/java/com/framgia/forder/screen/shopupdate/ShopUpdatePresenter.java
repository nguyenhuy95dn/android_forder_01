package com.framgia.forder.screen.shopupdate;

import android.text.TextUtils;
import com.framgia.forder.data.source.ShopRepository;
import com.framgia.forder.data.source.remote.api.error.BaseException;
import com.framgia.forder.data.source.remote.api.request.UpdateShopRequest;
import com.framgia.forder.data.source.remote.api.response.ShopResponse;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Listens to user actions from the UI ({@link ShopUpdateFragment}), retrieves the data and updates
 * the UI as required.
 */
final class ShopUpdatePresenter implements ShopUpdateContract.Presenter {
    private static final String TAG = ShopUpdatePresenter.class.getName();

    private final ShopUpdateContract.ViewModel mViewModel;
    private final CompositeSubscription mCompositeSubscription;
    private final ShopRepository mShopRepository;

    ShopUpdatePresenter(ShopUpdateContract.ViewModel viewModel, ShopRepository shopRepository) {
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
    public boolean validateDataInput(String name, String description) {
        boolean isValid = true;
        if (TextUtils.isEmpty(name)) {
            isValid = false;
            mViewModel.onInputNameError();
        }
        if (TextUtils.isEmpty(description)) {
            isValid = false;
            mViewModel.onInputDescriptionError();
        }
        return isValid;
    }

    @Override
    public void onUpdateShop(UpdateShopRequest updateShopRequest) {
        Subscription subscription = mShopRepository.updateShop(updateShopRequest)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<ShopResponse>() {
                    @Override
                    public void call(ShopResponse shopResponse) {
                        mViewModel.onUpdateShopSuccess();
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        mViewModel.onUpdateShopError((BaseException) throwable);
                    }
                });
        mCompositeSubscription.add(subscription);
    }
}
