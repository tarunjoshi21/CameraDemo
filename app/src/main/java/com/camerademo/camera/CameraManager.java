package com.camerademo.camera;

import android.content.Context;
import android.content.pm.PackageManager;
import android.hardware.Camera;

/**
 * Created by tarun on 18/1/17.
 */
public class CameraManager {
    private static CameraManager ourInstance = new CameraManager();

    public static CameraManager getInstance() {
        return ourInstance;
    }

    private CameraManager() {
    }

    /** Check if this device has a camera */
    public boolean checkCameraHardware(Context context) {
        if (context.getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA)){
            // this device has a camera
            return true;
        } else {
            // no camera on this device
            return false;
        }
    }

    /** A safe way to get an instance of the Camera object. */
    public static Camera getCameraInstance(int camerdId){
        Camera c = null;
        try {
            c = Camera.open(camerdId); // attempt to get a Camera instance
        }
        catch (Exception e){
            // Camera is not available (in use or does not exist)
        }
        return c; // returns null if camera is unavailable
    }
}
