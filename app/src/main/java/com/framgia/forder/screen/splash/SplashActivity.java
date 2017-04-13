package com.framgia.forder.screen.splash;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import com.framgia.forder.screen.login.LoginActivity;
import com.framgia.forder.utils.navigator.Navigator;

public class SplashActivity extends AppCompatActivity {
    private static final int SECOND_DELAYED = 2000;
    private Handler mHandler;
    private Runnable mRunnable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mHandler = new Handler();
        mRunnable = new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
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
