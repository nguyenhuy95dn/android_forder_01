package com.framgia.forder.data.source.local;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.BitmapFactory;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import com.framgia.forder.R;
import com.framgia.forder.screen.main.MainActivity;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import static com.framgia.forder.utils.Constant.DEFAULT_VALUE;

/**
 * Created by levutantuan on 6/20/17.
 */

public class MyFirebaseMessagingService extends FirebaseMessagingService {

    private static final String PREF_NOTIFICATION_NUMBER = "PREF_NOTIFICATION_NUMBER";
    private static final String MESSAGE = "message";

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        if (remoteMessage.getData().size() > DEFAULT_VALUE) {
            sendNotification(remoteMessage.getData().get(MESSAGE));
        }
        if (remoteMessage.getNotification() != null) {
            sendNotification(remoteMessage.getNotification().getBody());
        }
    }

    private void sendNotification(String messageBody) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, DEFAULT_VALUE, intent,
                PendingIntent.FLAG_UPDATE_CURRENT);
        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder notificationBuilder =
                new NotificationCompat.Builder(this).setShowWhen(false)
                        .setLargeIcon(
                                BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher))
                        .setSmallIcon(R.drawable.ic_notification_device)
                        .setContentText(messageBody)
                        .setAutoCancel(true)
                        .setSound(defaultSoundUri);
        notificationBuilder.setContentIntent(pendingIntent);
        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        SharedPreferences sharedPreferences =
                getSharedPreferences(MainActivity.class.getSimpleName(), Context.MODE_PRIVATE);
        int numberNotification = sharedPreferences.getInt(PREF_NOTIFICATION_NUMBER, DEFAULT_VALUE);
        notificationManager.notify(numberNotification, notificationBuilder.build());
        SharedPreferences.Editor editor = sharedPreferences.edit();
        numberNotification++;
        editor.putInt(PREF_NOTIFICATION_NUMBER, numberNotification);
        editor.apply();
    }
}
