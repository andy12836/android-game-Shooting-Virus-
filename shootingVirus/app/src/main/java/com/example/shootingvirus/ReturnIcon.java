package com.example.shootingvirus;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class ReturnIcon {
    int x, y, width, height;
    Bitmap returnIcon;
    ReturnIcon(int screenX, int screenY, Resources res){
        returnIcon = BitmapFactory.decodeResource(res, R.drawable.back);

        width = (int)(returnIcon.getWidth() / 4);
        height = (int)(returnIcon.getHeight() / 4);

        returnIcon = Bitmap.createScaledBitmap(returnIcon, width, height, false);

    }
}
