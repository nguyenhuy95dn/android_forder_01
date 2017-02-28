package com.example.duong.android_forder_01.data.model.source;

import com.example.duong.android_forder_01.data.model.Category;
import com.example.duong.android_forder_01.data.model.source.remote.CategoryRemoteDataSource;

import java.util.List;

/**
 * Created by tri on 27/02/2017.
 */
public class CategoryRepository implements DataSource<Category> {
    private static CategoryRepository sCategoryRepository;
    private DataSource mRemoteDataSource;

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
    public void getDatas(int domainId, final GetDataCallback<Category> getDataCallback) {
        mRemoteDataSource.getDatas(domainId, new GetDataCallback<Category>() {
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
    public void getCategoryById(int idCategory,
                                GetDataCallback<Category> getDataCallback) {
        // not required
    }
}
