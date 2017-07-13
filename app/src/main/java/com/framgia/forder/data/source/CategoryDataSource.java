package com.framgia.forder.data.source;

import com.framgia.forder.data.model.Category;
import java.util.List;
import rx.Observable;

/**
 * Created by Age on 5/24/2017.
 */

public interface CategoryDataSource {
    interface RemoteDataSource {
        Observable<List<Category>> getListCategory(int domainId);

        Observable<List<Category>> getListAllCategory();
    }
}
