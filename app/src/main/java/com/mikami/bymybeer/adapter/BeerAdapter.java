package com.mikami.bymybeer.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.mikami.bymybeer.R;
import com.mikami.bymybeer.model.BeerModel;
import com.mikami.bymybeer.model.PriceModel;
import com.mikami.bymybeer.utility.FileService;

import java.util.ArrayList;

public class BeerAdapter extends RecyclerView.Adapter<BeerAdapter.BeerViewHolder> {

    private Context mContext;

    private ArrayList<BeerModel> beerList;

    public BeerAdapter(Context mContext, ArrayList<BeerModel> beerList) {
        this.mContext = mContext;
        this.beerList = beerList;
    }

    public void updateItems( ArrayList<BeerModel> beerList) {
        this.beerList = beerList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public BeerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_view_layout, parent, false);
        return new BeerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BeerViewHolder holder, int i) {

        BeerModel model = beerList.get(i);

        holder.name.setText(model.getTitle());
        holder.ratingNumber.setText(String.valueOf(model.getRating().getAverage()));
        holder.ratingBar.setRating(model.getRating().getAverage());
        holder.smallDesc.setText(mContext.getString(R.string.small_desc, model.getType(), model.getAlcoholPercentage()));

        PriceModel price = model.getBeerPrice();
        holder.priceAndVolume.setText(mContext.getString(
                R.string.price_and_volume,
                price.getPrice(),
                mContext.getString(price.getCurrency().getResourceId()),
                price.getVolume()));

        FileService.setImage(mContext, holder.image, model.getImageName());
    }

    @Override
    public int getItemCount() {
        return beerList.size();
    }

    public static final class BeerViewHolder extends RecyclerView.ViewHolder {

        private TextView name;

        private RatingBar ratingBar;

        private TextView ratingNumber;

        private TextView smallDesc;

        private TextView priceAndVolume;

        private ImageView image;

        public BeerViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            ratingBar = itemView.findViewById(R.id.main_rating);
            ratingNumber = itemView.findViewById(R.id.rating_number);
            smallDesc = itemView.findViewById(R.id.small_desc);
            priceAndVolume = itemView.findViewById(R.id.price_and_volume);
            image = itemView.findViewById(R.id.mainImage);
        }
    }
}
