package com.framgia.forder.data.source;

import com.framgia.forder.data.source.remote.api.request.NotificationSettingRequest;
import com.framgia.forder.data.source.remote.api.response.BaseResponse;
import com.framgia.forder.data.source.remote.api.response.NotificationSettingResponse;
import rx.Observable;

/**
 * Created by nguyenhuy95dn on 9/7/2017.
 */

public class NotificationSettingDataSource {
    public interface RemoteDataSource {

        Observable<NotificationSettingResponse> getNotificationSetting();

        Observable<BaseResponse> updateNotificationSetting(
                NotificationSettingRequest notificationSettingRequest);
    }
}
