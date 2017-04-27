package com.framgia.forder.screen.searchpage;

import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.support.v4.app.Fragment;
import android.support.v4.content.res.ResourcesCompat;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.arlib.floatingsearchview.FloatingSearchView;
import com.arlib.floatingsearchview.suggestions.SearchSuggestionsAdapter;
import com.arlib.floatingsearchview.suggestions.model.SearchSuggestion;
import com.framgia.forder.BR;
import com.framgia.forder.R;
import com.framgia.forder.data.model.DataSuggest;
import com.framgia.forder.data.model.Product;
import com.framgia.forder.data.model.Shop;
import com.framgia.forder.data.source.remote.api.error.BaseException;
import com.framgia.forder.screen.searchproduct.ProductSearchResultFragment;
import com.framgia.forder.screen.searchshop.ShopSearchResultFragment;
import com.framgia.forder.utils.searchview.DataHelper;
import java.util.ArrayList;
import java.util.List;

/**
 * Exposes the data to be used in the FragmentSearchContainer screen.
 */

public class SearchContainerViewModel extends BaseObservable
        implements SearchContainerContract.ViewModel {

    public static final long FIND_SUGGESTION_SIMULATED_DELAY = 250;

    private SearchContainerContract.Presenter mPresenter;
    private SearchContainerAdapter mAdapter;
    private List<DataSuggest> mSuggests;
    private List<DataSuggest> mDataSuggests;
    private Context mContext;
    private DataHelper mDataHelper;
    private ArrayAdapter<DataSuggest> mSuggestArrayAdapter;

    public SearchContainerViewModel(Context context, SearchContainerAdapter adapter) {
        mContext = context;
        mAdapter = adapter;
        mSuggests = new ArrayList<>();
        mDataSuggests = new ArrayList<>();
        mSuggestArrayAdapter =
                new ArrayAdapter<>(mContext, android.R.layout.simple_list_item_1, mDataSuggests);
        mDataHelper = new DataHelper();
    }

    @Override
    public void onStart() {
        mPresenter.getDataSuggest();
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
    public void onSearchProductsError(BaseException error) {
        // Todo show dialog message
    }

    @Override
    public void onSearchShopsError(BaseException error) {
        // Todo show dialog message
    }

    @Override
    public void onGetDataSuggest(List<DataSuggest> dataSuggests) {
        updateData(dataSuggests);
    }

    public void updateData(List<DataSuggest> list) {
        if (list == null) {
            return;
        }
        for (DataSuggest dataSuggest : list) {
            mSuggestArrayAdapter.addAll(dataSuggest);
        }
        mSuggestArrayAdapter.notifyDataSetChanged();
    }

    @Override
    public void onGetDataSuggestError(Throwable e) {

    }

    @Override
    public void reloadData() {
        mPresenter.getDataSuggest();
    }

    @Override
    public void onClickSearch(String keyWord) {
        mPresenter.search(keyWord);
    }

    public FloatingSearchView.OnSearchListener getQueryTextListener() {
        return new FloatingSearchView.OnSearchListener() {
            @Override
            public void onSuggestionClicked(SearchSuggestion searchSuggestion) {

            }

            @Override
            public void onSearchAction(String keyWord) {
                onClickSearch(keyWord);
            }
        };
    }

    public SearchSuggestionsAdapter.OnBindSuggestionCallback getSuggestionCallback() {
        return new SearchSuggestionsAdapter.OnBindSuggestionCallback() {

            @Override
            public void onBindSuggestion(View suggestionView, ImageView leftIcon, TextView textView,
                    SearchSuggestion item, int itemPosition) {
                leftIcon.setImageDrawable(ResourcesCompat.getDrawable(mContext.getResources(),
                        R.drawable.ic_history_black_24dp, null));
            }
        };
    }

    public FloatingSearchView.OnQueryChangeListener getQueryTextChangedListener() {
        return new FloatingSearchView.OnQueryChangeListener() {

            @Override
            public void onSearchTextChanged(String oldQuery, String newQuery) {
                mDataHelper.findSuggestions(mContext, newQuery, 5, FIND_SUGGESTION_SIMULATED_DELAY,
                        new DataHelper.OnFindSuggestionsListener() {

                            @Override
                            public void onResults(List<DataSuggest> results) {
                                mSuggests = results;
                                notifyPropertyChanged(BR.suggestion);
                            }
                        });
            }
        };
    }

    @Bindable
    public List<DataSuggest> getSuggestion() {
        return mSuggests;
    }
}
