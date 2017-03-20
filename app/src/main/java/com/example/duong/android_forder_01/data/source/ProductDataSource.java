package com.example.duong.android_forder_01.data.source;

/**
 * Created by tri on 24/02/2017.
 */
public interface ProductDataSource<T> {
    void getDatas(int domainId, GetDataCallback<T> getDataCallback);
    void getProductByCategoryId(int domainId, int categoryId, GetDataCallback<T> getDataCallback);
}
