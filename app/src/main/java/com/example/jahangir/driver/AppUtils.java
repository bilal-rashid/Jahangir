package com.example.jahangir.driver;

import android.content.Context;
import android.hardware.Camera;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Vibrator;


public class AppUtils {
    public static void turnFlashOn(){

        Camera camera;
        Camera.Parameters params;
        camera = Camera.open();
        params = camera.getParameters();
        String myString = "0101010101010101010101010101010101010101010";
        long blinkDelay = 100; //Delay in ms
        for (int i = 0; i < myString.length(); i++) {
            if (myString.charAt(i) == '0') {
                params.setFlashMode(Camera.Parameters.FLASH_MODE_TORCH);
            } else {
                params.setFlashMode(Camera.Parameters.FLASH_MODE_OFF);
            }
            camera.setParameters(params);
            camera.startPreview();
            try {
                Thread.sleep(blinkDelay);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        camera.release();

    }

    public static void playSound(Context context){
        final MediaPlayer mp = MediaPlayer.create(context, R.raw.buzzer);
        mp.start();
        mp.setVolume(100,100);
    }

    public static void vibrate(Context context){
        Vibrator v = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
        // Vibrate for 500 milliseconds
        v.vibrate(3000);
    }

    public static void changeProfile(Context context) {
        AudioManager am;
        am = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);

//For Normal mode
        am.setRingerMode(AudioManager.RINGER_MODE_NORMAL);
        am.setStreamVolume(AudioManager.STREAM_MUSIC, 15, 0);
    }
}
