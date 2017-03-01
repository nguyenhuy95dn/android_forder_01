package com.example.duong.android_forder_01.data.source.remote;

import com.example.duong.android_forder_01.data.model.Category;
import com.example.duong.android_forder_01.data.source.DataSource;
import com.example.duong.android_forder_01.data.source.GetDataCallback;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tri on 27/02/2017.
 */
public class CategoryRemoteDataSource implements DataSource<Category> {
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
        list.add(new Category(1, "Cơm"));
        list.add(new Category(2, "Chè"));
        list.add(new Category(3, "Bún"));
        list.add(new Category(4, "Phở"));
        list.add(new Category(5, "Bánh mì"));
        list.add(new Category(6, "Sinh Tố"));
        getDataCallback.onLoaded(list);
    }

    @Override
    public void getCategoryById(int idCategory,
                                GetDataCallback<Category> getDataCallback) {
        // not required
    }
}
