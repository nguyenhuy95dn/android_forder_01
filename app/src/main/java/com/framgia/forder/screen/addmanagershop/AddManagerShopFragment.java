package com.framgia.forder.screen.addmanagershop;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.framgia.forder.R;
import com.framgia.forder.data.source.DomainRepository;
import com.framgia.forder.data.source.UserRepository;
import com.framgia.forder.data.source.local.DomainLocalDataSource;
import com.framgia.forder.data.source.local.UserLocalDataSource;
import com.framgia.forder.data.source.local.sharedprf.SharedPrefsApi;
import com.framgia.forder.data.source.local.sharedprf.SharedPrefsImpl;
import com.framgia.forder.data.source.remote.DomainRemoteDataSource;
import com.framgia.forder.data.source.remote.api.service.FOrderServiceClient;
import com.framgia.forder.databinding.FragmentAddManagerShopBinding;
import com.framgia.forder.utils.navigator.Navigator;

/**
 * Addmanagershop Screen.
 */
public class AddManagerShopFragment extends Fragment {

    private AddManagerShopContract.ViewModel mViewModel;

    public static AddManagerShopFragment newInstance() {
        return new AddManagerShopFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Navigator navigator = new Navigator(getParentFragment());
        AddManagerShopAdapter adapter = new AddManagerShopAdapter(getContext());
        mViewModel = new AddManagerShopViewModel(navigator, adapter);

        SharedPrefsApi prefsApi = new SharedPrefsImpl(getActivity().getApplicationContext());
        UserRepository userRepository = new UserRepository(null, new UserLocalDataSource(prefsApi));
        DomainRepository domainRepository =
                new DomainRepository(new DomainRemoteDataSource(FOrderServiceClient.getInstance()),
                        new DomainLocalDataSource(prefsApi, new UserLocalDataSource(prefsApi)));
        AddManagerShopContract.Presenter presenter =
                new AddManagerShopPresenter(mViewModel, domainRepository, userRepository);
        mViewModel.setPresenter(presenter);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {
        FragmentAddManagerShopBinding binding =
                DataBindingUtil.inflate(inflater, R.layout.fragment_add_manager_shop, container,
                        false);
        binding.setViewModel((AddManagerShopViewModel) mViewModel);
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
