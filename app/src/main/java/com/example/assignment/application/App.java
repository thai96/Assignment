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
        createNotificationChannel(CHANNEL_ID, CHANNEL_NAME);
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private String createNotificationChannel(String channelId,String channelName){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            // Create the NotificationChannel
            NotificationChannel mChannel = new NotificationChannel(CHANNEL_ID, CHANNEL_NAME, NotificationManager.IMPORTANCE_DEFAULT);
            mChannel.setDescription("Alarm");
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            notificationManager.createNotificationChannel(mChannel);
        }
        return CHANNEL_ID;
    }
}
