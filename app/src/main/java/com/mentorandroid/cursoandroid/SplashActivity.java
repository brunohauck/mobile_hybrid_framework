package com.mentorandroid.cursoandroid;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import java.util.Timer;
import java.util.TimerTask;

public class SplashActivity extends AppCompatActivity {

    public int currentimageindex=0;
    Timer timer;
    TimerTask task;
    ImageView slidingimage;

    private int[] IMAGE_IDS = {
            R.drawable.splash0, R.drawable.splash1, R.drawable.splash2,
            R.drawable.splash3
    };

    private Thread splashTread;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        getSupportActionBar().hide();

        final Handler mHandler = new Handler();

        // Create runnable for posting
        final Runnable mUpdateResults = new Runnable() {
            public void run() {

                AnimateandSlideShow();

            }
        };
        int delay = 500;
        int period = 1000;
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            public void run() {
                mHandler.post(mUpdateResults);
            }

        }, delay,period);

        final SplashActivity sPlashScreen = this;
        splashTread = new Thread() {
            @Override
            public void run() {
                try {
                    synchronized(this){
                        wait( 2 * 2000 );
                    }
                } catch(InterruptedException e) {}
                finally {
                    finish();
                    //start a new activity
                    Intent i = new Intent();
                    i.setClass(sPlashScreen, MainActivity.class);
                    startActivity(i);
                }
            }
        };
        splashTread.start();

    }
    private void AnimateandSlideShow() {

        slidingimage = (ImageView)findViewById(R.id.ImageView3_Left);
        slidingimage.setImageResource(IMAGE_IDS[currentimageindex % IMAGE_IDS.length]);
        currentimageindex++;

    }
}
