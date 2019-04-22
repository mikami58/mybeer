package com.mikami.bymybeer.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.*;
import com.mikami.bymybeer.R;
import com.mikami.bymybeer.model.BeerModel;
import com.mikami.bymybeer.model.PriceModel;
import com.mikami.bymybeer.model.RatingModel;
import com.mikami.bymybeer.utility.DataProvider;
import com.mikami.bymybeer.utility.FileService;
import com.mikami.bymybeer.utility.GenericFileProvider;

import java.io.File;

public class AddActivity extends AppCompatActivity implements View.OnClickListener {

    private String imageName;

    private ImageView mainImage;

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
        if (view.getId() == R.id.cameraButton)
            takePhoto();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_confirm) {
            save();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        FileService.setImage(this, mainImage, imageName);
    }

    private void save() {
        if (fieldsNotBlank()) {
            DataProvider.getInstance().addAndSave(getModel());
            this.finish();
        } else {
            Toast.makeText(this, "Необходимо заполнить все поля", Toast.LENGTH_LONG).show();
        }
    }

    private void takePhoto() {
        String fileName = FileService.createImageName();
        File photo = FileService.getOrCreateImage(fileName);
        Uri photoUri = GenericFileProvider.getUriForFile(AddActivity.this, photo);

        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(android.provider.MediaStore.EXTRA_OUTPUT, photoUri);
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);

        startActivityForResult(intent, 0);
        imageName = fileName;
    }

    private boolean fieldsNotBlank() {
        return !(TextUtils.isEmpty(title.getText().toString())
                || TextUtils.isEmpty(type.getText().toString())
                || TextUtils.isEmpty(alcoholPercent.getText().toString())
                || TextUtils.isEmpty(price.getText().toString())
                || TextUtils.isEmpty(volume.getText().toString()));
    }

    private BeerModel getModel() {
        BeerModel model = new BeerModel();

        model.setName(title.getText().toString());
        model.setType(type.getText().toString());
        model.setAlcoholPercentage(Float.valueOf(alcoholPercent.getText().toString()));
        model.setImageName(imageName);
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

        return model;
    }

    private void loadElements() {
        setContentView(R.layout.activity_mutable);

        mainImage = findViewById(R.id.mainImage);
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

        ImageButton cameraButton = findViewById(R.id.cameraButton);
        cameraButton.setOnClickListener(this);
    }

    private void loadToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
    }
}
