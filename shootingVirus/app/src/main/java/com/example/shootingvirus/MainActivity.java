package com.example.shootingvirus;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentContainerView;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    static boolean permissionGranted = false;
    TextView startGame, highScore, settings, about;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startGame = findViewById(R.id.start_game);
        startGame.setOnClickListener((view)->{
            Intent intent = new Intent(getApplicationContext(),GameActivity.class);
            startActivity(intent);
        });

        highScore = findViewById(R.id.high_score);
        highScore.setOnClickListener((view)->{
            Intent intent = new Intent(getApplicationContext(), HighScoreActivity.class);
            startActivity(intent);
        });

        settings = findViewById(R.id.settings);
        settings.setOnClickListener((view)->{
            Intent intent = new Intent(getApplicationContext(), SettingsActivity.class);
            startActivity(intent);
        });

//        Button button = findViewById(R.id.button);
//        button.setOnClickListener((view)->{
//            Photo.createPhoto(getApplicationContext(),null,getResources());
//        });

        about = findViewById(R.id.about);
        about.setOnClickListener((view)->{
            Intent intent = new Intent(this, AboutPageActivity.class);
            startActivity(intent);
        });

        checkIfPermissionGranted();

    }
    void checkIfPermissionGranted(){
        int permissionRead
                = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE);
        int permissionWrite
                = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if(permissionRead != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 0);
        }
        if(permissionWrite != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
        }

    }

}