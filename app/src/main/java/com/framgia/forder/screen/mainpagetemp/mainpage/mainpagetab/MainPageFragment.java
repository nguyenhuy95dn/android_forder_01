package com.framgia.forder.screen.mainpagetemp.mainpage.mainpagetab;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.framgia.forder.R;
import com.framgia.forder.databinding.FragmentMainPageBinding;

/**
 * Created by Duong on 4/13/2017.
 */

public class MainPageFragment extends Fragment {
    private MainPageContract.ViewModel mViewModel;

    public static MainPageFragment newInstance() {
        return new MainPageFragment();
    }

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {
        mViewModel = new MainPageViewModel();
        MainPageContract.Presenter presenter = new MainPagePresenter(mViewModel);
        mViewModel.setPresenter(presenter);
        FragmentMainPageBinding binding =
                DataBindingUtil.inflate(inflater, R.layout.fragment_main_page, container,
                        false);
        binding.setViewModel((MainPageViewModel) mViewModel);
        return binding.getRoot();
    }
}
