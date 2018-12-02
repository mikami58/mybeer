package com.mikami.bymybeer.utility;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

public final class PermissionsService {

    private PermissionsService() { }

    public static void executeWithPermissions(Activity activity, String[] permissions, Runnable r) {
        boolean access;

        do {
            access = true;
            for (String s : permissions) {
                access &= ContextCompat.checkSelfPermission(activity.getApplicationContext(), s)
                        == PackageManager.PERMISSION_GRANTED;
            }
            if (!access) ActivityCompat.requestPermissions(activity, permissions, 1);
        } while (!access);

        r.run();
    }

}
