package com.example.kenne.restaurant;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MenuAdapter extends ArrayAdapter<MenuItem> {

    private ArrayList<MenuItem> menuitems;

    public MenuAdapter(@NonNull Context context, int resource, @NonNull ArrayList<MenuItem> objects) {
        super(context, resource, objects);
        menuitems = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        String name = menuitems.get(position).getName();
        double price = menuitems.get(position).getPrice();
        String img = menuitems.get(position).getImageUrl();

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.menu_items, parent, false);
        }
        TextView name_view = convertView.findViewById(R.id.nameView);
        name_view.setText(name);

        TextView price_view = convertView.findViewById(R.id.priceView);
        String euro = "€ "+String.valueOf(price);
        price_view.setText(euro);

        ImageView image_view = convertView.findViewById(R.id.imageView);
        Log.d("testjson",img);
        Picasso.get().load(img).centerCrop().resize(250, 250).into(image_view);

        return convertView;
    }
}
