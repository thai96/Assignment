package com.example.assignment.application;

import android.app.Application;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;

import androidx.annotation.RequiresApi;

public class App extends Application {
    //ck
    public static final String CHANNEL_ID = "com.example.assignment.application";
    public static final String CHANNEL_NAME = "ALARM_SERVICE_CHANNEL_NAME";


    @Override
    public void onCreate() {
        super.onCreate();
        createNotificationChannel();
    }

    private void createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = CHANNEL_NAME;
            String description = "Alarm Service";
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            if(notificationManager != null)
            notificationManager.createNotificationChannel(channel);
        }
    }
}
