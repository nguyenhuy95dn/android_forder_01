
package com.example.duong.android_forder_01.service;

import com.google.firebase.FirebaseApp;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

public class FirebaseInstanceIDService extends FirebaseInstanceIdService {
    private String token = "token";

    @Override
    public void onCreate() {
        super.onCreate();
        getToken();
    }

    public String getToken() {
        FirebaseInstanceId instanceID = FirebaseInstanceId.getInstance(
            FirebaseApp.initializeApp(this));
        token = instanceID.getToken();
        return token;
    }

    @Override
    public void onTokenRefresh() {
        getToken();
        //TODO: Send token to Ruby's Server
    }
}
