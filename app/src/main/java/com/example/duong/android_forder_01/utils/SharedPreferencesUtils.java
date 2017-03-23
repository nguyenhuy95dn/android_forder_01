package com.example.duong.android_forder_01.utils;

import android.content.Context;
import android.preference.PreferenceManager;

import com.example.duong.android_forder_01.data.model.Domain;
import com.example.duong.android_forder_01.data.model.Orders;
import com.example.duong.android_forder_01.data.model.User;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;

/**
 * Created by Duong on 2/22/2017.
 */
public class SharedPreferencesUtils {
    private static final String PREF_USER = "User";
    private static final String PREF_DOMAIN = "Domain";
    private static final String PREF_ORDER = "Order";

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

    public static void saveOrder(Context context, List<Orders> list) {
        PreferenceManager.getDefaultSharedPreferences(context).edit().putString
            (PREF_ORDER, new Gson().toJson(list)).apply();
    }

    public static List<Orders> getOrder(Context context) {
        TypeToken<List<Orders>> token = new TypeToken<List<Orders>>() {
        };
        return new Gson().fromJson(PreferenceManager.getDefaultSharedPreferences(context).getString
            (PREF_ORDER, null), token.getType());
    }
}
