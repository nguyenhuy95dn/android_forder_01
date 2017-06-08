package com.framgia.forder.screen.cart.notecart;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import com.framgia.forder.BR;
import com.framgia.forder.data.model.CartItem;

/**
 * Exposes the data to be used in the Notecart screen.
 */

public class NoteCartViewModel extends BaseObservable implements NoteCartContract.ViewModel {
    private static final String TAG = "NoteCartViewModel";
    private NoteCartContract.Presenter mPresenter;

    private CartItem mCartItem;
    private String mNote;

    NoteCartViewModel(CartItem cartItem) {
        mCartItem = cartItem;
        mNote = cartItem.getNotes();
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
    public void setPresenter(NoteCartContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Bindable
    public String getNote() {
        return mNote;
    }

    public void setNote(String note) {
        mNote = note;
        notifyPropertyChanged(BR.note);
        mCartItem.setNotes(mNote);
    }
}
