package com.nbucedog.godpaw;

import android.content.Intent;
import android.app.Activity;
import android.os.Bundle;
import android.view.Window;

public class SplashActivity extends Activity {
    private final int SPLASH_DISPLAY_LENGHT = 1500;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        //requestWindowFeature(Window.FEATURE_NO_TITLE);
        new android.os.Handler().postDelayed(new Runnable() {
            public void run() {
                Intent mainIntent = new Intent(SplashActivity.this, MainActivity.class);
                SplashActivity.this.startActivity(mainIntent);
                SplashActivity.this.finish();
            }
        }, SPLASH_DISPLAY_LENGHT);
    }
}
