package com.framgia.forder.data.source.remote;

import com.framgia.forder.data.source.NotificationSettingDataSource;
import com.framgia.forder.data.source.remote.api.request.NotificationSettingRequest;
import com.framgia.forder.data.source.remote.api.response.BaseResponse;
import com.framgia.forder.data.source.remote.api.response.NotificationSettingResponse;
import com.framgia.forder.data.source.remote.api.service.FOrderApi;
import rx.Observable;

/**
 * Created by nguyenhuy95dn on 9/7/2017.
 */

public class NotificationSettingRemoteDataSource extends BaseRemoteDataSource
        implements NotificationSettingDataSource.RemoteDataSource {

    public NotificationSettingRemoteDataSource(FOrderApi fOrderApi) {
        super(fOrderApi);
    }

    @Override
    public Observable<NotificationSettingResponse> getNotificationSetting() {
        return mFOrderApi.getNotificationSetting();
    }

    @Override
    public Observable<BaseResponse> updateNotificationSetting(
            NotificationSettingRequest notificationSettingRequest) {
        return mFOrderApi.updateNotificationSetting(notificationSettingRequest);
    }
}
