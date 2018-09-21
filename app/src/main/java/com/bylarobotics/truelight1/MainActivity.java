package com.bylarobotics.truelight1;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import static android.widget.Toast.LENGTH_LONG;
import static com.bylarobotics.truelight1.Beacon.beacon;
import static com.bylarobotics.truelight1.Headlight.flashLamp;

public class MainActivity extends Activity {

    public boolean isOff = true;
    public boolean isBeaconOn;
    public boolean isLightOn;

    TextView mText1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button mOnButton = findViewById(R.id.on_button);
        Button mOffButton = findViewById(R.id.off_button);
        Button beaconButton = findViewById(R.id.beacon_button);
        mText1 = findViewById(R.id.mTextView);


        /**Camera exist?*/
       if (flashLamp.checkCameraExist(this)) {
           String sToastText = " камер " + Integer.toString(Camera.getNumberOfCameras());
           Toast.makeText(this, sToastText, LENGTH_LONG).show();
       }

        /**If authorisation not granted for camera request for permission*/
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, 50);
        }

        /**
         * Set Light On
         */
        flashLamp.turnLightOn();
        isLightOn = true;
        isOff = false;

        /**Button simple Light On*/
        mOnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isOff) {
                    flashLamp.turnLightOn();
                    isLightOn = true;
                    isOff = false;
                }
                if (isBeaconOn) {
                    beacon.stopBeacon();
                    isBeaconOn = false;
                    flashLamp.turnLightOn();
                    isLightOn = true;
                    isOff = false;
                }
            }
        });

        /**Button Light Off*/
        mOffButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isLightOn) {
                    flashLamp.turnLightOff();
                    isLightOn = false;
                    isOff = true;
                }
                if (isBeaconOn) {
                    beacon.stopBeacon();
                    isBeaconOn = false;
                    isOff = true;
                }
            }
        });

        /**Set beacon ON */
        beaconButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isOff) {
                    beacon.startBeacon();
                    isBeaconOn = true;
                    isOff = false;
                }
                if (isLightOn) {
                    flashLamp.turnLightOff();
                    isLightOn = false;
                    beacon.startBeacon();
                    isBeaconOn = true;
                    isOff = false;
                }
            }
        });

    }

}
