package com.framgia.forder.screen.searchpage;

/**
 * Exposes the data to be used in the FragmentSearchContainer screen.
 */

public class SearchContainerViewModel implements SearchContainerContract.ViewModel {

    private SearchContainerContract.Presenter mPresenter;
    private SearchContainerAdapter mAdapter;

    public SearchContainerViewModel(SearchContainerAdapter adapter) {
        mAdapter = adapter;
    }

    @Override
    public void onStart() {
        mPresenter.onStart();
    }

    @Override
    public void onStop() {
        mPresenter.onStop();
    }

    @Override
    public void setPresenter(SearchContainerContract.Presenter presenter) {
        mPresenter = presenter;
    }

    public SearchContainerAdapter getAdapter() {
        return mAdapter;
    }
}
