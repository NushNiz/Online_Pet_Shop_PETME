package com.example.authenticationapp;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

import androidx.core.app.NotificationCompat;

public class AlarmReciever extends BroadcastReceiver {

    private static final String CHANNEL_ID="SAMPLE_CHANNEL";

    @Override
    public void onReceive(Context context, Intent intent) {

        //get id & message from intent
        int notificationId= intent.getIntExtra("notificationId",0);
        String message = intent.getStringExtra("message");

        //Call Reminder activity when the notification tapped!
        Intent reminderIntent = new Intent(context,Reminder.class);
        PendingIntent contentIntent = PendingIntent.getActivity(
                context,0,reminderIntent,0
        );

        //Notification manager
        NotificationManager notificationManager =
                (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O){
            //for API26 and above
            CharSequence channel_name = "My Notification";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;

            NotificationChannel channel = new NotificationChannel(CHANNEL_ID,channel_name,importance);
            notificationManager.createNotificationChannel(channel);
        }

        //Prepare notification
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context,CHANNEL_ID)
                .setSmallIcon(android.R.drawable.ic_dialog_info)
                .setContentTitle("TITLE")
                .setContentText(message)
                .setContentIntent(contentIntent)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setAutoCancel(true);

        //Notify
        notificationManager.notify(notificationId,builder.build());
    }
}
