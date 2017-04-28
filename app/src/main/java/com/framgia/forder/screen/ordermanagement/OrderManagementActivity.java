package com.framgia.forder.screen.ordermanagement;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import com.framgia.forder.R;
import com.framgia.forder.databinding.ActivityOrderManagementBinding;
import com.framgia.forder.utils.navigator.Navigator;

/**
 * OrderManagement Screen.
 */
public class OrderManagementActivity extends AppCompatActivity {

    private OrderManagementContract.ViewModel mViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Navigator navigator = new Navigator(this);
        mViewModel = new OrderManagementViewModel(navigator);

        OrderManagementContract.Presenter presenter = new OrderManagementPresenter(mViewModel);
        mViewModel.setPresenter(presenter);

        ActivityOrderManagementBinding binding =
                DataBindingUtil.setContentView(this, R.layout.activity_order_management);
        binding.setViewModel((OrderManagementViewModel) mViewModel);

        setSupportActionBar(binding.toolbarOrderManagement);
        getSupportActionBar().setTitle(R.string.order_management);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
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
