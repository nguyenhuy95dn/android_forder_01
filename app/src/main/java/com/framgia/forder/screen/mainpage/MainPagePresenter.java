package com.framgia.forder.screen.mainpage;

import com.framgia.forder.data.model.Product;
import com.framgia.forder.data.source.ProductRepository;
import com.framgia.forder.screen.main.MainActivity;

/**
 * Listens to user actions from the UI ({@link MainActivity}), retrieves the data and updates
 * the UI as required.
 */
final class MainPagePresenter implements MainPageContract.Presenter {
    private static final String TAG = MainPagePresenter.class.getName();
    private ProductRepository mProductRepository;

    private final MainPageContract.ViewModel mViewModel;

    public MainPagePresenter(MainPageContract.ViewModel viewModel,
            ProductRepository repository) {
        mViewModel = viewModel;
        mProductRepository = repository;
    }

    @Override
    public void onStart() {
        mProductRepository.openTransaction();
    }

    @Override
    public void onStop() {
        mProductRepository.closeTransaction();
    }

    @Override
    public void addToCart(Product product) {
        if (product == null) {
            return;
        }
        mProductRepository.addToCart(product);
    }
}
