package com.bylarobotics.truelight1;

import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.SurfaceTexture;
import android.hardware.Camera;
import android.widget.Toast;

import java.io.IOException;

import static android.widget.Toast.LENGTH_LONG;

/**Singleton Class to camera flash control
 * @author Ilya Evstafev
 * @version 1.1 */
public class Headlight {
    public static final Headlight flashLamp = new Headlight();
    private Camera camera;
    private Camera.Parameters parameters;


    /** Check if this device has a camera
     * @param context */
    public static boolean checkCameraExist (Context context) {
        if (context.getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA)){
            Toast.makeText(context, "Camera exist", LENGTH_LONG).show();
            return true; // this device has a camera

        } else {
            return false; // no camera on this device
        }
    }

    /**Check flash modes of this device*/

    /** Get an instance of the Camera object. */
    private Camera getCameraInstance(){
        Camera c = null;
        try {
            c = Camera.open(0); // attempt to get a Camera instance
        }
        catch (Exception e){ // Camera is not available (in use or does not exist)
            e.toString();
        }
        return c; // returns null if camera is unavailable
    }

    /**Set Light ON */
    public void turnLightOn() {
        try {

                camera = getCameraInstance();
                parameters = camera.getParameters();
                parameters.setFlashMode(Camera.Parameters.FLASH_MODE_TORCH);
                camera.setParameters(parameters);
                camera.startPreview();
                camera.setPreviewTexture(new SurfaceTexture( 0));
        } catch (IOException e) {
            e.toString();
        }
    }

    /** Set Light Off release camera*/
    public void turnLightOff() {
            try {
                camera.release();
            } catch (RuntimeException e) {
                e.toString();
            }
        }


}
