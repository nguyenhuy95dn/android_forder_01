package com.framgia.forder.screen.searchpage;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.support.v4.app.Fragment;
import com.arlib.floatingsearchview.FloatingSearchView;
import com.arlib.floatingsearchview.suggestions.model.SearchSuggestion;
import com.framgia.forder.BR;
import com.framgia.forder.data.model.Product;
import com.framgia.forder.data.model.Shop;
import com.framgia.forder.data.model.Suggestion;
import com.framgia.forder.data.source.remote.api.error.BaseException;
import com.framgia.forder.screen.searchproduct.ProductSearchResultFragment;
import com.framgia.forder.screen.searchshop.ShopSearchResultFragment;
import com.framgia.forder.utils.navigator.Navigator;
import java.util.ArrayList;
import java.util.List;

/**
 * Exposes the data to be used in the FragmentSearchContainer screen.
 */

public class SearchContainerViewModel extends BaseObservable
        implements SearchContainerContract.ViewModel {

    private SearchContainerContract.Presenter mPresenter;
    private final SearchContainerAdapter mAdapter;
    private final Navigator mNavigator;
    private final List<SearchSuggestion> mSearchSuggestions;
    private String mKeyWord;

    SearchContainerViewModel(SearchContainerAdapter adapter, Navigator navigator) {
        mAdapter = adapter;
        mNavigator = navigator;
        mSearchSuggestions = new ArrayList<>();
        notifyPropertyChanged(BR.clear);
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
    public void setPresenter(SearchContainerContract.Presenter presenter) {
        mPresenter = presenter;
    }

    public SearchContainerAdapter getAdapter() {
        return mAdapter;
    }

    @Override
    public void onSearchProductsSuccess(List<Product> products) {
        Fragment fragment =
                mAdapter.getFragment(SearchContainerAdapter.SearchResultsTab.TAB_PRODUCT);
        ((ProductSearchResultFragment) fragment).setProducts(products);
    }

    @Override
    public void onSearchShopsSuccess(List<Shop> shops) {
        Fragment fragment = mAdapter.getFragment(SearchContainerAdapter.SearchResultsTab.TAB_SHOP);
        ((ShopSearchResultFragment) fragment).setShops(shops);
    }

    @Override
    public void onSearchError(BaseException error) {
        mNavigator.showToast(error.getMessage());
    }

    @Override
    public void onClickSearch(String keyWord) {
        mPresenter.search(keyWord);
    }

    public FloatingSearchView.OnSearchListener getQueryTextListener() {
        return new FloatingSearchView.OnSearchListener() {
            @Override
            public void onSuggestionClicked(SearchSuggestion searchSuggestion) {
                onClickSearch(mKeyWord);
            }

            @Override
            public void onSearchAction(String keyWord) {
            }
        };
    }

    public FloatingSearchView.OnQueryChangeListener getQueryChangeListener() {
        return new FloatingSearchView.OnQueryChangeListener() {
            @Override
            public void onSearchTextChanged(String oldQuery, String newQuery) {
                mKeyWord = newQuery;
                mSearchSuggestions.clear();
                mSearchSuggestions.add(new Suggestion(newQuery));
                notifyPropertyChanged(BR.suggests);
            }
        };
    }

    @Bindable
    public boolean isClear() {
        return true;
    }

    @Bindable
    public List<SearchSuggestion> getSuggests() {
        return mSearchSuggestions;
    }
}
