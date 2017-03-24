package com.example.duong.android_forder_01.data.source;

import com.example.duong.android_forder_01.data.model.Category;
import com.example.duong.android_forder_01.data.model.User;
import com.example.duong.android_forder_01.data.source.remote.CategoryRemoteDataSource;

import java.util.List;

/**
 * Created by tri on 27/02/2017.
 */
public class CategoryRepository implements ProductDataSource<Category> {
    private static CategoryRepository sCategoryRepository;
    private ProductDataSource mRemoteDataSource;

    private CategoryRepository(CategoryRemoteDataSource categoryRemoteDataSource) {
        mRemoteDataSource = categoryRemoteDataSource;
    }

    public static CategoryRepository getInstance() {
        if (sCategoryRepository == null) {
            sCategoryRepository = new CategoryRepository(CategoryRemoteDataSource.getInstance());
        }
        return sCategoryRepository;
    }

    @Override
    public void getDatas(int domainId, User user, final GetDataCallback<Category> getDataCallback) {
        if (getDataCallback == null || user == null) return;
        mRemoteDataSource.getDatas(domainId, user, new GetDataCallback<Category>() {
            @Override
            public void onLoaded(List<Category> datas) {
                getDataCallback.onLoaded(datas);
            }

            @Override
            public void onNotAvailable() {
                getDataCallback.onNotAvailable();
            }
        });
    }

    @Override
    public void getProductByCategoryId(int domainId, int categoryId, User user,
                                       GetDataCallback<Category> getDataCallback) {
        // not required
    }
}
