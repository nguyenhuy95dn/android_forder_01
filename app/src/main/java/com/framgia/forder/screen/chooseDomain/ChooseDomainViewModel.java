package com.framgia.forder.screen.chooseDomain;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.view.View;
import android.widget.ArrayAdapter;
import com.framgia.forder.data.model.Domain;
import com.framgia.forder.data.source.remote.api.error.BaseException;
import com.framgia.forder.screen.main.MainActivity;
import com.framgia.forder.utils.navigator.Navigator;
import java.util.ArrayList;
import java.util.List;

/**
 * Exposes the data to be used in the ChooseDomain screen.
 */

public class ChooseDomainViewModel extends BaseObservable
        implements ChooseDomainContract.ViewModel {

    private ChooseDomainContract.Presenter mPresenter;
    private ArrayAdapter<String> mAdapter;
    private ArrayAdapter<Domain> mAdapterDomain;
    private List<Domain> mDomains;
    private int mSelectedTypePosition;
    private Navigator mNavigator;

    public ChooseDomainViewModel(ArrayAdapter<String> adapter, ArrayAdapter<Domain> adapterDomain,
            Navigator navigator) {
        mDomains = new ArrayList<>();
        mNavigator = navigator;
        mAdapter = adapter;
        mAdapterDomain = adapterDomain;
    }

    public ArrayAdapter<String> getAdapter() {
        return mAdapter;
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
    public void setPresenter(ChooseDomainContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void onGetDomainSuccess(List<Domain> domains) {
        if (domains == null) {
            return;
        }
        mDomains.clear();
        mDomains.addAll(domains);
        mAdapterDomain.notifyDataSetChanged();
        for (Domain domain : domains) {
            mAdapter.add(domain.getName());
        }
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void onGetDomainError(BaseException e) {
        // TODO show dialog error later
    }

    public void onClickNext(View view) {
        Domain domain = mDomains.get(mSelectedTypePosition);
        mPresenter.saveDomainId(domain.getId());
        mNavigator.startActivity(MainActivity.class);
        mNavigator.finishActivity();
    }

    @Bindable
    public Integer getSelectedTypePosition() {
        return mSelectedTypePosition;
    }

    public void setSelectedTypePosition(Integer selectedTypePosition) {
        this.mSelectedTypePosition = selectedTypePosition;
    }
}
