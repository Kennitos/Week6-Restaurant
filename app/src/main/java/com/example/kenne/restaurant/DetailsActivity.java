package com.example.kenne.restaurant;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class DetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        Intent intent = getIntent();
        MenuItem menu = (MenuItem) getIntent().getSerializableExtra("menu");

        String name = menu.getName();
        String description = menu.getDescription();
        String img = menu.getImageUrl();
        double price = menu.getPrice();

        TextView nameView = findViewById(R.id.detailTileView);
        TextView descriptionView = findViewById(R.id.detailDescriptionView);
        TextView priceView = findViewById(R.id.detailPriceView);

        nameView.setText(name);
        descriptionView.setText(description);
        String euro = "€ "+String.valueOf(price);
        priceView.setText(euro);

        ImageView image_view = findViewById(R.id.imageView2);
        Picasso.get().load(img).centerCrop().resize(800, 500).into(image_view);





    }
}
