package com.example.duong.android_forder_01.data.model.source;

import java.util.List;

/**
 * Created by tri on 21/02/2017.
 */
public interface GetDataCallback<T> {
    void onLoaded(List<T> datas);
    void onNotAvailable();
}
