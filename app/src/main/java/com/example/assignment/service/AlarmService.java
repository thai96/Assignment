package com.example.assignment.service;

import static com.example.assignment.AlarmBroadcastReceiver.TITLE;
import static com.example.assignment.application.App.CHANNEL_ID;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.IBinder;
import android.os.VibrationEffect;
import android.os.Vibrator;

import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.example.assignment.R;
import com.example.assignment.RingActivity;

public class AlarmService extends Service {
    private MediaPlayer mediaPlayer;
    private Vibrator vibrator;

    public AlarmService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mediaPlayer = MediaPlayer.create(this, R.raw.duck);
        mediaPlayer.setLooping(true);
        vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Intent notificationIntent = new Intent(this, RingActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0,
                notificationIntent, 0);
        String alarmTitle = String.format("%s Alarm", intent.getStringExtra(TITLE));

//        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID)
//                .setContentTitle(alarmTitle)
//                .setContentText("Wake up motherfucker")
//                .setSmallIcon(R.drawable.baseline_alarm_24)
//                .setContentIntent(pendingIntent);
        Notification notification =
                new NotificationCompat.Builder(this, CHANNEL_ID)
                .setContentTitle(alarmTitle)
                .setContentText("Wake up motherfucker")
                .setSmallIcon(R.drawable.baseline_alarm_24)
                        .setPriority(NotificationCompat.PRIORITY_MAX)
                .setContentIntent(pendingIntent).build();

        mediaPlayer.start();
        long[] pattern = {0, 100, 1000};
        vibrator.vibrate(VibrationEffect.createWaveform(pattern, 0));
//        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
//
//// notificationId is a unique int for each notification that you must define
//        notificationManager.notify(1, builder.build());
        startForeground(1, notification);
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mediaPlayer.stop();
        vibrator.cancel();
    }

}