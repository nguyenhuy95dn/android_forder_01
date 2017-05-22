package com.framgia.forder.screen.managerdetail;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.framgia.forder.R;
import com.framgia.forder.data.model.User;
import com.framgia.forder.databinding.FragmentManagerdetailBinding;

/**
 * ManagerDetail Screen.
 */
public class ManagerDetailFragment extends Fragment {

    private static final String EXTRA_MANAGER = "EXTRA_MANAGER";
    private ManagerDetailContract.ViewModel mViewModel;

    public static ManagerDetailFragment newInstance(User user) {
        ManagerDetailFragment managerDetailFragment = new ManagerDetailFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable(EXTRA_MANAGER, user);
        managerDetailFragment.setArguments(bundle);
        return managerDetailFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewModel = new ManagerDetailViewModel();

        ManagerDetailContract.Presenter presenter = new ManagerDetailPresenter(mViewModel);
        mViewModel.setPresenter(presenter);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {

        FragmentManagerdetailBinding binding =
                DataBindingUtil.inflate(inflater, R.layout.fragment_managerdetail, container,
                        false);
        binding.setViewModel((ManagerDetailViewModel) mViewModel);
        return binding.getRoot();
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
