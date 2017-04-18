package com.framgia.forder.screen.searchpage;

/**
 * Listens to user actions from the UI ({@link SearchContainerFragment}), retrieves the data
 * and updates
 * the UI as required.
 */
final class SearchContainerPresenter implements SearchContainerContract.Presenter {
    private static final String TAG = SearchContainerPresenter.class.getName();

    private final SearchContainerContract.ViewModel mViewModel;

    public SearchContainerPresenter(SearchContainerContract.ViewModel viewModel) {
        mViewModel = viewModel;
    }

    @Override
    public void onStart() {
    }

    @Override
    public void onStop() {
    }
}
