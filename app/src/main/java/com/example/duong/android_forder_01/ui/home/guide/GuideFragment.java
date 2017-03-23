package com.example.duong.android_forder_01.ui.home.guide;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.duong.android_forder_01.R;
import com.example.duong.android_forder_01.data.model.Guide;
import com.example.duong.android_forder_01.databinding.FragmentGuideBinding;

import static com.example.duong.android_forder_01.utils.Const.KeyIntent.EXTRA_GUIDE;

/**
 * Created by tri on 21/03/2017.
 */
public class GuideFragment extends Fragment implements GuideContract.View {
    private GuideContract.Presenter mPresenter;
    private FragmentGuideBinding mBinding;
    private Guide mGuide;

    public static GuideFragment newInstance(Guide guide) {
        GuideFragment fragment = new GuideFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(EXTRA_GUIDE, guide);
        fragment.setArguments(bundle);
        return fragment;
    }

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_guide, container, false);
        setPresenter(new GuidePresenter(this));
        mPresenter.start();
        return mBinding.getRoot();
    }

    @Override
    public void start() {
        mGuide = (Guide) getArguments().getSerializable(EXTRA_GUIDE);
        mBinding.setGuideFrament(this);
        mBinding.setGuide(mGuide);
    }

    @Override
    public void setPresenter(GuideContract.Presenter presenter) {
        mPresenter = presenter;
    }
}
