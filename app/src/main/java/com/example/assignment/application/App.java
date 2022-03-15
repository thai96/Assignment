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
        NotificationChannel chan = new NotificationChannel(channelId,
                channelName, NotificationManager.IMPORTANCE_NONE);
        chan.setLightColor(Color.BLUE);
        chan.setLockscreenVisibility(Notification.VISIBILITY_PRIVATE);
        NotificationManager service =(NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE) ;
        service.createNotificationChannel(chan);
        return channelId;
    }
}
