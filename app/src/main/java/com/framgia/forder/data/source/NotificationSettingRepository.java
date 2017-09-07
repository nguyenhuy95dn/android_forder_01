package com.framgia.forder.data.source;

import com.framgia.forder.data.source.remote.api.request.NotificationSettingRequest;
import com.framgia.forder.data.source.remote.api.response.BaseResponse;
import com.framgia.forder.data.source.remote.api.response.NotificationSettingResponse;
import rx.Observable;

/**
 * Created by nguyenhuy95dn on 9/7/2017.
 */

public class NotificationSettingRepository {

    private NotificationSettingDataSource.RemoteDataSource mRemoteDataSource;

    public NotificationSettingRepository(
            NotificationSettingDataSource.RemoteDataSource remoteDataSource) {
        mRemoteDataSource = remoteDataSource;
    }

    public Observable<NotificationSettingResponse> getNotificationSetting() {
        return mRemoteDataSource.getNotificationSetting();
    }

    public Observable<BaseResponse> updateNotificationSetting(
            NotificationSettingRequest notificationSettingRequest) {
        return mRemoteDataSource.updateNotificationSetting(notificationSettingRequest);
    }
}
