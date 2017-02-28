package com.example.duong.android_forder_01.utils;

import android.content.Context;
import android.preference.PreferenceManager;

import com.example.duong.android_forder_01.data.model.Domain;
import com.example.duong.android_forder_01.data.model.User;
import com.google.gson.Gson;

/**
 * Created by Duong on 2/22/2017.
 */
public class SharedPreferencesUtils {
    private static final String PREF_USER = "User";
    private static final String PREF_DOMAIN = "Domain";

    public static void saveUser(Context context, User user) {
        PreferenceManager.getDefaultSharedPreferences(context).edit().putString
            (PREF_USER, new Gson().toJson(user)).apply();
    }

    public static User loadUser(Context context) {
        return new Gson().fromJson(PreferenceManager.getDefaultSharedPreferences(context).getString
            (PREF_USER, null), User.class);
    }

    public static void deleteUser(Context context) {
        PreferenceManager.getDefaultSharedPreferences(context).edit().clear().apply();
    }

    public static void saveCurrentDomain(Context context, Domain domain) {
        PreferenceManager.getDefaultSharedPreferences(context).edit().putString
            (PREF_DOMAIN, new Gson().toJson(domain)).apply();
    }

    public static Domain getCurrentDomain(Context context) {
        return new Gson().fromJson(PreferenceManager.getDefaultSharedPreferences(context).getString
            (PREF_DOMAIN, null), Domain.class);
    }
}
