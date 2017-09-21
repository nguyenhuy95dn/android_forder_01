package com.framgia.forder.screen.splash;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import com.framgia.forder.data.source.DomainRepository;
import com.framgia.forder.data.source.UserRepository;
import com.framgia.forder.data.source.local.DomainLocalDataSource;
import com.framgia.forder.data.source.local.UserLocalDataSource;
import com.framgia.forder.data.source.local.sharedprf.SharedPrefsApi;
import com.framgia.forder.data.source.local.sharedprf.SharedPrefsImpl;
import com.framgia.forder.screen.chooseDomain.ChooseDomainActivity;
import com.framgia.forder.screen.login.LoginActivity;
import com.framgia.forder.screen.main.MainActivity;
import com.framgia.forder.utils.navigator.Navigator;
import java.util.List;

public class SplashActivity extends AppCompatActivity {
    public final static String PARAMS = "PARAMS";
    private static final int SECOND_DELAYED = 2000;
    private Handler mHandler;
    private Runnable mRunnable;
    private Intent mIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPrefsApi sharedPrefsApi = new SharedPrefsImpl(getApplicationContext());
        final DomainRepository domainRepository =
                new DomainRepository(null, new DomainLocalDataSource(sharedPrefsApi, null));
        final UserRepository userRepository =
                new UserRepository(null, new UserLocalDataSource(sharedPrefsApi));
        final Uri data = getIntent().getData();
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
}
