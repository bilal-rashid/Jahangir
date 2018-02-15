package com.example.jahangir.driver;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import in.shadowfax.proswipebutton.ProSwipeButton;

public class JobActivity extends AppCompatActivity implements ProSwipeButton.OnSwipeListener {

    ProSwipeButton proSwipeBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job);
        proSwipeBtn = (ProSwipeButton) findViewById(R.id.proswipebutton_main);
        proSwipeBtn.setOnSwipeListener(new ProSwipeButton.OnSwipeListener() {
            @Override
            public void onSwipeConfirm() {
                // user has swiped the btn. Perform your async operation now
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        // task success! show TICK icon in ProSwipeButton
                        proSwipeBtn.showResultIcon(true); // false if task failed
                        JobActivity.this.finish();
                    }
                }, 2000);
            }
        });
    }

    @Override
    public void onBackPressed() {

    }

    @Override
    public void onSwipeConfirm() {
        this.finish();
    }
}
