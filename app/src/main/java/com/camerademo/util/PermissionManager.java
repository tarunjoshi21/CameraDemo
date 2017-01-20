package com.camerademo.util;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

/**
 * Created by tarun on 20/1/17.
 * This class handles all the permission required by the application.
 */
public class PermissionManager {
    private static PermissionManager ourInstance = new PermissionManager();

    public static PermissionManager getInstance() {
        return ourInstance;
    }

    private PermissionManager() {
    }

    /**
     * Method check if permission has been acquired by the application or not
     *
     * @param permission that you want to be acquired by the application
     * @param context application context
     * @return true if application already has the required permission otherwise false
     */
    public boolean isPermissonAcquired(String permission, Context context) {
        if (ContextCompat.checkSelfPermission(context,
                permission)
                != PackageManager.PERMISSION_GRANTED) {
            return false;
        }
        return true;
    }

    /**
     * This method request a particular to be acquired by an application
     * @param activity calling activity
     * @param permissions that you want to be acquired by the application
     * @param permissionRequestCode request code for permission
     */
    public void requestPermission(Activity activity, String[] permissions, int permissionRequestCode) {
        ActivityCompat.requestPermissions(activity, permissions, permissionRequestCode);

    }
}
