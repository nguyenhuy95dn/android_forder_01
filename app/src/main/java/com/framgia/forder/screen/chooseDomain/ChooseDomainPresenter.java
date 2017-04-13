package com.framgia.forder.screen.chooseDomain;

import com.framgia.forder.data.model.Domain;
import com.framgia.forder.data.source.DomainRepository;
import java.util.List;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Listens to user actions from the UI ({@link ChooseDomainActivity}), retrieves the data and
 * updates
 * the UI as required.
 */
final class ChooseDomainPresenter implements ChooseDomainContract.Presenter {
    private static final String TAG = ChooseDomainPresenter.class.getName();

    private final ChooseDomainContract.ViewModel mViewModel;
    private CompositeSubscription mCompositeSubscription;
    private DomainRepository mDomainRepository;

    public ChooseDomainPresenter(ChooseDomainContract.ViewModel viewModel,
            DomainRepository domainRepository) {
        mViewModel = viewModel;
        mDomainRepository = domainRepository;
        mCompositeSubscription = new CompositeSubscription();
    }

    @Override
    public void onStart() {
        getListDomain();
    }

    @Override
    public void onStop() {
        mCompositeSubscription.clear();
    }

    public void getListDomain() {
        Subscription subscription = mDomainRepository.getListDomain()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<List<Domain>>() {
                    @Override
                    public void call(List<Domain> domains) {
                        mViewModel.onGetDomainSuccess(domains);
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        mViewModel.onGetDomainError(throwable);
                    }
                });
        mCompositeSubscription.add(subscription);
    }

    @Override
    public void saveDomainId(int id) {
        mDomainRepository.saveDomainId(id);
    }
}
