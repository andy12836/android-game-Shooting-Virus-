package com.example.shootingvirus;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Bundle;
import android.widget.Button;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        NotificationChannel channel = new NotificationChannel("channel1","1", NotificationManager.IMPORTANCE_DEFAULT);
        NotificationManager manager = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
        manager.createNotificationChannel(channel);

        Button notifyButton = findViewById(R.id.notify_button);
        notifyButton.setOnClickListener((view)->{
            manager.notify(1, createNotification());
        });

    }

    Notification createNotification(){
        return new Notification.Builder(this, "channel1")
                .setContentText("Let's play")
                .setContentTitle("Virus Shooting")
                .setWhen(System.currentTimeMillis())
                .setSmallIcon(R.mipmap.ic_launcher).build();
    }
}