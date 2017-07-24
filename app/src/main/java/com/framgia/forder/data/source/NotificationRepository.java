package com.framgia.forder.data.source;

import com.framgia.forder.data.model.Notification;
import com.framgia.forder.data.source.remote.NotificationRemoteDataSource;
import com.framgia.forder.data.source.remote.api.response.BaseResponse;
import java.util.List;
import rx.Observable;

/**
 * Created by ASUS on 24-04-2017.
 */

public class NotificationRepository {
    private NotificationDataSource.RemoteDataSource mRemoteDataSource;

    public NotificationRepository(NotificationRemoteDataSource remoteDataSource) {
        mRemoteDataSource = remoteDataSource;
    }

    public Observable<List<Notification>> getListNotification() {
        return mRemoteDataSource.getListNotification();
    }

    public Observable<BaseResponse> readAllNotification(boolean readAll) {
        return mRemoteDataSource.readAllNotification(readAll);
    }
}
