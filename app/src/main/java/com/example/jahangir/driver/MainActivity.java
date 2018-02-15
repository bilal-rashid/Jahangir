package com.example.jahangir.driver;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button btn,logoutbtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Driver Home Page");
        btn = (Button) findViewById(R.id.toggleButton);
        logoutbtn = (Button) findViewById(R.id.button);
        logoutbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
             logout(MainActivity.this);
             startActivity(MainActivity.this,SplashActivity.class,true);
            }
        });
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(MainActivity.this, JobActivity.class,false);
            }
        });
    }
    public  void startActivity(Activity context, Class<?> class_, boolean isFinish) {
        Intent intent = new Intent(context, class_);
        context.startActivity(intent);
        if (isFinish)
            context.finish();
    }
    public static void logout(Context context){
        PrefUtils.persistBoolean(context,Constants.USER_DRIVER_LOGIN,false);
    }
}
