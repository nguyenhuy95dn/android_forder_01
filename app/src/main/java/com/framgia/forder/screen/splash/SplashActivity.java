package com.framgia.forder.screen.splash;

import android.content.Intent;
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

public class SplashActivity extends AppCompatActivity {
    private static final int SECOND_DELAYED = 2000;
    private Handler mHandler;
    private Runnable mRunnable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPrefsApi prefsApi = new SharedPrefsImpl(getApplicationContext());
        final DomainRepository domainRepository =
                new DomainRepository(null, new DomainLocalDataSource(prefsApi, null));
        final UserRepository userRepository =
                new UserRepository(null, new UserLocalDataSource(prefsApi));
        mHandler = new Handler();
        mRunnable = new Runnable() {
            @Override
            public void run() {
                Intent intent;
                if (userRepository.getUser() != null && domainRepository.getDomainId() == 0) {
                    intent = new Intent(SplashActivity.this, ChooseDomainActivity.class);
                } else if (domainRepository.getDomainId() != 0) {
                    intent = new Intent(SplashActivity.this, MainActivity.class);
                } else {
                    intent = new Intent(SplashActivity.this, LoginActivity.class);
                }
                new Navigator(SplashActivity.this).startActivity(intent);
                finish();
            }
        };
        mHandler.postDelayed(mRunnable, SECOND_DELAYED);
    }

    @Override
    protected void onDestroy() {
        mHandler.removeCallbacks(mRunnable);
        super.onDestroy();
    }
}
