package com.example.duong.android_forder_01.data.source;

import com.example.duong.android_forder_01.data.model.User;

/**
 * Created by tri on 24/02/2017.
 */
public interface ProductDataSource<T> {
    void getDatas(int domainId, User user, GetDataCallback<T> getDataCallback);
    void getProductByCategoryId(int domainId, int categoryId, User user, GetDataCallback<T>
        getDataCallback);
}
