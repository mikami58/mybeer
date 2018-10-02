package com.mikami.bymybeer.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.mikami.bymybeer.Domain.Beer.Beer;
import com.mikami.bymybeer.R;

import java.util.ArrayList;

public class BeerListAdapter extends ArrayAdapter<Beer> {

    private Context mContext;

    private int mResource;

    public BeerListAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Beer> objects) {
        super(context, resource, objects);
        mContext = context;
        mResource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        Beer item = getItem(position);

        LayoutInflater inflater = LayoutInflater.from(mContext);
        convertView = inflater.inflate(mResource, parent, false);

        TextView tvName = (TextView) convertView.findViewById(R.id.textView1);
        TextView tvTaste = (TextView) convertView.findViewById(R.id.textView2);

        tvName.setText(item.getName());
        tvTaste.setText(String.valueOf(item.getTaste()));

        return convertView;
    }
}
