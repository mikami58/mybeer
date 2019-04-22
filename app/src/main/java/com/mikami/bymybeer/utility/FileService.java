package com.mikami.bymybeer.utility;

import android.content.Context;
import android.os.Environment;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.mikami.bymybeer.R;

import java.io.File;

public final class FileService {

    private static final String FILE_DIRECTORY = Environment.getExternalStorageDirectory() + "/.mybeer/";

    private static final String IMAGES_DIRECTORY = FILE_DIRECTORY + "images/";

    private static final String JPG = ".jpg";

    private static final int PLACEHOLDER = R.drawable.ic_launcher_background;

    private FileService() { }

    public static void setImage(Context mContext, ImageView image, String name) {
        RequestOptions requestOptions = RequestOptions.placeholderOf(PLACEHOLDER);
        if (name != null) {
            Glide.with(mContext)
                    .load(getOrCreateImage(name))
                    .apply(requestOptions)
                    .into(image);
        }
    }

    public static String createImageName() {
        return System.currentTimeMillis() + JPG;
    }

    public static File getOrCreateImage(String name) {
        return new File(getImagesFolder(), name);
    }

    public static File getRootFolder() {
        return getFolder(FILE_DIRECTORY);
    }

    private static File getImagesFolder() {
        return getFolder(IMAGES_DIRECTORY);
    }

    private static File getFolder(String directory) {
        File folder = new File(directory);
        if (!folder.exists()) {
            folder.mkdirs();
        }
        return folder;
    }
}
