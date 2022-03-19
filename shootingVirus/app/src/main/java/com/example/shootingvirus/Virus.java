package com.example.shootingvirus;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

// icon source: https://www.freepik.com
public class Virus {
    static int virusInterval = 100;
    int x, y;
    static int width, height;
    Bitmap virus;
    COLUMN column;

    Virus(int screenX, int screenY, Resources res){

        virus = BitmapFactory.decodeResource(res, R.drawable.virus);

        width = virus.getWidth();
        height = virus.getHeight();

        width /= 2.7;
        height /= 2.7;
//        width = (int)(width/screenRatioX);
//        height = (int)(height/screenRatioY);

        virus = Bitmap.createScaledBitmap(virus, width, height, false);

    }

}
enum COLUMN{
    LEFT, MIDDLE, RIGHT
}
