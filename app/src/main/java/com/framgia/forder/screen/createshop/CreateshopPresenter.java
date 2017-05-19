package com.framgia.forder.screen.createshop;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.text.TextUtils;
import android.util.Base64;
import com.framgia.forder.data.model.CollectionAvatar;
import com.framgia.forder.data.model.CollectionImage;
import com.framgia.forder.data.model.Image;
import com.framgia.forder.data.model.User;
import com.framgia.forder.data.source.ShopRepository;
import com.framgia.forder.data.source.UserRepository;
import com.framgia.forder.data.source.remote.api.error.BaseException;
import com.framgia.forder.data.source.remote.api.error.SafetyError;
import com.framgia.forder.data.source.remote.api.request.RegisterShopRequest;
import com.framgia.forder.data.source.remote.api.response.RegisterShopResponse;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Listens to user actions from the UI ({@link CreateshopFragment}), retrieves the data and updates
 * the UI as required.
 */
final class CreateshopPresenter implements CreateshopContract.Presenter {
    private static final String TAG = CreateshopPresenter.class.getName();
    private static final int NUMBER_COMPRESS = 100;

    private final CreateshopContract.ViewModel mViewModel;
    private final CompositeSubscription mCompositeSubscription;
    private final ShopRepository mShopRepository;
    private final UserRepository mUserRepository;

    CreateshopPresenter(CreateshopContract.ViewModel viewModel, UserRepository userRepository,
            ShopRepository shopRepository) {
        mViewModel = viewModel;
        mUserRepository = userRepository;
        mShopRepository = shopRepository;
        mCompositeSubscription = new CompositeSubscription();
    }

    @Override
    public void onStart() {
    }

    @Override
    public void onStop() {
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
    public void onRequestRegisterShop(RegisterShopRequest registerShopRequest) {
        User user = mUserRepository.getUser();
        registerShopRequest.setUserEmail(user.getEmail());
        registerShopRequest.setUserToken(user.getToken());
        Subscription subscription = Observable.just(registerShopRequest)
                .flatMap(new Func1<RegisterShopRequest, Observable<RegisterShopResponse>>() {

                    @Override
                    public Observable<RegisterShopResponse> call(
                            RegisterShopRequest registerShopRequest) {
                        String imageCoverBase64 =
                                convertImagetoBase64(registerShopRequest.getImageCover());
                        String imageAvatarBase64 =
                                convertImagetoBase64(registerShopRequest.getImageAvatar());
                        registerShopRequest.getShop()
                                .setCoverImage(new CollectionImage(new Image(imageCoverBase64)));
                        registerShopRequest.getShop()
                                .setCollectionAvatar(
                                        new CollectionAvatar(new Image(imageAvatarBase64)));

                        return mShopRepository.requestRegisterShop(registerShopRequest);
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<RegisterShopResponse>() {
                    @Override
                    public void call(RegisterShopResponse response) {
                        mViewModel.onRequestRegisterShopSuccess();
                    }
                }, new SafetyError() {
                    @Override
                    public void onSafetyError(BaseException error) {
                        mViewModel.onRequestRegisterShopError(error);
                    }
                });
        mCompositeSubscription.add(subscription);
    }

    public String convertImagetoBase64(InputStream imageStream) {
        final Bitmap image = BitmapFactory.decodeStream(imageStream);
        String encodedImage = encodeImage(image);
        return encodedImage;
    }

    private String encodeImage(Bitmap bm) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.JPEG, NUMBER_COMPRESS, baos);
        byte[] b = baos.toByteArray();
        return Base64.encodeToString(b, Base64.DEFAULT);
    }
}
