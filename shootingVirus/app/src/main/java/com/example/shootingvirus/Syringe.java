package com.example.shootingvirus;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

public class Syringe {

    int x, y, width, height;
    Bitmap syringe;

    Syringe(int screenX, int screenY, Resources res){

        syringe = BitmapFactory.decodeResource(res, R.drawable.syringe);

        width = syringe.getWidth();
        height = syringe.getHeight();
        Log.d("logg",String.valueOf(width));
        Log.d("logg",String.valueOf(height));

        width /= 2.7;
        height /= 2.7;
//        width = (int)(width/screenRatioX);
//        height = (int)(height/screenRatioY);

        syringe = Bitmap.createScaledBitmap(syringe, width, height, false);

    }
}
