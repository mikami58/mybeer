package com.mikami.bymybeer.activity;

import android.Manifest;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;

import com.mikami.bymybeer.R;
import com.mikami.bymybeer.model.BeerModel;
import com.mikami.bymybeer.model.PriceModel;
import com.mikami.bymybeer.model.RatingModel;
import com.mikami.bymybeer.utility.DataProvider;
import com.mikami.bymybeer.utility.FileService;
import com.mikami.bymybeer.utility.PermissionsService;

import java.io.File;

public class AddActivity extends AppCompatActivity implements View.OnClickListener {

    private String imagePath;

    private Toolbar toolbar;

    private ImageView imageView;

    private EditText title;

    private EditText type;

    private EditText alcoholPercent;

    private EditText price;

    private EditText volume;

    private RatingBar costRating;

    private RatingBar bitternessRating;

    private RatingBar tasteRating;

    private RatingBar saturationRating;

    private RatingBar alcoholRating;

    private EditText note;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadElements();
        loadToolbar();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_mutable, menu);
        return true;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.cameraButton:
                takePhoto();
                break;
            case R.id.action_confirm:
                save();
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

    private void save() {
        BeerModel model = new BeerModel();

        model.setName(title.getText().toString());
        model.setType(type.getText().toString());
        model.setAlcoholPercentage(Float.valueOf(alcoholPercent.getText().toString()));
        model.setImageName(imagePath);
        model.setNote(note.getText().toString());

        PriceModel priceModel = new PriceModel();
        priceModel.setPrice(Float.valueOf(price.getText().toString()));
        priceModel.setVolume(Float.valueOf(volume.getText().toString()));
        model.setBeerPrice(priceModel);

        RatingModel ratingModel = new RatingModel();
        ratingModel.setCost(costRating.getRating());
        ratingModel.setBitterness(bitternessRating.getRating());
        ratingModel.setTaste(tasteRating.getRating());
        ratingModel.setSaturation(saturationRating.getRating());
        ratingModel.setAlcohol(alcoholRating.getRating());
        model.setRating(ratingModel);

        DataProvider.getInstance().addAndSave(model);
        this.finish();
    }

    private void takePhoto() {
        String[] permissions = {
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.CAMERA};

        PermissionsService.executeWithPermissions(this, permissions, new Runnable() {
            @Override
            public void run() {

                File photo = FileService.getFile(String.valueOf(System.currentTimeMillis()) + ".png");

                Uri photoUri = FileProvider.getUriForFile(
                        AddActivity.this,
                        AddActivity.this.getApplicationContext().getPackageName() + ".com.mikami.bymybeer.provider", photo);

                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                intent.putExtra(android.provider.MediaStore.EXTRA_OUTPUT, photoUri);
                intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);

                startActivityForResult(intent, 0);

                imagePath = photo.getPath();
                Log.d("TakePhoto", imagePath);
            }
        });
    }

    private void loadElements() {
        setContentView(R.layout.activity_mutable);

        imageView = findViewById(R.id.mainImage);
        title = findViewById(R.id.title);
        type = findViewById(R.id.type);
        alcoholPercent = findViewById(R.id.alcohol_percent);
        price = findViewById(R.id.price);
        volume = findViewById(R.id.volume);
        costRating = findViewById(R.id.cost_rating);
        bitternessRating = findViewById(R.id.bitterness_rating);
        tasteRating = findViewById(R.id.taste_rating);
        saturationRating = findViewById(R.id.saturation_rating);
        alcoholRating = findViewById(R.id.alcohol_rating);
        note = findViewById(R.id.note_content);

        imageView.setOnClickListener(this);
    }

    private void loadToolbar() {
        toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
    }
}
