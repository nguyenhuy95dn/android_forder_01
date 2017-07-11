package com.framgia.forder.screen.adduserindomain;

import com.framgia.forder.data.model.User;
import com.framgia.forder.data.source.DomainRepository;
import com.framgia.forder.data.source.remote.api.error.BaseException;
import com.framgia.forder.data.source.remote.api.error.SafetyError;
import com.framgia.forder.data.source.remote.api.request.AddUserInDomainRequest;
import com.framgia.forder.data.source.remote.api.response.BaseResponse;
import java.util.List;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Listens to user actions from the UI ({@link AddUserInDomainFragment}), retrieves the data and
 * updates
 * the UI as required.
 */
final class AddUserInDomainPresenter implements AddUserInDomainContract.Presenter {
    private static final String TAG = AddUserInDomainPresenter.class.getName();

    private final AddUserInDomainContract.ViewModel mViewModel;
    private final DomainRepository mDomainRepository;
    private final CompositeSubscription mCompositeSubscription;

    AddUserInDomainPresenter(AddUserInDomainContract.ViewModel viewModel,
            DomainRepository domainRepository) {
        mViewModel = viewModel;
        mDomainRepository = domainRepository;
        mCompositeSubscription = new CompositeSubscription();
    }

    @Override
    public void onStart() {
    }

    @Override
    public void onStop() {
        mCompositeSubscription.clear();
    }

    @Override
    public void getListUser(int domainId) {
        Subscription subscription = mDomainRepository.getListUserToAddInDomain(domainId)
                .subscribeOn(Schedulers.io())
                .doOnSubscribe(new Action0() {
                    @Override
                    public void call() {
                        mViewModel.onShowProgressBar();
                    }
                })
                .doAfterTerminate(new Action0() {
                    @Override
                    public void call() {
                        mViewModel.onHideProgressBar();
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<List<User>>() {
                    @Override
                    public void call(List<User> users) {
                        mViewModel.onGetListUserSuccess(users);
                    }
                }, new SafetyError() {
                    @Override
                    public void onSafetyError(BaseException error) {
                        mViewModel.onMessageError(error);
                    }
                });
        mCompositeSubscription.add(subscription);
    }

    @Override
    public void addUser(AddUserInDomainRequest addUserInDomainRequest) {
        Subscription subscription = mDomainRepository.requestAddUserInDomain(addUserInDomainRequest)
                .subscribeOn(Schedulers.io())
                .doOnSubscribe(new Action0() {
                    @Override
                    public void call() {
                        mViewModel.onShowProgressBarDialog();
                    }
                })
                .doAfterTerminate(new Action0() {
                    @Override
                    public void call() {
                        mViewModel.onHideProgressBarDialog();
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<BaseResponse>() {
                    @Override
                    public void call(BaseResponse response) {
                        mViewModel.onAddUserSuccess();
                    }
                }, new SafetyError() {
                    @Override
                    public void onSafetyError(BaseException error) {
                        mViewModel.onMessageError(error);
                    }
                });
        mCompositeSubscription.add(subscription);
    }
}
