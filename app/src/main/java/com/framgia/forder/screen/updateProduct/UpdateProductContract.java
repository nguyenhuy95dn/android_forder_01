package com.framgia.forder.screen.updateProduct;

import com.framgia.forder.data.model.Category;
import com.framgia.forder.data.source.remote.api.error.BaseException;
import com.framgia.forder.data.source.remote.api.request.UpdateProductRequest;
import com.framgia.forder.screen.BasePresenter;
import com.framgia.forder.screen.BaseViewModel;
import java.util.List;

/**
 * This specifies the contract between the view and the presenter.
 */
interface UpdateProductContract {
    /**
     * View.
     */
    interface ViewModel extends BaseViewModel<Presenter> {
        void onGetCategoriesSuccess(List<Category> categories);

        void onGetCategoriesError(BaseException error);

        void onShowProgressDialog();

        void onHideProgressDialog();

        void setImage(String image);

        void onUpdateProductSuccess();

        void onUpdateProductError(BaseException error);

        void onInputNameError();

        void onInputDescriptionError();
    }

    /**
     * Presenter.
     */
    interface Presenter extends BasePresenter {
        void updateProduct(UpdateProductRequest updateProductRequest);

        boolean validateDataInput(String name, String description);
    }
}
