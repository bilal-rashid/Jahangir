package com.example.jahangir.driver;


import android.Manifest;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v4.app.ActivityCompat;
import android.telephony.SmsManager;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;


public class MyService extends Service implements GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener {
    private GoogleApiClient mLocationClient;
    private LocationListener mListener;


    @Override
    public IBinder onBind(Intent arg0) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {


        //    Notify("", "", 5);
        mLocationClient = new GoogleApiClient.Builder(this).
                addApi(LocationServices.API).addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .build();
        mLocationClient.connect();
        return START_STICKY;
    }

    int i = 0;


    @Override
    public void onDestroy() {

        super.onDestroy();
        WifiManager wifi = (WifiManager) getSystemService(Context.WIFI_SERVICE);
        if (FlagClass.wifi == false) {
            wifi.setWifiEnabled(false);
        }
        LocationServices.FusedLocationApi.removeLocationUpdates(
                mLocationClient, mListener
        );
        // Toast.makeText(this, "Service Destroyed ", Toast.LENGTH_SHORT).show();
        Log.d("murder", "destroyed");

    }

    public void murder() {
        i = 0;

        this.stopSelf();
    }

    public void showtoast(String ms) {
        Toast.makeText(this, "" + ms, Toast.LENGTH_LONG).show();

    }


    @Override
    public void onConnected(Bundle bundle) {

        Toast.makeText(this, "Service Started", Toast.LENGTH_SHORT).show();
        mListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {

//                Toast.makeText(getApplicationContext(), "location", Toast.LENGTH_SHORT).show();
                i++;
                try {
//                    if (i == 2) {
                    String msg = "" + location.getLatitude() + "-" + location.getLongitude() + "-" + location.getSpeed();
                    Log.d("TAAAG",""+msg.getBytes().length);
                    Log.d("TAAAG",""+msg.getBytes());
                    Log.d("TAAAG",""+FlagClass.number);
                    // showtoast(msg);
                    SmsManager smsManager = SmsManager.getDefault();
                    short port = 6735;
                    smsManager.sendDataMessage("" + FlagClass.number, null, port, msg.getBytes(), null, null);
                    // smsManager.sendTextMessage("03345505421", null, "sms message "+msg, null, null);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    // showtoast(msg);
                    LocationServices.FusedLocationApi.removeLocationUpdates(
                            mLocationClient, mListener
                    );
                    murder();
//                    Toast.makeText(getApplicationContext(), "location", Toast.LENGTH_SHORT).show();

//                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    LocationServices.FusedLocationApi.removeLocationUpdates(
                            mLocationClient, mListener
                    );

                }


            }
        };
        LocationRequest request = LocationRequest.create();
        request.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        request.setInterval(2000);
        request.setFastestInterval(1000);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        LocationServices.FusedLocationApi.requestLocationUpdates(
                mLocationClient, request, mListener
        );


    }


    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {

    }
}