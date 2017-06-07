package com.framgia.forder.screen.cart.notecart;

import com.framgia.forder.data.model.CartItem;
import com.framgia.forder.data.source.ProductRepository;
import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * Listens to user actions from the UI ({@link NoteCartFragment}), retrieves the data and updates
 * the UI as required.
 */
final class NoteCartPresenter implements NoteCartContract.Presenter {
    private static final String TAG = NoteCartPresenter.class.getName();

    private final NoteCartContract.ViewModel mViewModel;
    private final ProductRepository mProductRepository;
    private final CompositeSubscription mCompositeSubscription;

    NoteCartPresenter(NoteCartContract.ViewModel viewModel, ProductRepository productRepository) {
        mViewModel = viewModel;
        mProductRepository = productRepository;
        mCompositeSubscription = new CompositeSubscription();
    }

    @Override
    public void onStart() {
        mProductRepository.openTransaction();
    }

    @Override
    public void onStop() {
        mCompositeSubscription.clear();
        mProductRepository.closeTransaction();
    }

    @Override
    public void editNoteCart(CartItem cartItem) {
        if (cartItem == null) {
            return;
        }
        Subscription subscriptions =
                mProductRepository.editNoteCart(cartItem.getProductId(), cartItem.getNotes())
                        .subscribe();
        mCompositeSubscription.add(subscriptions);
    }
}
