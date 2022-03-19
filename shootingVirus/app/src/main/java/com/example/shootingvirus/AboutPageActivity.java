package com.example.shootingvirus;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.TextView;

public class AboutPageActivity extends AppCompatActivity {

    TextView authorTv, iconTv, backgroundTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_page);

        authorTv = findViewById(R.id.author_tv2);
        iconTv = findViewById(R.id.icon_tv);
        backgroundTv = findViewById(R.id.background_tv);

        authorTv.setOnClickListener((view)->{
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/andy12836"));
            startActivity(intent);
        });

        iconTv.setOnClickListener((view)->{
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.flaticon.com/"));
            startActivity(intent);
        });
        backgroundTv.setOnClickListener((view)->{
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/heyletscode/2D-Game-In-Android-Studio/blob/master/Assets/background.png"));
            startActivity(intent);
        });

    }
}