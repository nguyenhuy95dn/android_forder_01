package com.framgia.forder.screen.domainmanagement.editdomain;

import android.app.Dialog;
import android.content.DialogInterface;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import com.framgia.forder.R;
import com.framgia.forder.data.model.DomainManagement;
import com.framgia.forder.databinding.FragmentEditDomainBinding;

/**
 * Editdomain Screen.
 */
public class EditdomainFragment extends DialogFragment {
    private static final String EXTRA_DOMAIN = "EXTRA_DOMAIN";
    private EditdomainContract.ViewModel mViewModel;
    private EditDomainListener mEditDomainListener;

    public static EditdomainFragment newInstance(DomainManagement domainManagement) {
        EditdomainFragment editdomainFragment = new EditdomainFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable(EXTRA_DOMAIN, domainManagement);
        editdomainFragment.setArguments(bundle);
        return editdomainFragment;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        DomainManagement domainManagement = (DomainManagement) getArguments().get(EXTRA_DOMAIN);
        mViewModel = new EditdomainViewModel(getActivity(), domainManagement, mEditDomainListener);

        EditdomainContract.Presenter presenter = new EditdomainPresenter(mViewModel);
        mViewModel.setPresenter(presenter);

        FragmentEditDomainBinding binding =
                DataBindingUtil.inflate(LayoutInflater.from(getContext()),
                        R.layout.fragment_edit_domain, null, false);
        binding.setViewModel((EditdomainViewModel) mViewModel);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity(),
                R.style.Theme_AppCompat_DayNight_Dialog).setTitle(R.string.edit_domain)
                .setView(binding.getRoot())
                .setPositiveButton(R.string.save, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(@NonNull DialogInterface dialogInterface, int i) {
                        mViewModel.onRequestEditDomain();
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

    public void setEditDomainListener(EditDomainListener listener) {
        mEditDomainListener = listener;
    }
}
