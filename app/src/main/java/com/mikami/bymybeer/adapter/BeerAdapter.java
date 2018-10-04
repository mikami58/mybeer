package com.mikami.bymybeer.adapter;

import android.graphics.BitmapFactory;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mikami.bymybeer.R;
import com.mikami.bymybeer.model.BeerModel;

import java.util.ArrayList;

public class BeerAdapter extends RecyclerView.Adapter<BeerAdapter.BeerViewHolder> {

    private ArrayList<BeerModel> beerList;

    public BeerAdapter(ArrayList<BeerModel> beerList) {
        this.beerList = beerList;
    }

    public void updateItems(ArrayList<BeerModel> beerList) {
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
        holder.path.setText(model.getImageName());
        holder.image.setImageBitmap(BitmapFactory.decodeFile(Environment.getExternalStorageDirectory() + "/ByMyBeer/" + model.getImageName()));
    }

    @Override
    public int getItemCount() {
        return beerList.size();
    }

    public static final class BeerViewHolder extends RecyclerView.ViewHolder {

        private TextView name;

        private TextView path;

        private ImageView image;

        public BeerViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            path = itemView.findViewById(R.id.path);
            image = itemView.findViewById(R.id.mainImage);
        }
    }
}
