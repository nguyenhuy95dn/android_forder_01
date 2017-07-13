package com.framgia.forder.data.source.remote;

import com.framgia.forder.data.model.Category;
import com.framgia.forder.data.source.CategoryDataSource;
import com.framgia.forder.data.source.remote.api.response.CategoryResponse;
import com.framgia.forder.data.source.remote.api.service.FOrderApi;
import java.util.List;
import rx.Observable;
import rx.functions.Func1;

/**
 * Created by Age on 5/24/2017.
 */

public class CategoryRemoteDataSource extends BaseRemoteDataSource
        implements CategoryDataSource.RemoteDataSource {
    public CategoryRemoteDataSource(FOrderApi fOrderApi) {
        super(fOrderApi);
    }

    @Override
    public Observable<List<Category>> getListCategory(int domainId) {
        return mFOrderApi.getCategories(domainId)
                .flatMap(new Func1<CategoryResponse, Observable<List<Category>>>() {

                    @Override
                    public Observable<List<Category>> call(CategoryResponse categoryResponse) {
                        if (categoryResponse != null) {
                            return Observable.just(categoryResponse.getCategories());
                        }
                        return Observable.error(new NullPointerException());
                    }
                });
    }

    @Override
    public Observable<List<Category>> getListAllCategory() {
        return mFOrderApi.getListAllCategory()
                .flatMap(new Func1<CategoryResponse, Observable<List<Category>>>() {

                    @Override
                    public Observable<List<Category>> call(CategoryResponse categoryResponse) {
                        if (categoryResponse != null) {
                            return Observable.just(categoryResponse.getCategories());
                        }
                        return Observable.error(new NullPointerException());
                    }
                });
    }
}
