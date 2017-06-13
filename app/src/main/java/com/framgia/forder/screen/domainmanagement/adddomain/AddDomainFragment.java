package com.framgia.forder.screen.domainmanagement.adddomain;

import android.app.Dialog;
import android.content.DialogInterface;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import com.framgia.forder.R;
import com.framgia.forder.databinding.FragmentAddDomainBinding;

/**
 * Adddomain Screen.
 */
public class AddDomainFragment extends DialogFragment {

    private AddDomainContract.ViewModel mViewModel;
    private AddDomainListener mAddDomainListener;

    public static AddDomainFragment newInstance() {
        return new AddDomainFragment();
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        mViewModel =
                new AddDomainViewModel(getActivity().getApplicationContext(), mAddDomainListener);

        final AddDomainContract.Presenter presenter = new AddDomainPresenter(mViewModel);
        mViewModel.setPresenter(presenter);
        FragmentAddDomainBinding binding =
                DataBindingUtil.inflate(LayoutInflater.from(getContext()),
                        R.layout.fragment_add_domain, null, false);
        binding.setViewModel((AddDomainViewModel) mViewModel);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity(),
                R.style.Theme_AppCompat_DayNight_Dialog).setTitle(R.string.add_domain)
                .setView(binding.getRoot())
                .setPositiveButton(R.string.create, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(@NonNull DialogInterface dialogInterface, int i) {
                        mViewModel.onRequestRegisterDomain();
                    }
                })
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
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

    public void setAddDomainListener(AddDomainListener addDomainListener) {
        mAddDomainListener = addDomainListener;
    }
}
