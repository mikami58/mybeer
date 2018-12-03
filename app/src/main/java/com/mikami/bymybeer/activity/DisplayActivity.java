package com.mikami.bymybeer.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.mikami.bymybeer.R;
import com.mikami.bymybeer.model.BeerModel;
import com.mikami.bymybeer.model.PriceModel;
import com.mikami.bymybeer.utility.FileService;

public class DisplayActivity extends AppCompatActivity {

    public static final String TAG = "DisplayActivity";

    private Toolbar toolbar;

    private ImageView mainImage;

    private TextView name;

    private RatingBar averageRating;

    private TextView ratingNumber;

    private TextView smallDesc;

    private TextView priceAndVolume;

    private RatingBar costRating;

    private RatingBar bitternessRating;

    private RatingBar tasteRating;

    private RatingBar saturationRating;

    private RatingBar alcoholRating;

    private TextView note;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);

        toolbar = findViewById(R.id.toolbar);
        mainImage = findViewById(R.id.mainImage);
        name = findViewById(R.id.name);
        averageRating = findViewById(R.id.main_rating);
        ratingNumber = findViewById(R.id.rating_number);
        smallDesc = findViewById(R.id.small_desc);
        priceAndVolume = findViewById(R.id.price_and_volume);
        costRating = findViewById(R.id.cost_rating);
        bitternessRating = findViewById(R.id.bitterness_rating);
        tasteRating = findViewById(R.id.taste_rating);
        saturationRating = findViewById(R.id.saturation_rating);
        alcoholRating = findViewById(R.id.alcohol_rating);
        note = findViewById(R.id.note_content);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        setData();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_display, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    private void setData() {
        if (getIntent().hasExtra("item")) {
            BeerModel model = (BeerModel) getIntent().getSerializableExtra("item");

            FileService.setImage(this, mainImage, model.getImageName());

            name.setText(model.getName());
            name.setText(model.getName());
            ratingNumber.setText(String.valueOf(model.getRating().getAverage()));
            averageRating.setRating(model.getRating().getAverage());
            smallDesc.setText(getString(R.string.small_desc, model.getType(), model.getAlcoholPercentage()));

            PriceModel price = model.getBeerPrice();
            priceAndVolume.setText(getString(
                    R.string.price_and_volume,
                    price.getPrice(),
                    getString(price.getCurrency().getResourceId()),
                    price.getVolume()));

            costRating.setRating(model.getRating().getCost());
            bitternessRating.setRating(model.getRating().getBitterness());
            tasteRating.setRating(model.getRating().getTaste());
            saturationRating.setRating(model.getRating().getSaturation());
            alcoholRating.setRating(model.getRating().getAlcohol());
            note.setText(model.getNote());
        }
    }
}
