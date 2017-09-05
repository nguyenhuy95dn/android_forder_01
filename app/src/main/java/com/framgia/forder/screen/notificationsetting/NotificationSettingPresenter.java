package com.framgia.forder.screen.notificationsetting;

/**
 * Listens to user actions from the UI ({@link NotificationSettingFragment}), retrieves the data and
 * updates
 * the UI as required.
 */
final class NotificationSettingPresenter implements NotificationSettingContract.Presenter {
    private static final String TAG = NotificationSettingPresenter.class.getName();

    private final NotificationSettingContract.ViewModel mViewModel;

    NotificationSettingPresenter(NotificationSettingContract.ViewModel viewModel) {
        mViewModel = viewModel;
    }

    @Override
    public void onStart() {
    }

    @Override
    public void onStop() {
    }
}
