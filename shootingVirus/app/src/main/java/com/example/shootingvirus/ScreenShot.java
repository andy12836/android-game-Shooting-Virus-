package com.example.shootingvirus;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class ScreenShot {

    Bitmap screenShot;
    int x, y, width, height;

    ScreenShot(int screenX, int screenY, Resources res){

        screenShot = BitmapFactory.decodeResource(res, R.drawable.camera);

        width = screenShot.getWidth() / 3;
        height = screenShot.getHeight() / 3;

        x = screenX - width - 50;
        y = 50;

        screenShot = Bitmap.createScaledBitmap(screenShot, width, height, false);
    }
}
