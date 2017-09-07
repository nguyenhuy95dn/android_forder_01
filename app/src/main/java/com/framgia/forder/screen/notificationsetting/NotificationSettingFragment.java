package com.framgia.forder.screen.notificationsetting;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.framgia.forder.R;
import com.framgia.forder.data.source.NotificationSettingRepository;
import com.framgia.forder.data.source.remote.NotificationSettingRemoteDataSource;
import com.framgia.forder.data.source.remote.api.service.FOrderServiceClient;
import com.framgia.forder.databinding.FragmentNotificationSettingBinding;
import com.framgia.forder.utils.navigator.Navigator;

/**
 * Notificationsetting Screen.
 */
public class NotificationSettingFragment extends Fragment {

    private NotificationSettingContract.ViewModel mViewModel;

    public static NotificationSettingFragment newInstance() {
        return new NotificationSettingFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Navigator navigator = new Navigator(getParentFragment());
        mViewModel = new NotificationSettingViewModel(navigator);

        NotificationSettingRepository notificationSettingRepository =
                new NotificationSettingRepository(
                        new NotificationSettingRemoteDataSource(FOrderServiceClient.getInstance()));
        NotificationSettingContract.Presenter presenter =
                new NotificationSettingPresenter(mViewModel, notificationSettingRepository);
        mViewModel.setPresenter(presenter);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {

        FragmentNotificationSettingBinding binding =
                DataBindingUtil.inflate(inflater, R.layout.fragment_notification_setting, container,
                        false);
        binding.setViewModel((NotificationSettingViewModel) mViewModel);
        return binding.getRoot();
    }

    @Override
    public void onStart() {
        super.onStart();
        mViewModel.onStart();
    }

    @Override
    public void onStop() {
        mViewModel.onStop();
        super.onStop();
    }
}
