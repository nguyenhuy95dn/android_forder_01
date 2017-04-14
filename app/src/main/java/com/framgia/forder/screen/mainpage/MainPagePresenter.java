package com.framgia.forder.screen.mainpage;

import com.framgia.forder.screen.main.MainActivity;

/**
 * Listens to user actions from the UI ({@link MainActivity}), retrieves the data and updates
 * the UI as required.
 */
final class MainPagePresenter implements MainPageContract.Presenter {
    private static final String TAG = MainPagePresenter.class.getName();

    private final MainPageContract.ViewModel mViewModel;

    public MainPagePresenter(MainPageContract.ViewModel viewModel) {
        mViewModel = viewModel;
    }

    @Override
    public void onStart() {
    }

    @Override
    public void onStop() {
    }
}
