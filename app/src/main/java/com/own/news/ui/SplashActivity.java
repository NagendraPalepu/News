package com.own.news.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.own.news.R;

public class SplashActivity extends Activity {

    Handler handler = new Handler ();

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.splash_screen);
        handler.postDelayed (runnable, 3000);
    }


    private Runnable runnable = new Runnable () {
        @Override
        public void run () {
            Intent intent = new Intent (SplashActivity.this, NewMainActivity.class);
            startActivity (intent);
            finish ();
        }
    };

    @Override
    public void onBackPressed () {
        super.onBackPressed ();
        handler.removeCallbacks (runnable);
    }
}
