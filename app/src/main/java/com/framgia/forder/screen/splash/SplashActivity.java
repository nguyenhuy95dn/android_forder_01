package com.framgia.forder.screen.splash;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import com.framgia.forder.R;
import com.framgia.forder.data.event.NetWorkStateEvent;
import com.framgia.forder.data.source.DomainRepository;
import com.framgia.forder.data.source.UserRepository;
import com.framgia.forder.data.source.local.DomainLocalDataSource;
import com.framgia.forder.data.source.local.UserLocalDataSource;
import com.framgia.forder.data.source.local.sharedprf.SharedPrefsApi;
import com.framgia.forder.data.source.local.sharedprf.SharedPrefsImpl;
import com.framgia.forder.data.source.remote.api.ConnectivityReceiver;
import com.framgia.forder.screen.BaseActivity;
import com.framgia.forder.screen.chooseDomain.ChooseDomainActivity;
import com.framgia.forder.screen.login.LoginActivity;
import com.framgia.forder.screen.main.MainActivity;
import com.framgia.forder.utils.navigator.Navigator;
import com.framgia.forder.widgets.dialog.DialogManager;
import java.util.List;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class SplashActivity extends BaseActivity {
    public final static String PARAMS = "PARAMS";
    private static final int SECOND_DELAYED = 2000;
    private Handler mHandler;
    private Runnable mRunnable;
    private Intent mIntent;
    private DialogManager mDialogManager;
    private boolean mIsConnected;
    private DomainRepository domainRepository;
    private UserRepository userRepository;
    private Uri data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mDialogManager = new DialogManager(this);
        SharedPrefsApi sharedPrefsApi = new SharedPrefsImpl(getApplicationContext());
        domainRepository =
                new DomainRepository(null, new DomainLocalDataSource(sharedPrefsApi, null));
        userRepository = new UserRepository(null, new UserLocalDataSource(sharedPrefsApi));
        data = getIntent().getData();
        checkConnection();
    }

    public void chooseDomainPage(Uri data) {
        if (data == null) {
            mIntent = new Intent(SplashActivity.this, ChooseDomainActivity.class);
            Bundle bundle = new Bundle();
            bundle.putString(PARAMS, "");
            mIntent.putExtras(bundle);
        } else {
            mIntent = new Intent(SplashActivity.this, LoginActivity.class);
            List<String> params = data.getPathSegments();
            Bundle bundle = new Bundle();
            bundle.putString(PARAMS, params.get(1));
            mIntent.putExtras(bundle);
        }
    }

    public void mainPage(Uri data) {
        if (data == null) {
            mIntent = new Intent(SplashActivity.this, MainActivity.class);
            Bundle bundle = new Bundle();
            bundle.putString(PARAMS, "");
            mIntent.putExtras(bundle);
        } else {
            mIntent = new Intent(SplashActivity.this, MainActivity.class);
            List<String> params = data.getPathSegments();
            Bundle bundle = new Bundle();
            bundle.putString(PARAMS, params.get(1));
            mIntent.putExtras(bundle);
        }
    }

    public void loginPage(Uri data) {
        if (data == null) {
            mIntent = new Intent(SplashActivity.this, LoginActivity.class);
            Bundle bundle = new Bundle();
            bundle.putString(PARAMS, "");
            mIntent.putExtras(bundle);
        } else {
            mIntent = new Intent(SplashActivity.this, LoginActivity.class);
            List<String> params = data.getPathSegments();
            Bundle bundle = new Bundle();
            bundle.putString(PARAMS, params.get(1));
            mIntent.putExtras(bundle);
        }
    }

    @Override
    protected void onDestroy() {
        mHandler.removeCallbacks(mRunnable);
        super.onDestroy();
    }

    private void checkConnection() {
        mIsConnected = ConnectivityReceiver.isConnected(this);
        if (mIsConnected) {
            mHandler = new Handler();
            mRunnable = new Runnable() {
                @Override
                public void run() {

                    if (userRepository.getUser() == null) {
                        loginPage(data);
                    } else if (domainRepository.getCurrentDomain() == null) {
                        chooseDomainPage(data);
                    } else {
                        mainPage(data);
                    }
                    new Navigator(SplashActivity.this).startActivity(mIntent);
                    finish();
                }
            };
            mHandler.postDelayed(mRunnable, SECOND_DELAYED);
        } else {
            mDialogManager.dialogWarning(R.string.sorry_not_connect_to_internet);
            mDialogManager.show();
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(NetWorkStateEvent event) {
        checkConnection();
    }
}
