package com.example.jahangir.driver;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;



public class SplashActivity extends AppCompatActivity {
    private Handler mHandler;
    private Runnable mRunnable = new Runnable() {
        @Override
        public void run() {
            if(isDriverUserLogin(SplashActivity.this)){
                startActivity(SplashActivity.this, MainActivity.class, true);
            }else
                startActivity(SplashActivity.this, LoginActivity.class, true);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        mHandler = new Handler();
        mHandler.postDelayed(mRunnable, 1000);

    }

    @Override
    public void onBackPressed() {
        if (mHandler != null) {
            mHandler.removeCallbacks(mRunnable);
        }
        super.onBackPressed();
    }
    public  void startActivity(Activity context, Class<?> class_, boolean isFinish) {
        Intent intent = new Intent(context, class_);
        context.startActivity(intent);
        if (isFinish)
            context.finish();
    }
    public static boolean isDriverUserLogin(Context context) {
        return PrefUtils.getBoolean(context, Constants.USER_DRIVER_LOGIN, false);
    }

}