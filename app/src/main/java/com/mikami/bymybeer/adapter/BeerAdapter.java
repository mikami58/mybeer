package com.mikami.bymybeer.adapter;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import com.mikami.bymybeer.R;
import com.mikami.bymybeer.activity.DisplayActivity;
import com.mikami.bymybeer.model.BeerModel;
import com.mikami.bymybeer.model.PriceModel;
import com.mikami.bymybeer.utility.DataProvider;
import com.mikami.bymybeer.utility.FileService;

import static com.mikami.bymybeer.utility.Constants.EXTRA_NAME;

public class BeerAdapter extends RecyclerView.Adapter<BeerAdapter.BeerViewHolder> {

    private Activity parentActivity;

    private DataProvider provider;

    public BeerAdapter(Activity parentActivity, DataProvider provider) {
        this.parentActivity = parentActivity;
        this.provider = provider;
    }

    @NonNull
    @Override
    public BeerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_view_layout, parent, false);
        return new BeerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BeerViewHolder holder, final int i) {

        final BeerModel model = provider.getBeerList().get(i);

        holder.name.setText(model.getName());
        holder.ratingNumber.setText(String.valueOf(model.getRating().getAverage()));
        holder.ratingBar.setRating(model.getRating().getAverage());
        holder.smallDesc.setText(parentActivity.getString(R.string.small_desc, model.getType(), model.getAlcoholPercentage()));

        PriceModel price = model.getBeerPrice();
        holder.priceAndVolume.setText(parentActivity.getString(
                R.string.price_and_volume,
                price.getPrice(),
                parentActivity.getString(price.getCurrency().getResourceId()),
                price.getVolume()));

        FileService.setImage(parentActivity, holder.image, model.getImageName());

        holder.parentLayout.setOnClickListener(v -> {
            Intent intent = new Intent(parentActivity, DisplayActivity.class);
            intent.putExtra(EXTRA_NAME, model.getId());
            parentActivity.startActivityForResult(intent, 0);
        });
    }

    @Override
    public int getItemCount() {
        return provider.getBeerList().size();
    }

    public static final class BeerViewHolder extends RecyclerView.ViewHolder {

        private ConstraintLayout parentLayout;

        private TextView name;

        private RatingBar ratingBar;

        private TextView ratingNumber;

        private TextView smallDesc;

        private TextView priceAndVolume;

        private ImageView image;

        public BeerViewHolder(@NonNull View itemView) {
            super(itemView);
            parentLayout = itemView.findViewById(R.id.parent_layout);
            name = itemView.findViewById(R.id.name);
            ratingBar = itemView.findViewById(R.id.main_rating);
            ratingNumber = itemView.findViewById(R.id.rating_number);
            smallDesc = itemView.findViewById(R.id.small_desc);
            priceAndVolume = itemView.findViewById(R.id.price_and_volume);
            image = itemView.findViewById(R.id.mainImage);
        }
    }
}
