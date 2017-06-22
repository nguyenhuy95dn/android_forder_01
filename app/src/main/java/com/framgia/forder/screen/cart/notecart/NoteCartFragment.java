package com.framgia.forder.screen.cart.notecart;

import android.app.Dialog;
import android.content.DialogInterface;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import com.framgia.forder.R;
import com.framgia.forder.data.model.CartItem;
import com.framgia.forder.data.source.DomainRepository;
import com.framgia.forder.data.source.ProductRepository;
import com.framgia.forder.data.source.local.DomainLocalDataSource;
import com.framgia.forder.data.source.local.ProductLocalDataSource;
import com.framgia.forder.data.source.local.UserLocalDataSource;
import com.framgia.forder.data.source.local.realm.RealmApi;
import com.framgia.forder.data.source.local.sharedprf.SharedPrefsApi;
import com.framgia.forder.data.source.local.sharedprf.SharedPrefsImpl;
import com.framgia.forder.data.source.remote.DomainRemoteDataSource;
import com.framgia.forder.data.source.remote.ProductRemoteDataSource;
import com.framgia.forder.data.source.remote.api.service.FOrderServiceClient;
import com.framgia.forder.databinding.FragmentNoteCartBinding;

/**
 * Notecart Screen.
 */
public class NoteCartFragment extends DialogFragment {

    private static final String KEY_CART_ITEM = "KEY_CART_ITEM";

    private NoteCartContract.ViewModel mViewModel;

    public static NoteCartFragment newInstance(CartItem cartItem) {
        NoteCartFragment fragment = new NoteCartFragment();
        Bundle args = new Bundle();
        args.putParcelable(KEY_CART_ITEM, cartItem);
        fragment.setArguments(args);
        return fragment;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final CartItem cartItem = getArguments().getParcelable(KEY_CART_ITEM);
        mViewModel = new NoteCartViewModel(cartItem);

        RealmApi realmApi = new RealmApi();
        SharedPrefsApi prefsApi = new SharedPrefsImpl(getActivity());

        DomainRepository domainRepository =
                new DomainRepository(new DomainRemoteDataSource(FOrderServiceClient.getInstance()),
                        new DomainLocalDataSource(prefsApi, new UserLocalDataSource(prefsApi)));
        int currentDomainId = domainRepository.getCurrentDomain().getId();
        ProductRepository productRepository = new ProductRepository(
                new ProductRemoteDataSource(FOrderServiceClient.getInstance()),
                new ProductLocalDataSource(realmApi), currentDomainId);
        final NoteCartContract.Presenter presenter =
                new NoteCartPresenter(mViewModel, productRepository);
        mViewModel.setPresenter(presenter);

        FragmentNoteCartBinding binding = DataBindingUtil.inflate(LayoutInflater.from(getContext()),
                R.layout.fragment_note_cart, null, false);

        binding.setViewModel((NoteCartViewModel) mViewModel);
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity(),
                R.style.Theme_AppCompat_DayNight_Dialog).setTitle(R.string.add_note)
                .setView(binding.getRoot())
                .setPositiveButton(R.string.save, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(@NonNull DialogInterface dialogInterface, int i) {
                        presenter.editNoteCart(cartItem);
                    }
                });
        return builder.create();
    }

    @Override
    public void onStart() {
        super.onStart();
        mViewModel.onStart();
    }

    @Override
    public void onStop() {
        mViewModel.onStop();
        super.onStop();
    }
}
