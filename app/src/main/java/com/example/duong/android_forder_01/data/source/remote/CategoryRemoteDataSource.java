package com.example.duong.android_forder_01.data.source.remote;

import com.example.duong.android_forder_01.data.model.Category;
import com.example.duong.android_forder_01.data.source.ProductDataSource;
import com.example.duong.android_forder_01.data.source.GetDataCallback;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tri on 27/02/2017.
 */
public class CategoryRemoteDataSource implements ProductDataSource<Category> {
    private static CategoryRemoteDataSource sCategoryRemoteDataSource;

    private CategoryRemoteDataSource() {
    }

    public static CategoryRemoteDataSource getInstance() {
        if (sCategoryRemoteDataSource == null) {
            sCategoryRemoteDataSource = new CategoryRemoteDataSource();
        }
        return sCategoryRemoteDataSource;
    }

    @Override
    public void getDatas(int idDomain, GetDataCallback<Category> getDataCallback) {
        if (getDataCallback == null) return;
        List<Category> list = new ArrayList<>();
        list.add(new Category(1, "Cơm"));
        list.add(new Category(8, "Mì Que"));
        getDataCallback.onLoaded(list);
    }

    @Override
    public void getProductByCategoryId(int domainID, int categoryId,
                                       GetDataCallback<Category> getDataCallback) {
        // not required
    }
}
