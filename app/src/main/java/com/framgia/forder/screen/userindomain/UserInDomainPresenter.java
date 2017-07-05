package com.framgia.forder.screen.userindomain;

import com.framgia.forder.data.model.User;
import com.framgia.forder.data.source.DomainRepository;
import com.framgia.forder.data.source.UserRepository;
import com.framgia.forder.data.source.remote.api.error.BaseException;
import com.framgia.forder.data.source.remote.api.error.SafetyError;
import com.framgia.forder.data.source.remote.api.response.ChangeRuleOfUserResponse;
import com.framgia.forder.data.source.remote.api.response.DeleteUserInDomainResponse;
import java.util.List;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Listens to user actions from the UI ({@link UserInDomainFragment}), retrieves the data and
 * updates
 * the UI as required.
 */
final class UserInDomainPresenter implements UserInDomainContract.Presenter {

    private static final String TAG = UserInDomainPresenter.class.getName();

    private final UserInDomainContract.ViewModel mViewModel;
    private final DomainRepository mDomainRepository;
    private final UserRepository mUserRepository;
    private final CompositeSubscription mCompositeSubscription;

    UserInDomainPresenter(UserInDomainContract.ViewModel viewModel,
            DomainRepository domainRepository, UserRepository userRepository) {
        mViewModel = viewModel;
        mDomainRepository = domainRepository;
        mUserRepository = userRepository;
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
    public void getListUserInDomain(int domainId) {
        final User user = mUserRepository.getUser();
        Subscription subscription = mDomainRepository.getListUserInDomain(domainId)
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
                        mViewModel.onGetListUserInDomainSuccess(users, user.getId());
                    }
                }, new SafetyError() {
                    @Override
                    public void onSafetyError(BaseException error) {
                        mViewModel.onGetListUserInDomainError(error);
                    }
                });
        mCompositeSubscription.add(subscription);
    }

    @Override
    public void deleteUserInDomain(int domainId, final int userId) {
        Subscription subscription = mDomainRepository.requestDeleteUserInDomain(domainId, userId)
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
                .subscribe(new Action1<DeleteUserInDomainResponse>() {
                    @Override
                    public void call(DeleteUserInDomainResponse response) {
                        mViewModel.onDeleteUserInDomainSuccess();
                    }
                }, new SafetyError() {
                    @Override
                    public void onSafetyError(BaseException error) {
                        mViewModel.onDeleteUserInDomainError(error);
                    }
                });
        mCompositeSubscription.add(subscription);
    }

    @Override
    public void changeRuleOfUserInDomain(int domainId, int userId, String role) {
        Subscription subscription =
                mDomainRepository.requestChangeRuleOfUserInDomain(domainId, userId, role)
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
                        .subscribe(new Action1<ChangeRuleOfUserResponse>() {
                            @Override
                            public void call(ChangeRuleOfUserResponse response) {
                                mViewModel.onChangeRoleOfUserSuccess();
                            }
                        }, new SafetyError() {
                            @Override
                            public void onSafetyError(BaseException error) {
                                mViewModel.onChangeRoleOfUserError(error);
                            }
                        });
        mCompositeSubscription.add(subscription);
    }
}
