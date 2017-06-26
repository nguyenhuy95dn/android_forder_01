package com.framgia.forder.screen.managerinshop;

import android.app.Dialog;
import android.content.DialogInterface;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import com.framgia.forder.R;
import com.framgia.forder.data.model.OwnerShop;
import com.framgia.forder.databinding.FragmentManagerInShopBinding;
import java.util.ArrayList;
import java.util.List;

/**
 * Managerinshop Screen.
 */
public class ManagerInShopFragment extends DialogFragment {

    private static final String EXTRA_OWNER = "EXTRA_OWNER";

    private ManagerInShopContract.ViewModel mViewModel;

    public static ManagerInShopFragment newInstance() {
        return new ManagerInShopFragment();
    }

    public static ManagerInShopFragment newInstance(List<OwnerShop> ownerShops) {
        ManagerInShopFragment managerInShopFragment = new ManagerInShopFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList(EXTRA_OWNER, (ArrayList<? extends Parcelable>) ownerShops);
        managerInShopFragment.setArguments(bundle);
        return managerInShopFragment;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        List<OwnerShop> ownerShops = (List<OwnerShop>) getArguments().get(EXTRA_OWNER);

        ManagerInShopContract.Presenter presenter = new ManagerInShopPresenter(mViewModel);
        mViewModel.setPresenter(presenter);

        FragmentManagerInShopBinding binding =
                DataBindingUtil.inflate(LayoutInflater.from(getContext()),
                        R.layout.fragment_manager_in_shop, null, false);
        binding.setViewModel((ManagerInShopViewModel) mViewModel);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity(),
                R.style.Theme_AppCompat_DayNight_Dialog).setTitle(R.string.manager_in_shop)
                .setView(binding.getRoot())
                .setNegativeButton(R.string.close, new DialogInterface.OnClickListener() {
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
}
