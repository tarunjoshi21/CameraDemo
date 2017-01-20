package com.camerademo;

import android.Manifest;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.camerademo.camera.CameraManager;
import com.camerademo.camera.CameraPreview;
import com.camerademo.util.PermissionManager;

public class CameraActivity extends AppCompatActivity {
    private Camera mCamera;
    private CameraPreview mPreview;
    private FrameLayout preview;
    private final String TAG = CameraActivity.class.getSimpleName();
    private final int CAMERA_PERMISSION_REQUEST_CODE = 1234;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);
        preview = (FrameLayout) findViewById(R.id.camera_preview);
        //openCamera();
    }

    private void openCamera() {
        CameraManager cameraManager = CameraManager.getInstance();
        if (cameraManager.checkCameraHardware(this)) {
            Toast.makeText(this, "Device contain camera", Toast.LENGTH_SHORT).show();
            mCamera = CameraManager.getCameraInstance(1);

            // Create our Preview view and set it as the content of our activity.
            mPreview = new CameraPreview(this, mCamera);

            preview.addView(mPreview);
        } else {
            Toast.makeText(this, "Device does not contain camera", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG, "OnResume");
        // check permission
        PermissionManager permissionManager = PermissionManager.getInstance();
        if (!permissionManager.isPermissonAcquired(Manifest.permission.CAMERA, this)) {
            permissionManager.requestPermission(this, new String[]{Manifest.permission.CAMERA}, CAMERA_PERMISSION_REQUEST_CODE);
        } else {
            openCamera();
        }

    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(TAG, "OnPause");
        releaseMediaRecorder();       // if you are using MediaRecorder, release it first
        releaseCamera();              // release the camera immediately on pause event
    }

    private void releaseMediaRecorder(){
       /* if (mMediaRecorder != null) {
            mMediaRecorder.reset();   // clear recorder configuration
            mMediaRecorder.release(); // release the recorder object
            mMediaRecorder = null;*/
        if (mCamera != null)
            mCamera.lock();           // lock camera for later use
        //}
    }

    private void releaseCamera(){
        if (mCamera != null){
           // mCamera.unlock();
            mCamera.release();        // release the camera for other applications
            mCamera = null;
        }
        preview.removeView(mPreview);
        mPreview = null;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        Log.i(TAG, "Inside onRequestPermissionsResult");
        switch (requestCode) {
            case CAMERA_PERMISSION_REQUEST_CODE: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.
                    openCamera();

                } else {

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                    Toast.makeText(this, "Camera Permission denied", Toast.LENGTH_SHORT).show();
                }
                return;
            }

            // other 'case' lines to check for other
            // permissions this app might request
        }
    }
}

