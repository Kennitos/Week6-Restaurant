package com.example.kenne.restaurant;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class CategoriesActivity extends AppCompatActivity implements CategoriesRequest.Callback {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);
        CategoriesRequest x = new CategoriesRequest(this);
        x.getCategories(this);

        Toast.makeText(this,"Started",Toast.LENGTH_SHORT).show();

    }

    @Override
    public void gotCategories(ArrayList<String> categories) {
        Toast.makeText(this, categories.get(0), Toast.LENGTH_SHORT).show();
        Toast.makeText(this, categories.get(1), Toast.LENGTH_SHORT).show();

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, categories);
        ListView listView = findViewById(R.id.listview);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String category = (String) adapterView.getItemAtPosition(i);

                Intent intent = new Intent(CategoriesActivity.this, MenuActivity.class);
                intent.putExtra("category",category);
                startActivity(intent);
            }
        });
    }


    @Override
    public void gotCategoriesError(String message) {
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show();
    }
}
