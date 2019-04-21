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

    private static final int PLACEHOLDER = R.drawable.ic_launcher_background;

    private FileService() { }

    public static void setImage(Context mContext, ImageView image, String name) {
        RequestOptions requestOptions = RequestOptions.placeholderOf(PLACEHOLDER);
        Glide.with(mContext)
                .load(getFile(name))
                .apply(requestOptions)
                .into(image);
    }

    public static File getFile(String name) {
        return new File(FILE_DIRECTORY, name);
    }


}
