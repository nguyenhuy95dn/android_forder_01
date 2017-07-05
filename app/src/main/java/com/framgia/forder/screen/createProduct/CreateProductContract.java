package com.framgia.forder.screen.createProduct;

import com.framgia.forder.data.model.Category;
import com.framgia.forder.data.source.remote.api.error.BaseException;
import com.framgia.forder.data.source.remote.api.request.RegisterProductRequest;
import com.framgia.forder.screen.BasePresenter;
import com.framgia.forder.screen.BaseViewModel;
import java.util.List;

/**
 * This specifies the contract between the view and the presenter.
 */
interface CreateProductContract {
    /**
     * View.
     */
    interface ViewModel extends BaseViewModel<Presenter> {
        void onGetCategoriesSuccess(List<Category> categories);

        void onGetCategoriesError(BaseException error);

        void onInputNameError();

        void onInputDescriptionError();

        void setImage(String image);

        void onRegisterProductSuccess();

        void onRegisterProductError(BaseException error);

        void onShowProgressDialog();

        void onHideProgressDialog();
    }

    /**
     * Presenter.
     */
    interface Presenter extends BasePresenter {
        boolean validateDataInput(String name, String description, String price);

        void registerProduct(RegisterProductRequest registerProductRequest);
    }
}
