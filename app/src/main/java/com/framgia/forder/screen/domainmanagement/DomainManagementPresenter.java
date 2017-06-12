package com.framgia.forder.screen.domainmanagement;

import com.framgia.forder.data.model.DomainManagement;
import com.framgia.forder.data.source.DomainRepository;
import com.framgia.forder.data.source.remote.api.error.BaseException;
import com.framgia.forder.data.source.remote.api.error.SafetyError;
import java.util.List;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Listens to user actions from the UI ({@link DomainManagementFragment}), retrieves the data and
 * updates
 * the UI as required.
 */
final class DomainManagementPresenter implements DomainManagementContract.Presenter {
    private static final String TAG = DomainManagementPresenter.class.getName();

    private final DomainManagementContract.ViewModel mViewModel;
    private final DomainRepository mDomainRepository;
    private final CompositeSubscription mCompositeSubscription;

    DomainManagementPresenter(DomainManagementContract.ViewModel viewModel,
            DomainRepository domainRepository) {
        mViewModel = viewModel;
        mDomainRepository = domainRepository;
        mCompositeSubscription = new CompositeSubscription();
    }

    @Override
    public void onStart() {
        getListDomainManagement();
    }

    @Override
    public void onStop() {
        mCompositeSubscription.clear();
    }

    @Override
    public void getListDomainManagement() {
        Subscription subscription = mDomainRepository.getListDomainManagement()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<List<DomainManagement>>() {
                    @Override
                    public void call(List<DomainManagement> domainManagementList) {
                        mViewModel.onGetListDomainManagementSuccess(domainManagementList);
                    }
                }, new SafetyError() {
                    @Override
                    public void onSafetyError(BaseException error) {
                        mViewModel.onGetListDomainManagementError(error);
                    }
                });
        mCompositeSubscription.add(subscription);
    }
}
