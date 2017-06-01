package com.framgia.forder.screen.shopupdate;

import android.text.TextUtils;
import com.framgia.forder.data.model.CollectionAvatar;
import com.framgia.forder.data.model.CollectionImage;
import com.framgia.forder.data.model.Image;
import com.framgia.forder.data.source.ShopRepository;
import com.framgia.forder.data.source.remote.api.error.BaseException;
import com.framgia.forder.data.source.remote.api.error.SafetyError;
import com.framgia.forder.data.source.remote.api.request.UpdateShopRequest;
import com.framgia.forder.data.source.remote.api.response.RegisterShopResponse;
import com.framgia.forder.utils.Utils;
import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
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
    public void onUpdateShop(final UpdateShopRequest updateShopRequest) {
        Subscription subscription = Observable.just(updateShopRequest)
                .flatMap(new Func1<UpdateShopRequest, Observable<RegisterShopResponse>>() {

                    @Override
                    public Observable<RegisterShopResponse> call(
                            UpdateShopRequest updateShopRequest) {
                        if (updateShopRequest.getImageAvatar() != null) {
                            String imageBase64 = Utils.ImageUtils.convertImagetoBase64(
                                    updateShopRequest.getImageAvatar());
                            updateShopRequest.getShop()
                                    .setCollectionAvatar(
                                            new CollectionAvatar(new Image(imageBase64)));
                        }
                        if (updateShopRequest.getImageCover() != null) {
                            String imageBase64 = Utils.ImageUtils.convertImagetoBase64(
                                    updateShopRequest.getImageCover());
                            updateShopRequest.getShop()
                                    .setCoverImage(new CollectionImage(new Image(imageBase64)));
                        }
                        return mShopRepository.updateShop(updateShopRequest.getShopId(),
                                updateShopRequest);
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<RegisterShopResponse>() {
                    @Override
                    public void call(RegisterShopResponse response) {
                        mViewModel.onUpdateShopSuccess();
                    }
                }, new SafetyError() {
                    @Override
                    public void onSafetyError(BaseException error) {
                        mViewModel.onUpdateShopError(error);
                    }
                });
        mCompositeSubscription.add(subscription);
    }
}
