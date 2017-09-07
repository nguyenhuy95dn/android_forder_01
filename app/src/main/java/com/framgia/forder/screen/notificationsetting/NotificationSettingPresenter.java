package com.framgia.forder.screen.notificationsetting;

import com.framgia.forder.data.source.NotificationSettingRepository;
import com.framgia.forder.data.source.remote.api.error.BaseException;
import com.framgia.forder.data.source.remote.api.error.SafetyError;
import com.framgia.forder.data.source.remote.api.request.NotificationSettingRequest;
import com.framgia.forder.data.source.remote.api.response.BaseResponse;
import com.framgia.forder.data.source.remote.api.response.NotificationSettingResponse;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Listens to user actions from the UI ({@link NotificationSettingFragment}), retrieves the data and
 * updates
 * the UI as required.
 */
final class NotificationSettingPresenter implements NotificationSettingContract.Presenter {
    private static final String TAG = NotificationSettingPresenter.class.getName();

    private final NotificationSettingContract.ViewModel mViewModel;
    private final CompositeSubscription mCompositeSubscription;
    private final NotificationSettingRepository mNotificationSettingRepository;

    NotificationSettingPresenter(NotificationSettingContract.ViewModel viewModel,
            NotificationSettingRepository notificationSettingRepository) {
        mViewModel = viewModel;
        mNotificationSettingRepository = notificationSettingRepository;
        mCompositeSubscription = new CompositeSubscription();
    }

    @Override
    public void onStart() {
        getNotificationSetting();
    }

    @Override
    public void onStop() {
        mCompositeSubscription.clear();
    }

    private void getNotificationSetting() {
        Subscription subscription = mNotificationSettingRepository.getNotificationSetting()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(

                        new Action1<NotificationSettingResponse>() {
                            @Override
                            public void call(
                                    NotificationSettingResponse notificationSettingResponse) {
                                if (notificationSettingResponse == null) {
                                    return;
                                }
                                mViewModel.onGetNotificationSettingSuccess(
                                        notificationSettingResponse.getUserSetting());
                            }
                        }, new SafetyError() {
                            @Override
                            public void onSafetyError(BaseException error) {
                                mViewModel.onError(error);
                            }
                        });
        mCompositeSubscription.add(subscription);
    }

    @Override
    public void updateNotificationSetting(NotificationSettingRequest notificationSettingRequest) {
        Subscription subscription =
                mNotificationSettingRepository.updateNotificationSetting(notificationSettingRequest)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Action1<BaseResponse>() {
                            @Override
                            public void call(BaseResponse response) {
                                mViewModel.onUpdateNotificationSettingSuccess();
                            }
                        }, new SafetyError() {
                            @Override
                            public void onSafetyError(BaseException error) {
                                mViewModel.onError(error);
                            }
                        });
        mCompositeSubscription.add(subscription);
    }
}
