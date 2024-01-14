// ProductAdapter.java
package com.example.api;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

public class ProductAdapter extends ArrayAdapter<ModelClass> {

    public ProductAdapter(Context context, List<ModelClass> productList) {
        super(context, 0, productList);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ModelClass product = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_product, parent, false);
        }

        TextView titleTextView = convertView.findViewById(R.id.textTitle);
        ImageView imageView = convertView.findViewById(R.id.imageProduct);

        titleTextView.setText(product.getTitle());

        // Glide to load the image from the URL
        Glide.with(getContext())
                .load(product.getImage())
                .into(imageView);

        return convertView;
    }
}
