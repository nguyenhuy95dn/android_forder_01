package com.framgia.forder.screen.requestshopindomain;

/**
 * Listens to user actions from the UI ({@link RequestShopInDomainFragment}), retrieves the data and
 * updates
 * the UI as required.
 */
final class RequestShopInDomainPresenter implements RequestShopInDomainContract.Presenter {
    private static final String TAG = RequestShopInDomainPresenter.class.getName();

    private final RequestShopInDomainContract.ViewModel mViewModel;

    RequestShopInDomainPresenter(RequestShopInDomainContract.ViewModel viewModel) {
        mViewModel = viewModel;
    }

    @Override
    public void onStart() {
    }

    @Override
    public void onStop() {
    }
}
