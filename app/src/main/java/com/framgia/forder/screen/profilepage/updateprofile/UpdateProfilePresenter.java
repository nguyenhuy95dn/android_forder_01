package com.framgia.forder.screen.profilepage.updateprofile;

import android.annotation.SuppressLint;
import com.framgia.forder.data.model.CollectionAvatar;
import com.framgia.forder.data.model.Image;
import com.framgia.forder.data.model.User;
import com.framgia.forder.data.source.UserRepository;
import com.framgia.forder.data.source.remote.api.error.BaseException;
import com.framgia.forder.data.source.remote.api.error.SafetyError;
import com.framgia.forder.data.source.remote.api.request.UpdateProfileRequest;
import com.framgia.forder.data.source.remote.api.response.UpdateProfileResponse;
import com.framgia.forder.utils.Utils;
import java.util.Objects;
import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Listens to user actions from the UI ({@link UpdateProfileFragment}), retrieves the data and
 * updates
 * the UI as required.
 */
final class UpdateProfilePresenter implements UpdateProfileContract.Presenter {

    private static final String TAG = UpdateProfilePresenter.class.getName();

    private final UpdateProfileContract.ViewModel mViewModel;
    private CompositeSubscription mCompositeSubscription;
    private UserRepository mUserRepository;

    UpdateProfilePresenter(UpdateProfileContract.ViewModel viewModel,
            UserRepository userRepository) {
        mViewModel = viewModel;
        mUserRepository = userRepository;
        mCompositeSubscription = new CompositeSubscription();
        getUserProfile();
    }

    @Override
    public void onStart() {
    }

    @Override
    public void onStop() {
        mCompositeSubscription.clear();
    }

    @Override
    public void updateProfile(UpdateProfileRequest updateProfileRequest, String name,
            String chatworkId, String description) {
        final User user = mUserRepository.getUser();
        user.setName(name);
        user.setChatworkId(chatworkId);
        user.setDescription(description);
        Subscription subscription = Observable.just(updateProfileRequest)
                .flatMap(new Func1<UpdateProfileRequest, Observable<UpdateProfileResponse>>() {

                    @Override
                    public Observable<UpdateProfileResponse> call(
                            UpdateProfileRequest updateProfileRequest) {
                        if (updateProfileRequest.getAvatar() != null) {
                            String imageCoverBase64 = Utils.ImageUtils.convertImagetoBase64(
                                    updateProfileRequest.getAvatar());
                            updateProfileRequest.getUser().setAvatar(imageCoverBase64);
                            user.setAvatar(new CollectionAvatar(new Image(imageCoverBase64)));
                        }
                        return mUserRepository.updateProfile(updateProfileRequest);
                    }
                })
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
                .subscribe(new Action1<UpdateProfileResponse>() {
                    @Override
                    public void call(UpdateProfileResponse updateProfileRespone) {
                        mUserRepository.saveUser(user);
                        mViewModel.onUpdateProfileSuccess();
                    }
                }, new SafetyError() {
                    @Override
                    public void onSafetyError(BaseException error) {
                        mViewModel.onUpdateProfileError(error);
                    }
                });
        mCompositeSubscription.add(subscription);
    }

    public void getUserProfile() {
        mViewModel.onGetUserProfile(mUserRepository.getUser());
    }

    @SuppressLint("NewApi")
    @Override
    public boolean validateDataInputChange(String userName, String chatwordId, String description) {
        boolean isDataChange = false;
        String oldUsername = mUserRepository.getUser().getName();
        String oldChatworkId = mUserRepository.getUser().getChatworkId();
        String oldDescription = mUserRepository.getUser().getDescription();
        if (!Objects.equals(userName, oldUsername)) {
            isDataChange = true;
        }
        if (!Objects.equals(chatwordId, oldChatworkId)) {
            isDataChange = true;
        }
        if (!Objects.equals(description, oldDescription)) {
            isDataChange = true;
        }
        return isDataChange;
    }
}
