package com.example.shootingvirus;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BlendMode;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.Build;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.MotionEvent;
import android.view.PixelCopy;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.Lifecycle;

import com.example.shootingvirus.database.Database;
import com.example.shootingvirus.database.ScoreEntry;

import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import static com.example.shootingvirus.Virus.virusInterval;

public class GameView extends SurfaceView implements Runnable, PixelCopy.OnPixelCopyFinishedListener {

    private Thread thread;
    private Paint paint;
    private Background background;
    private int screenX, screenY, syringeTopPosition, score = 0, playTime;
    private COLUMN syringeTouched = null;
    private boolean isPlaying = true, isVirusHit = false, shouldVirusMove = false, isTiming = false, shouldTiming = false, isEnded = false;
    private LinkedList<Virus> viruses;
    private LinkedList<Virus> virusesLinkedList;
    private Syringe syringe1, syringe2, syringe3;
    public final int[] ROW;
    Random random;
    private Timer timer;
    public static float countDown = 3.000f; // 30seconds = 30000 milliseconds
    private DecimalFormat decimalFormat;
    private ReturnIcon returnIcon;
    private ReturnActivity returnActivity;
    private ScreenShot screenShot;
    private Activity activity;
    final String[] PERMISSIONS ={
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };

    public GameView(Context context, int screenX, int screenY, ReturnActivity returnActivity, Activity activity) {
        super(context);
        this.screenX = screenX;
        this.screenY = screenY;
        this.returnActivity = returnActivity;
        this.activity = activity;
        isEnded = false;

        random = new Random();
        timer = new Timer();
        decimalFormat = new DecimalFormat("#0.00");

        playTime = (int) countDown;

        paint = new Paint();
        paint.setColor(Color.BLACK);
        paint.setTextSize(128);
        background = new Background(screenX, screenY, getResources());
        screenShot = new ScreenShot(screenX, screenY, getResources());

        syringe1 = new Syringe(screenX, screenY, getResources());
        syringe2 = new Syringe(screenX, screenY, getResources());
        syringe3 = new Syringe(screenX, screenY, getResources());

        syringe1.x = (screenX - 3*syringe2.width - virusInterval)/2;
        syringe2.x = (screenX - syringe2.width)/2;
        syringe3.x = (screenX + syringe3.width + virusInterval)/2;

        syringeTopPosition = screenY - syringe1.height-50;

        returnIcon = new ReturnIcon(screenX, screenY, getResources());
        returnIcon.x = screenX/2-200;
        returnIcon.y = screenY/2-100;

        new Virus(screenX, screenY, getResources());
        ROW = new int[6];
        ROW[0] = syringeTopPosition - Virus.height - 100;
        ROW[1] = syringeTopPosition - 2*Virus.height - 150;
        ROW[2] = syringeTopPosition - 3*Virus.height - 200;
        ROW[3] = syringeTopPosition - 4*Virus.height - 250;
        ROW[4] = syringeTopPosition - 5*Virus.height - 300;
        ROW[5] = syringeTopPosition - 6*Virus.height - 350;

        viruses = new LinkedList<>();
        for(int i=0; i<5; i++) {
            Virus virus = createVirus(i);
            viruses.addLast(virus);
        }


    }

    private Virus createVirus(int positionY){
        Virus virus = new Virus(screenX, screenY, getResources());
        int j = random.nextInt(3);
        switch (j){
            case 0:
                virus.x = syringe1.x;
                virus.column = COLUMN.LEFT;
                break;
            case 1:
                virus.x = syringe2.x;
                virus.column = COLUMN.MIDDLE;
                break;
            case 2:
                virus.x = syringe3.x;
                virus.column = COLUMN.RIGHT;
                break;
        }
        Log.d("logdd",String.valueOf(ROW[positionY]));
        virus.y = ROW[positionY];
        return virus;
    }

    @RequiresApi(api = Build.VERSION_CODES.Q)
    @Override
    public void run() {
        while(isPlaying){
            update();
            draw();
            sleep();
        }
    }

    private void update(){

        if(isPlaying){

            if(!isTiming && shouldTiming){
//                timerThread.run();
                timer.schedule(timerTask,0,25);
                isTiming = true;
            }

            if(countDown <= 0 ){
                timer.cancel();
                isTiming = false;
                isPlaying = false;
                shouldTiming = false;
                isEnded = true;
                saveScoreToDb(score, playTime);
            }

            if(isVirusHit){

                viruses.poll();
                viruses.add(createVirus(5));
                score ++;

                isVirusHit = false;
                shouldVirusMove = true;

            }

        }

    }


    @RequiresApi(api = Build.VERSION_CODES.Q)
    private void draw(){

        if(getHolder().getSurface().isValid()){

            Canvas canvas = getHolder().lockCanvas();

            //draw background
            canvas.drawBitmap(background.background, background.x, background.y, paint);

            //draw syringe
            canvas.drawBitmap(syringe1.syringe, syringe1.x, syringeTopPosition, paint);
            canvas.drawBitmap(syringe2.syringe, syringe2.x, syringeTopPosition, paint);
            canvas.drawBitmap(syringe3.syringe, syringe3.x, syringeTopPosition, paint);


//            canvas.drawText(String.valueOf(score), screenX/2f - 32, 130, paint);
            canvas.drawText(String.valueOf(decimalFormat.format(countDown)),screenX/2f - 100, 130, paint);

            //draw virus
            if(shouldVirusMove){
//                for(Virus virus : viruses){
//                    virus.y += 60;
//                    canvas.drawBitmap(virus.virus, virus.x, virus.y, paint);
//                }
                for(int i=0; i<viruses.size(); i++){

                    Virus virus = viruses.get(i);
//                    Log.d("logdd","virus height: "+ virus.y);
//                    Log.d("logdd", "ROW["+ i +"]: "+ROW[i]);
                    virus.y += 70;

                    if(virus.y > ROW[i]){
                        virus.y = ROW[i];
//                        shouldVirusMove = false;
                    }
                    canvas.drawBitmap(virus.virus, virus.x, virus.y, paint);

                }
            }else{
                for(Virus virus : viruses){
                    canvas.drawBitmap(virus.virus, virus.x, virus.y, paint);
                }
            }

            if(isEnded){
//                canvas.drawColor(Color.BLACK, BlendMode.COLOR);
                canvas.drawText("Score: "+score, screenX/2-220, screenY/2-100, paint);
                canvas.drawBitmap(returnIcon.returnIcon, returnIcon.x, returnIcon.y, paint);
                canvas.drawBitmap(screenShot.screenShot, screenShot.x, screenShot.y, paint);
            }

            getHolder().unlockCanvasAndPost(canvas);
        }
    }

    private void sleep() {
        try {
            Thread.sleep(7);
//            Log.d("logdd","time: "+ new Timestamp(System.currentTimeMillis()).toString());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        if(isPlaying){
            switch (event.getAction()){
                case MotionEvent.ACTION_DOWN:
                    if(event.getY() >= syringeTopPosition){
                        if(event.getX() < syringe1.x + syringe1.width + virusInterval/2){
                            //////////////////////////////////////////////
                            shouldTiming = true;
                            checkIsVirusHit(COLUMN.LEFT);
//                        Log.d("logdd","touched left");
                            return true;
                        }
                        if(event.getX() < syringe2.x + syringe2.width + virusInterval/2){
                            shouldTiming = true;
                            checkIsVirusHit(COLUMN.MIDDLE);
//                        Log.d("logdd","touched middle");
                            return true;
                        }

                        shouldTiming = true;
                        checkIsVirusHit(COLUMN.RIGHT);
//                    Log.d("logdd","touched right");
                        return true;
                    }
                case MotionEvent.ACTION_UP:
                    syringeTouched = null;
                    return true;

            }

        }

        if(!isPlaying && isEnded){
            switch (event.getAction()){
                case MotionEvent.ACTION_DOWN:
                    if(event.getX() > returnIcon.x && event.getX() < returnIcon.x + returnIcon.width){
                        if(event.getY() > returnIcon.y && event.getY() < returnIcon.y + returnIcon.height){
                            returnActivity.returnActivity();
                        }
                    }

                    if(event.getX() > screenShot.x && event.getX() < screenShot.x + screenShot.width){
                        if(event.getY() > screenShot.y && event.getY() < screenShot.y + screenShot.height){
                            if(checkIfPermissionGranted()){
                                Photo.createPhoto(getContext(), getScreenShot(), getResources());
                            }
                        }
                    }
                    break;
            }
        }

        return true;

    }

    private Bitmap getScreenShot(){

        SurfaceView surfaceView = this;
        Bitmap bitmap = Bitmap.createBitmap(screenX, screenY, Bitmap.Config.RGB_565);
        try {
            PixelCopy.request(surfaceView, bitmap, this::onPixelCopyFinished, this.getHandler());
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }

        return bitmap;
    }


    @Override
    public void onPixelCopyFinished(int result) {
        if (result != PixelCopy.SUCCESS) {
            Log.e("err", "errMsg");
            return;
        }
    }

    private void checkIsVirusHit(COLUMN column){
        if(column == viruses.peek().column){
            isVirusHit = true;
        }
    }

    public void resume(){
        isPlaying = true;
        //create a new thread and input a runnable object(this)
        thread = new Thread(this);
        thread.start();
    }

    public void pause(){
        try {
            isPlaying = false;
            thread.join();
            isTiming = false;
            shouldTiming = false;
            timer.cancel();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    final TimerTask timerTask = new TimerTask() {
        @Override
        public void run() {
            countDown -= 0.025;
        }
    };

    interface ReturnActivity{
        public void returnActivity();
    }

    public static void setCountDown(float countDown) {
        GameView.countDown = countDown;
    }

    void saveScoreToDb(int score, int playTime){

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("mm/dd hh:mm");
        Date date = new Date();

        ScoreEntry scoreEntry = new ScoreEntry(score, playTime, simpleDateFormat.format(date).toString());

        AppExecutors.getInstance().diskIO().execute(() -> {
            Database.getInstance(getContext()).Dao().insertScore(scoreEntry);
        });

    }

    boolean checkIfPermissionGranted(){

        for(String permission : PERMISSIONS){
            if(ActivityCompat.checkSelfPermission(getContext(), permission) != PackageManager.PERMISSION_GRANTED){
                ActivityCompat.requestPermissions(activity, new String[]{permission}, 0);
                Toast.makeText(getContext(),"Storage access permissions needed", Toast.LENGTH_SHORT).show();
                return false;
            }
        }

        return true;
    }

}
