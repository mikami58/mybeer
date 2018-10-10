package com.mikami.bymybeer.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.mikami.bymybeer.R;
import com.mikami.bymybeer.model.BeerModel;
import com.mikami.bymybeer.utility.FileService;

import java.io.File;
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
        holder.path.setText(model.getImageName());

        FileService.setImage(mContext, holder.image, model.getImageName());
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
