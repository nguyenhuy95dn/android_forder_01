package com.framgia.forder.screen.searchpage;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import com.framgia.forder.R;
import com.framgia.forder.databinding.FragmentSearchContainerBinding;

/**
 * FragmentSearchContainer Screen.
 */
public class SearchContainerFragment extends Fragment implements SearchView.OnQueryTextListener {

    private SearchContainerContract.ViewModel mViewModel;
    private FragmentSearchContainerBinding mBinding;

    public static SearchContainerFragment newInstance() {
        return new SearchContainerFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {
        SearchContainerAdapter adapter =
                new SearchContainerAdapter(getChildFragmentManager(), getActivity());
        mViewModel = new SearchContainerViewModel(adapter);

        SearchContainerContract.Presenter presenter = new SearchContainerPresenter(mViewModel);
        mViewModel.setPresenter(presenter);

        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_search_container, container,
                false);
        mBinding.setViewModel((SearchContainerViewModel) mViewModel);
        setUpView();
        return mBinding.getRoot();
    }

    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);
        getActivity().getMenuInflater().inflate(R.menu.menu_search, menu);
        MenuItem item = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) item.getActionView();
        searchView.setIconified(false);
        searchView.getBaselineAlignedChildIndex();
        searchView.setOnQueryTextListener(this);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    private void setUpView() {
        setHasOptionsMenu(true);
        ((AppCompatActivity) getActivity()).setSupportActionBar(mBinding.toolbar);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayShowTitleEnabled(false);
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return false;
    }
}
