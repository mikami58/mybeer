package com.mikami.bymybeer.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import com.mikami.bymybeer.R;
import com.mikami.bymybeer.model.BeerModel;
import com.mikami.bymybeer.model.PriceModel;
import com.mikami.bymybeer.utility.DataProvider;
import com.mikami.bymybeer.utility.FileService;

import static com.mikami.bymybeer.utility.Constants.EXTRA_DEFAULT;
import static com.mikami.bymybeer.utility.Constants.EXTRA_NAME;

public class DisplayActivity extends AppCompatActivity {

    private long itemId;

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
        loadElements();
        loadToolbar();
        BeerModel model = extractDataFromExtra();
        setData(model);
        itemId = model.getId();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_display, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_edit:
                modify();
                return true;
            case R.id.action_delete:
                delete();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        setData(DataProvider.getInstance().findById(itemId));
    }

    private void modify() {
        Intent intent = new Intent(this, ModifyActivity.class);
        intent.putExtra(EXTRA_NAME, itemId);
        startActivityForResult(intent, 0);
    }

    private void delete() {
        new AlertDialog.Builder(this)
                .setMessage("Вы действительно хотите удалить запись?")
                .setPositiveButton("Да", (dialog, which) -> {
                    DataProvider.getInstance().deleteAndSave(itemId);
                    this.finish();
                })
                .setNegativeButton("Нет", null)
                .show();
    }

    private void loadElements() {
        setContentView(R.layout.activity_display);

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
    }

    private void loadToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
    }

    private BeerModel extractDataFromExtra() {
        if (getIntent().hasExtra(EXTRA_NAME)) {
            long id = getIntent().getLongExtra(EXTRA_NAME, EXTRA_DEFAULT);
            return DataProvider.getInstance().findById(id);
        } else {
            throw new RuntimeException("Item was not present in intent");
        }
    }

    private void setData(BeerModel model) {
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
