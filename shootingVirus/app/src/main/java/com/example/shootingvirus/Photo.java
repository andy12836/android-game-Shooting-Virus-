package com.example.shootingvirus;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class Photo {
    public static void createPhoto(Context context, Bitmap bitmap, Resources res){


        String fileName = System.currentTimeMillis() + ".jpg";

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q){

            ContentResolver resolver = context.getContentResolver();
            ContentValues values = new ContentValues();
            values.put(MediaStore.MediaColumns.DISPLAY_NAME, fileName);
            values.put(MediaStore.MediaColumns.MIME_TYPE, "image/jpg");


            Uri uri = resolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);

            try {
                OutputStream stream = resolver.openOutputStream(uri);
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
                stream.close();
                Toast.makeText(context, "Screenshot Successful!",Toast.LENGTH_SHORT).show();


            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        else{

            File fileDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
            File file = new File(fileDir, fileName);
            try {
                OutputStream stream = new FileOutputStream(file);
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
                stream.close();
                Toast.makeText(context, "Screenshot Successful!",Toast.LENGTH_SHORT).show();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }
}
