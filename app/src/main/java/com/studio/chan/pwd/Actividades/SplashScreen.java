package com.studio.chan.pwd.Actividades;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;

import com.studio.chan.pwd.R;
import com.studio.chan.pwd.ScrollingActivity;

public class SplashScreen extends Activity {

    private final int DURACION_SPLASH = 1000;

    @Override
    public void onCreate(Bundle saved){
        super.onCreate(saved);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.splash_screen);

        new Handler().postDelayed(new Runnable(){
            public void run(){
                Intent intent = new Intent(SplashScreen.this, ScrollingActivity.class);
                startActivity(intent);
                finish();
            };
        }, DURACION_SPLASH);
    }

}
