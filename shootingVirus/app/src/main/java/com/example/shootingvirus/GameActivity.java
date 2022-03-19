package com.example.shootingvirus;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Point;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.WindowManager;

public class GameActivity extends AppCompatActivity implements GameView.ReturnActivity {

    private GameView gameView;
    int playTime = 30;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        playTime = Integer.parseInt(sharedPreferences.getString("countdown","30"));
        GameView.setCountDown(playTime);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN
                ,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        Point point = new Point();
        getWindowManager().getDefaultDisplay().getSize(point);


        gameView = new GameView(this, point.x, point.y, this, this);
        setContentView(gameView);

    }

    @Override
    protected void onPause() {
        super.onPause();
        gameView.pause();

    }

    @Override
    protected void onResume() {
        super.onResume();
        gameView.resume();
    }

    @Override
    public void returnActivity() {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
    }
}