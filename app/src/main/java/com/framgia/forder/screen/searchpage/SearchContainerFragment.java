package com.framgia.forder.screen.searchpage;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.framgia.forder.R;
import com.framgia.forder.data.source.DomainRepository;
import com.framgia.forder.data.source.SearchRepository;
import com.framgia.forder.data.source.local.DomainLocalDataSource;
import com.framgia.forder.data.source.local.UserLocalDataSource;
import com.framgia.forder.data.source.local.sharedprf.SharedPrefsApi;
import com.framgia.forder.data.source.local.sharedprf.SharedPrefsImpl;
import com.framgia.forder.data.source.remote.DomainRemoteDataSource;
import com.framgia.forder.data.source.remote.SearchRemoteDataSource;
import com.framgia.forder.data.source.remote.api.service.FOrderServiceClient;
import com.framgia.forder.databinding.FragmentSearchContainerBinding;
import com.framgia.forder.utils.navigator.Navigator;

/**
 * FragmentSearchContainer Screen.
 */
public class SearchContainerFragment extends Fragment {
    private SearchContainerContract.ViewModel mViewModel;

    public static SearchContainerFragment newInstance() {
        return new SearchContainerFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SearchContainerAdapter adapter =
                new SearchContainerAdapter(getActivity(), getChildFragmentManager());
        Navigator navigator = new Navigator(getParentFragment());
        mViewModel = new SearchContainerViewModel(adapter, navigator);
        SearchRepository searchRepository =
                new SearchRepository(new SearchRemoteDataSource(FOrderServiceClient.getInstance()));

        SharedPrefsApi prefsApi = new SharedPrefsImpl(getActivity().getApplicationContext());
        DomainRepository domainRepository =
                new DomainRepository(new DomainRemoteDataSource(FOrderServiceClient.getInstance()),
                        new DomainLocalDataSource(prefsApi, new UserLocalDataSource(prefsApi)));
        SearchContainerContract.Presenter presenter =
                new SearchContainerPresenter(mViewModel, searchRepository, domainRepository);
        mViewModel.setPresenter(presenter);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {
        FragmentSearchContainerBinding binding =
                DataBindingUtil.inflate(inflater, R.layout.fragment_search_container, container,
                        false);
        binding.setViewModel((SearchContainerViewModel) mViewModel);
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
