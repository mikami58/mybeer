package com.mikami.bymybeer.Adapter;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.mikami.bymybeer.Domain.Beer.Beer;
import com.mikami.bymybeer.R;

import java.util.List;

public class BeerGridAdapter extends ArrayAdapter<Beer> {

    private Context mContext;

    private int mResource;

    public BeerGridAdapter(@NonNull Context context, int resource, @NonNull List<Beer> objects) {
        super(context, resource, objects);
        mContext = context;
        mResource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View row = convertView;
        ViewHolder holder;

        if (row == null) {
            LayoutInflater inflater = ((Activity) mContext).getLayoutInflater();
            row = inflater.inflate(mResource, parent, false);
            holder = new ViewHolder();
            holder.name = (TextView) row.findViewById(R.id.general_info);
            holder.path = (TextView) row.findViewById(R.id.path);
            holder.image = (ImageView) row.findViewById(R.id.mainImage);
            row.setTag(holder);
        } else {
            holder = (ViewHolder) row.getTag();
        }

        Beer item = getItem(position);
        holder.name.setText(item.getName());
        holder.path.setText(item.getPhoto().getPath());
        return row;
    }

    static class ViewHolder {
        TextView name;
        TextView path;
        ImageView image;
    }
}
