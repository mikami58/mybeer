package com.mikami.bymybeer.utility;

import android.content.Context;
import android.net.Uri;
import android.support.v4.content.FileProvider;

import java.io.File;

public class GenericFileProvider extends FileProvider {

    private static final String PROVIDER_PACKAGE = ".com.mikami.bymybeer.provider";

    public static Uri getUriForFile(Context context, File photo) {
        return FileProvider.getUriForFile(context, getAuthority(context), photo);
    }

    private static String getAuthority(Context context) {
        return context.getApplicationContext().getPackageName() + PROVIDER_PACKAGE;
    }
}
