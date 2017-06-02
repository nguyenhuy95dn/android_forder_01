package com.framgia.forder.screen.createProduct;

import android.text.TextUtils;
import com.framgia.forder.data.model.Category;
import com.framgia.forder.data.model.CollectionImage;
import com.framgia.forder.data.model.Image;
import com.framgia.forder.data.model.User;
import com.framgia.forder.data.source.CategoryRepository;
import com.framgia.forder.data.source.DomainRepository;
import com.framgia.forder.data.source.ProductRepository;
import com.framgia.forder.data.source.UserRepository;
import com.framgia.forder.data.source.remote.api.error.BaseException;
import com.framgia.forder.data.source.remote.api.error.SafetyError;
import com.framgia.forder.data.source.remote.api.request.RegisterProductRequest;
import com.framgia.forder.data.source.remote.api.response.RegisterProductResponse;
import com.framgia.forder.utils.Utils;
import java.util.List;
import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Listens to user actions from the UI ({@link CreateProductFragment}), retrieves the data and
 * updates
 * the UI as required.
 */
final class CreateProductPresenter implements CreateProductContract.Presenter {
    private static final String TAG = CreateProductPresenter.class.getName();
    public static final int NUMBER_COMPRESS = 100;

    private final CreateProductContract.ViewModel mViewModel;
    private final CompositeSubscription mCompositeSubscription;
    private final CategoryRepository mCategoryRepository;
    private final DomainRepository mDomainRepository;
    private final ProductRepository mProductRepository;
    private final UserRepository mUserRepository;

    CreateProductPresenter(CreateProductContract.ViewModel viewModel,
            CategoryRepository categoryRepository, DomainRepository domainRepository,
            UserRepository userRepository, ProductRepository productRepository) {
        mViewModel = viewModel;
        mCategoryRepository = categoryRepository;
        mDomainRepository = domainRepository;
        mUserRepository = userRepository;
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
        int currentDomain = mDomainRepository.getCurrentDomain().getId();
        Subscription subscription = mCategoryRepository.getListCategory(currentDomain)
                .subscribeOn(Schedulers.io())
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
    public boolean validateDataInput(String name, String description, String price) {
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

    @Override
    public void registerProduct(RegisterProductRequest registerProductRequest) {
        User user = mUserRepository.getUser();
        registerProductRequest.setUserEmail(user.getEmail());
        registerProductRequest.setUserToken(user.getToken());
        Subscription subscription = Observable.just(registerProductRequest)
                .flatMap(new Func1<RegisterProductRequest, Observable<RegisterProductResponse>>() {

                    @Override
                    public Observable<RegisterProductResponse> call(
                            RegisterProductRequest registerProductRequest) {
                        String imageBase64 = Utils.ImageUtils.convertImagetoBase64(
                                registerProductRequest.getImage());
                        registerProductRequest.getProduct()
                                .setImage(new CollectionImage(new Image(imageBase64)));
                        return mProductRepository.requestRegisterProduct(registerProductRequest);
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<RegisterProductResponse>() {
                    @Override
                    public void call(RegisterProductResponse response) {
                        mViewModel.onRegisterProductSuccess();
                    }
                }, new SafetyError() {
                    @Override
                    public void onSafetyError(BaseException error) {
                        mViewModel.onRegisterProductError(error);
                    }
                });
        mCompositeSubscription.add(subscription);
    }
}
