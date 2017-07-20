package com.framgia.forder.screen.addmanagershop;

import com.framgia.forder.data.model.User;
import com.framgia.forder.data.source.DomainRepository;
import com.framgia.forder.data.source.UserRepository;
import com.framgia.forder.data.source.remote.api.error.BaseException;
import com.framgia.forder.data.source.remote.api.error.SafetyError;
import java.util.List;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Listens to user actions from the UI ({@link AddManagerShopFragment}), retrieves the data and
 * updates
 * the UI as required.
 */
final class AddManagerShopPresenter implements AddManagerShopContract.Presenter {
    private static final String TAG = AddManagerShopPresenter.class.getName();

    private final AddManagerShopContract.ViewModel mViewModel;
    private final DomainRepository mDomainRepository;
    private final UserRepository mUserRepository;
    private final CompositeSubscription mCompositeSubscription;

    AddManagerShopPresenter(AddManagerShopContract.ViewModel viewModel,
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
    public void getListUser() {
        final int userId = mUserRepository.getUser().getId();
        Subscription subscription =
                mDomainRepository.getListUserInDomain(mDomainRepository.getCurrentDomain().getId())
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
                                mViewModel.onGetListUserSuccess(users, userId);
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
    public void addManagerInShop(int shopId, int userId) {
        //Todo Edit later
    }
}
