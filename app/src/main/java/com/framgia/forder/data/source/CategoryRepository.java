package com.framgia.forder.data.source;

import com.framgia.forder.data.model.Category;
import com.framgia.forder.data.source.remote.CategoryRemoteDataSource;
import java.util.List;
import rx.Observable;

/**
 * Created by Age on 5/24/2017.
 */

public class CategoryRepository {
    private final CategoryDataSource.RemoteDataSource mRemoteDataSource;

    public CategoryRepository(CategoryRemoteDataSource remoteDataSource) {
        mRemoteDataSource = remoteDataSource;
    }

    public Observable<List<Category>> getListCategory(int currentDomainId) {
        return mRemoteDataSource.getListCategory(currentDomainId);
    }

    public Observable<List<Category>> getListAllCategory() {
        return mRemoteDataSource.getListAllCategory();
    }
}
