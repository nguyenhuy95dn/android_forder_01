package com.framgia.forder.screen.chooseDomain;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import com.framgia.forder.R;
import com.framgia.forder.databinding.ActivityChoosedomainBinding;

/**
 * ChooseDomain Screen.
 */
public class ChooseDomainActivity extends AppCompatActivity {

    private ChooseDomainContract.ViewModel mViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mViewModel = new ChooseDomainViewModel();

        ChooseDomainContract.Presenter presenter = new ChooseDomainPresenter(mViewModel);
        mViewModel.setPresenter(presenter);

        ActivityChoosedomainBinding binding =
                DataBindingUtil.setContentView(this, R.layout.activity_choosedomain);
        binding.setViewModel((ChooseDomainViewModel) mViewModel);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mViewModel.onStart();
    }

    @Override
    protected void onStop() {
        mViewModel.onStop();
        super.onStop();
    }
}
