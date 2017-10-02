package com.framgia.forder.screen.main;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;
import com.framgia.forder.R;
import com.framgia.forder.data.event.NetWorkStateEvent;
import com.framgia.forder.data.source.DomainRepository;
import com.framgia.forder.data.source.NotificationRepository;
import com.framgia.forder.data.source.ProductRepository;
import com.framgia.forder.data.source.ShopRepository;
import com.framgia.forder.data.source.local.DomainLocalDataSource;
import com.framgia.forder.data.source.local.ProductLocalDataSource;
import com.framgia.forder.data.source.local.UserLocalDataSource;
import com.framgia.forder.data.source.local.realm.RealmApi;
import com.framgia.forder.data.source.local.sharedprf.SharedPrefsApi;
import com.framgia.forder.data.source.local.sharedprf.SharedPrefsImpl;
import com.framgia.forder.data.source.remote.DomainRemoteDataSource;
import com.framgia.forder.data.source.remote.NotificationRemoteDataSource;
import com.framgia.forder.data.source.remote.ProductRemoteDataSource;
import com.framgia.forder.data.source.remote.ShopRemoteDataSource;
import com.framgia.forder.data.source.remote.api.ConnectivityReceiver;
import com.framgia.forder.data.source.remote.api.service.FOrderServiceClient;
import com.framgia.forder.databinding.ActivityMainBinding;
import com.framgia.forder.screen.BaseActivity;
import com.framgia.forder.widgets.dialog.DialogManager;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import static com.framgia.forder.screen.splash.SplashActivity.PARAMS;

/**
 * Main Screen.
 */
public class MainActivity extends BaseActivity
        implements LoadCartListener, ChangeDomainListener, LoadNotificationListener,
        LoadOrderHistoryListener {
    private static final String TAG = "MainActivity";

    private static final int DELAY_TIME_TWO_TAP_BACK_BUTTON = 2000;
    private static final int TAB_PROFILE = 4;
    private static final int TAB_HOME = 0;

    private MainContract.ViewModel mViewModel;
    private Handler mHandler;
    private Runnable mRunnable;
    private boolean mIsDoubleTapBack = false;
    private View mView;
    private View mViewProfile;
    private DialogManager mDialogManager;
    private boolean mIsConnected;
    private boolean mIsChecked;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String params = getIntent().getExtras().getString(PARAMS);
        mDialogManager = new DialogManager(this);

        MainViewPagerAdapter adapter = new MainViewPagerAdapter(getSupportFragmentManager());
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        mViewModel = new MainViewModel(adapter, alertDialog, this, params);

        RealmApi realmApi = new RealmApi();
        SharedPrefsApi prefsApi = new SharedPrefsImpl(getApplicationContext());
        DomainRepository domainRepository =
                new DomainRepository(new DomainRemoteDataSource(FOrderServiceClient.getInstance()),
                        new DomainLocalDataSource(prefsApi, new UserLocalDataSource(prefsApi)));
        int currentDomainId = 0;
        if (domainRepository.getUser() != null) {
            currentDomainId = domainRepository.getCurrentDomain().getId();
        }
        ProductRepository productRepository = new ProductRepository(
                new ProductRemoteDataSource(FOrderServiceClient.getInstance()),
                new ProductLocalDataSource(realmApi), currentDomainId);
        NotificationRepository notificationRepository = new NotificationRepository(
                new NotificationRemoteDataSource(FOrderServiceClient.getInstance()));
        ShopRepository shopRepository =
                new ShopRepository(new ShopRemoteDataSource(FOrderServiceClient.getInstance()));

        MainContract.Presenter presenter =
                new MainPresenter(mViewModel, domainRepository, productRepository,
                        notificationRepository, shopRepository);
        mViewModel.setPresenter(presenter);

        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.setViewModel((MainViewModel) mViewModel);

        LinearLayout view = (LinearLayout) findViewById(R.id.layout_contain_item);
        mView = view.getChildAt(TAB_HOME);
        mViewProfile = view.getChildAt(TAB_PROFILE);

        mHandler = new Handler();
        mRunnable = new Runnable() {
            @Override
            public void run() {
                mIsDoubleTapBack = false;
            }
        };
    }

    @Override
    protected void onStart() {
        super.onStart();
        mViewModel.onStart();
    }

    @Override
    protected void onStop() {
        mViewModel.onStop();
        mHandler.removeCallbacks(mRunnable);
        super.onStop();
    }

    @Override
    public void onBackPressed() {
        if (mViewModel.onBackPressed()) {
            return;
        }
        if (mIsDoubleTapBack) {
            super.onBackPressed();
            return;
        }
        mIsDoubleTapBack = true;
        Toast.makeText(this, getString(R.string.please_click_back_again_to_exit),
                Toast.LENGTH_SHORT).show();
        mHandler.postDelayed(mRunnable, DELAY_TIME_TWO_TAP_BACK_BUTTON);
    }

    @Override
    public void onReloadCart() {
        mViewModel.onReloadCart();
    }

    @Override
    public void reloadData() {
        mViewModel.reloadData(mView);
    }

    @Override
    public void onReloadNotification() {
        mViewModel.onReloadNotification();
    }

    @Override
    public void onLoadOrderHistoryPage() {
        mViewModel.onLoadOrderHistoryPage(mViewProfile);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(NetWorkStateEvent event) {
        checkConnection(event.isConnected());
    }

    private void checkConnection(boolean isConnected) {
        if (!isConnected) {
            mDialogManager.dialogwithNoTitleOneButton(R.string.sorry_not_connect_to_internet,
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            refreshConnection();
                        }
                    });
            mDialogManager.show();
        }
    }

    private void refreshConnection() {
        mIsConnected = ConnectivityReceiver.isConnected(this);
        if (!mIsConnected) {
            mDialogManager.dialogwithNoTitleOneButton(R.string.sorry_not_connect_to_internet,
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            refreshConnection();
                        }
                    });
            mDialogManager.show();
        } else {
            mDialogManager.dismiss();
            mViewModel.reloadData(mView);
            mViewModel.onReloadDataMain();
        }
    }
}
