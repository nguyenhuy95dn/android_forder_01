package com.framgia.forder.screen.notificationsetting;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.framgia.forder.R;
import com.framgia.forder.databinding.FragmentNotificationSettingBinding;

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
        mViewModel = new NotificationSettingViewModel();

        NotificationSettingContract.Presenter presenter =
                new NotificationSettingPresenter(mViewModel);
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
