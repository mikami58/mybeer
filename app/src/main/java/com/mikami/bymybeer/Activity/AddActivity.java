package com.mikami.bymybeer.Activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.mikami.bymybeer.R;
import com.mikami.bymybeer.Utility.GenericFileProvider;

import java.io.File;

public class AddActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView imageView;

    private String imagePath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        imageView = findViewById(R.id.mainImage);
        imageView.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.mainImage:
                takePhoto();
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        imageView.setImageBitmap(BitmapFactory.decodeFile(imagePath));
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        takePhoto();
    }

    private void takePhoto() {
        String[] permissions = {
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.CAMERA};

        boolean access = true;
        for (String s : permissions)
            access &= ContextCompat.checkSelfPermission(this.getApplicationContext(), s) == PackageManager.PERMISSION_GRANTED;
        if (access) {

            File basePath = Environment.getExternalStorageDirectory();
            File dir = new File(basePath + "/ByMyBeer/");
            dir.mkdirs();
            File photo = new File(dir, String.valueOf(System.currentTimeMillis()) + ".png");

            Uri photoUri = GenericFileProvider.getUriForFile(this, this.getApplicationContext().getPackageName() + ".com.mikami.bymybeer.provider", photo);

            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            intent.putExtra(android.provider.MediaStore.EXTRA_OUTPUT, photoUri);
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);

            startActivityForResult(intent, 0);

            imagePath = photo.getPath();
            Log.d("TakePhoto", imagePath);
        }
        else {
            ActivityCompat.requestPermissions(AddActivity.this, permissions, 1);
        }
    }
}
