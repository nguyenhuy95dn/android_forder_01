package com.framgia.forder.screen.searchpage;

import android.databinding.BaseObservable;
import android.support.v4.app.Fragment;
import com.arlib.floatingsearchview.FloatingSearchView;
import com.arlib.floatingsearchview.suggestions.model.SearchSuggestion;
import com.framgia.forder.data.model.Product;
import com.framgia.forder.data.model.Shop;
import com.framgia.forder.data.source.remote.api.error.BaseException;
import com.framgia.forder.screen.searchproduct.ProductSearchResultFragment;
import com.framgia.forder.screen.searchshop.ShopSearchResultFragment;
import com.framgia.forder.utils.navigator.Navigator;
import java.util.List;

/**
 * Exposes the data to be used in the FragmentSearchContainer screen.
 */

public class SearchContainerViewModel extends BaseObservable
        implements SearchContainerContract.ViewModel {

    private SearchContainerContract.Presenter mPresenter;
    private final SearchContainerAdapter mAdapter;
    private final Navigator mNavigator;

    SearchContainerViewModel(SearchContainerAdapter adapter, Navigator navigator) {
        mAdapter = adapter;
        mNavigator = navigator;
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
        if (fragment instanceof ProductSearchResultFragment) {
            ((ProductSearchResultFragment) fragment).setProducts(products);
        }
    }

    @Override
    public void onSearchShopsSuccess(List<Shop> shops) {
        Fragment fragment = mAdapter.getFragment(SearchContainerAdapter.SearchResultsTab.TAB_SHOP);
        if (fragment instanceof ShopSearchResultFragment) {
            ((ShopSearchResultFragment) fragment).setShops(shops);
        }
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
                //Todo dev later
            }

            @Override
            public void onSearchAction(String keyWord) {
                onClickSearch(keyWord);
            }
        };
    }
}
