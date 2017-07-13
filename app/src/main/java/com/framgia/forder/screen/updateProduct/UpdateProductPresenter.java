package com.framgia.forder.screen.updateProduct;

import android.text.TextUtils;
import com.framgia.forder.data.model.Category;
import com.framgia.forder.data.model.CollectionImage;
import com.framgia.forder.data.model.Image;
import com.framgia.forder.data.source.CategoryRepository;
import com.framgia.forder.data.source.DomainRepository;
import com.framgia.forder.data.source.ProductRepository;
import com.framgia.forder.data.source.remote.api.error.BaseException;
import com.framgia.forder.data.source.remote.api.error.SafetyError;
import com.framgia.forder.data.source.remote.api.request.UpdateProductRequest;
import com.framgia.forder.data.source.remote.api.response.UpdateProductResponse;
import com.framgia.forder.utils.Utils;
import java.util.List;
import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Listens to user actions from the UI ({@link UpdateProductFragment}), retrieves the data and
 * updates
 * the UI as required.
 */
final class UpdateProductPresenter implements UpdateProductContract.Presenter {
    private static final String TAG = UpdateProductPresenter.class.getName();

    private final UpdateProductContract.ViewModel mViewModel;
    private final CompositeSubscription mCompositeSubscription;
    private final CategoryRepository mCategoryRepository;
    private final DomainRepository mDomainRepository;
    private final ProductRepository mProductRepository;

    UpdateProductPresenter(UpdateProductContract.ViewModel viewModel,
            CategoryRepository categoryRepository, DomainRepository domainRepository,
            ProductRepository productRepository) {
        mViewModel = viewModel;
        mCategoryRepository = categoryRepository;
        mDomainRepository = domainRepository;
        mProductRepository = productRepository;
        mCompositeSubscription = new CompositeSubscription();
    }

    @Override
    public void onStart() {
        getListCategory();
    }

    @Override
    public void onStop() {
        mCompositeSubscription.clear();
    }

    private void getListCategory() {
        Subscription subscription = mCategoryRepository.getListAllCategory()
                .subscribeOn(Schedulers.io())
                .doOnSubscribe(new Action0() {
                    @Override
                    public void call() {
                        mViewModel.onShowProgressDialog();
                    }
                })
                .doAfterTerminate(new Action0() {
                    @Override
                    public void call() {
                        mViewModel.onHideProgressDialog();
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<List<Category>>() {
                    @Override
                    public void call(List<Category> categories) {
                        mViewModel.onGetCategoriesSuccess(categories);
                    }
                }, new SafetyError() {
                    @Override
                    public void onSafetyError(BaseException error) {
                        mViewModel.onGetCategoriesError(error);
                    }
                });
        mCompositeSubscription.add(subscription);
    }

    @Override
    public void updateProduct(UpdateProductRequest updateProductRequest) {
        Subscription subscription = Observable.just(updateProductRequest)
                .flatMap(new Func1<UpdateProductRequest, Observable<UpdateProductResponse>>() {

                    @Override
                    public Observable<UpdateProductResponse> call(
                            UpdateProductRequest updateProductRequest) {
                        if (updateProductRequest.getImage() != null) {
                            String imageBase64 = Utils.ImageUtils.convertImagetoBase64(
                                    updateProductRequest.getImage());
                            updateProductRequest.getProduct()
                                    .setImage(new CollectionImage(new Image(imageBase64)));
                        }
                        return mProductRepository.requestUpdateProduct(
                                updateProductRequest.getProductId(), updateProductRequest);
                    }
                })
                .subscribeOn(Schedulers.io())
                .doOnSubscribe(new Action0() {
                    @Override
                    public void call() {
                        mViewModel.onShowProgressDialog();
                    }
                })
                .doAfterTerminate(new Action0() {
                    @Override
                    public void call() {
                        mViewModel.onHideProgressDialog();
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<UpdateProductResponse>() {
                    @Override
                    public void call(UpdateProductResponse response) {
                        mViewModel.onUpdateProductSuccess();
                    }
                }, new SafetyError() {
                    @Override
                    public void onSafetyError(BaseException error) {
                        mViewModel.onUpdateProductError(error);
                    }
                });
        mCompositeSubscription.add(subscription);
    }

    @Override
    public boolean validateDataInput(String name, String description) {
        boolean isValid = true;
        if (TextUtils.isEmpty(name)) {
            isValid = false;
            mViewModel.onInputNameError();
        }
        if (TextUtils.isEmpty(description)) {
            isValid = false;
            mViewModel.onInputDescriptionError();
        }
        return isValid;
    }
}
