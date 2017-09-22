package com.framgia.forder.screen.main;

import com.framgia.forder.data.model.Cart;
import com.framgia.forder.data.model.Domain;
import com.framgia.forder.data.model.Notification;
import com.framgia.forder.data.model.ShopInDomain;
import com.framgia.forder.data.source.DomainRepository;
import com.framgia.forder.data.source.NotificationRepository;
import com.framgia.forder.data.source.ProductRepository;
import com.framgia.forder.data.source.ShopRepository;
import com.framgia.forder.data.source.remote.api.error.BaseException;
import com.framgia.forder.data.source.remote.api.error.SafetyError;
import com.framgia.forder.data.source.remote.api.response.BaseResponse;
import java.util.List;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Listens to user actions from the UI ({@link MainActivity}), retrieves the data and updates
 * the UI as required.
 */
final class MainPresenter implements MainContract.Presenter {
    private static final String TAG = MainPresenter.class.getName();
    private final CompositeSubscription mCompositeSubscription;
    private final MainContract.ViewModel mViewModel;
    private final ProductRepository mProductRepository;
    protected DomainRepository mDomainRepository;
    private final NotificationRepository mNotificationRepository;
    private final ShopRepository mShopRepository;
    private int mCurrenDomain;

    MainPresenter(MainContract.ViewModel viewModel, DomainRepository domainRepository,
            ProductRepository productRepository, NotificationRepository notificationRepository,
            ShopRepository shopRepository) {
        mViewModel = viewModel;
        mDomainRepository = domainRepository;
        mProductRepository = productRepository;
        mNotificationRepository = notificationRepository;
        mShopRepository = shopRepository;
        mCompositeSubscription = new CompositeSubscription();
        mCurrenDomain = domainRepository.getCurrentDomain().getId();
    }

    @Override
    public void onStart() {
        mProductRepository.openTransaction();
        getListCart();
        getListNotification();
        getListShopInDomain(mCurrenDomain);
    }

    @Override
    public void onStop() {
        mCompositeSubscription.clear();
        mProductRepository.closeTransaction();
    }

    @Override
    public void getCurrentDomain() {
        mViewModel.showCurrentDomain(mDomainRepository.getCurrentDomain().getName());
    }

    @Override
    public void getListDomain() {
        Subscription subscription = mDomainRepository.getListDomain()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<List<Domain>>() {
                    @Override
                    public void call(List<Domain> domains) {
                        mViewModel.onGetListDomainSuccess(domains);
                    }
                }, new SafetyError() {
                    @Override
                    public void onSafetyError(BaseException error) {
                        mViewModel.onGetListDomainError(error);
                    }
                });
        mCompositeSubscription.add(subscription);
    }

    @Override
    public int getCurrentDomainPosition(List<Domain> domains) {
        Domain domain = mDomainRepository.getCurrentDomain();
        if (domain == null) {
            return 0;
        }
        for (int i = 0; i < domains.size(); i++) {
            if (domain.getId() == domains.get(i).getId()) {
                return i;
            }
        }
        return 0;
    }

    @Override
    public void getListCart() {
        Subscription subscriptions =
                mProductRepository.getAllShoppingCart().subscribe(new Action1<List<Cart>>() {
                    @Override
                    public void call(List<Cart> carts) {
                        mViewModel.onGetListCartSuccess(carts);
                    }
                }, new SafetyError() {
                    @Override
                    public void onSafetyError(BaseException error) {
                        mViewModel.onGetListCartError(error);
                    }
                });
        mCompositeSubscription.add(subscriptions);
    }

    @Override
    public void saveCurrentDomain(Domain domain) {
        mDomainRepository.saveCurrentDomain(domain);
    }

    @Override
    public void getListNotification() {
        Subscription subscription = mNotificationRepository.getListNotification()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<List<Notification>>() {
                    @Override
                    public void call(List<Notification> notifications) {
                        mViewModel.onGetListNotificationSuccess(notifications);
                    }
                }, new SafetyError() {
                    @Override
                    public void onSafetyError(BaseException error) {
                        mViewModel.onGetListNotificationError(error);
                    }
                });
        mCompositeSubscription.add(subscription);
    }

    @Override
    public void readAllNotification() {
        Subscription subscription = mNotificationRepository.readAllNotification(true)
                .subscribeOn(Schedulers.io())
                .subscribe(new Action1<BaseResponse>() {
                    @Override
                    public void call(BaseResponse baseResponse) {
                        mViewModel.readAllNotificationSuccess();
                    }
                }, new SafetyError() {
                    @Override
                    public void onSafetyError(BaseException error) {
                        mViewModel.readAllNotificationError(error);
                    }
                });
        mCompositeSubscription.add(subscription);
    }

    @Override
    public void goToListProduct(final int shopId) {
        //Make this step to let the adapter complete the binding
        Subscription subscription = mDomainRepository.getListDomain()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<List<Domain>>() {
                    @Override
                    public void call(List<Domain> domains) {
                        mViewModel.onGotoListProductSuccess(shopId);
                    }
                }, new SafetyError() {
                    @Override
                    public void onSafetyError(BaseException error) {
                        mViewModel.onGetListDomainError(error);
                    }
                });
        mCompositeSubscription.add(subscription);
    }

    @Override
    public void getListShopInDomain(int domainId) {
        Subscription subscription = mShopRepository.getListShopInDomain(domainId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<List<ShopInDomain>>() {
                    @Override
                    public void call(List<ShopInDomain> shops) {
                        mViewModel.onGetListShopInDomainSuccess(shops);
                    }
                }, new SafetyError() {
                    @Override
                    public void onSafetyError(BaseException error) {
                        mViewModel.onGetListShopInDomainError(error);
                    }
                });
        mCompositeSubscription.add(subscription);
    }
}
