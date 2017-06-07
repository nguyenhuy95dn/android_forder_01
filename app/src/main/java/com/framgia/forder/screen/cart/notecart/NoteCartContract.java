package com.framgia.forder.screen.cart.notecart;

import com.framgia.forder.data.model.CartItem;
import com.framgia.forder.screen.BasePresenter;
import com.framgia.forder.screen.BaseViewModel;

/**
 * This specifies the contract between the view and the presenter.
 */
interface NoteCartContract {
    /**
     * View.
     */
    interface ViewModel extends BaseViewModel<Presenter> {
    }

    /**
     * Presenter.
     */
    interface Presenter extends BasePresenter {
        void editNoteCart(CartItem cartItem);
    }
}
