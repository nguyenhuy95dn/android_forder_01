package com.example.duong.android_forder_01.data.model.source;

import com.example.duong.android_forder_01.data.model.Product;

/**
 * Created by tri on 24/02/2017.
 */
public interface DataSource<T> {
    void getDatas(int idDomain, GetDataCallback<T> getDataCallback);
}
