
package com.example.duong.android_forder_01.service;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v7.app.NotificationCompat;

import com.example.duong.android_forder_01.R;
import com.example.duong.android_forder_01.ui.login.LoginActivity;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import static com.example.duong.android_forder_01.utils.Const.ConstantApi.MESSAGE_CONTENT;

public class FirebaseMessageService extends FirebaseMessagingService {
    private static final String PREF_NOTIFICATION_NUMBER = "PREF_NOTIFICATION_NUMBER";
    private static final String TITLE_NOTIFICATION = "FOder";

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        if (remoteMessage.getData().size() > 0) {
            buildNotification(String.valueOf(remoteMessage.getData().get(MESSAGE_CONTENT)));
        }
        if (remoteMessage.getNotification() != null) {
            buildNotification(remoteMessage.getNotification().getBody());
        }
    }

    private void buildNotification(String message) {
        Intent intent = new Intent(this, LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent,
            PendingIntent.FLAG_UPDATE_CURRENT);
        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder notificationBuilder =
            (NotificationCompat.Builder) new NotificationCompat.Builder(this).setShowWhen(false)
                .setSmallIcon(R.drawable.ic_notifications_none_black)
                .setContentTitle(TITLE_NOTIFICATION)
                .setContentText(message)
                .setAutoCancel(true)
                .setSound(defaultSoundUri);
        notificationBuilder.setContentIntent(pendingIntent);
        NotificationManager notificationManager =
            (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        SharedPreferences sharedPreferences =
            getSharedPreferences(LoginActivity.class.getSimpleName(), Context.MODE_PRIVATE);
        int notificationNumber = sharedPreferences.getInt(PREF_NOTIFICATION_NUMBER, 0);
        notificationManager.notify(notificationNumber, notificationBuilder.build());
        SharedPreferences.Editor editor = sharedPreferences.edit();
        notificationNumber++;
        editor.putInt(PREF_NOTIFICATION_NUMBER, notificationNumber);
        editor.commit();
    }
}
