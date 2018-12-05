package com.example.kenne.restaurant;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MenuActivity extends AppCompatActivity implements  MenuRequest.Callback{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        Intent intent = getIntent();
        String category = intent.getStringExtra("category");
        String url = "https://resto.mprog.nl/menu?category="+category;
//        Log.d("testjson","https://resto.mprog.nl/menu?category="+category);

        MenuRequest x = new MenuRequest(this,url);
        x.getMenu(this);
        Toast.makeText(this,"Started",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void gotMenu(ArrayList<MenuItem> menu) {
        MenuAdapter adapter = new MenuAdapter(this,R.layout.menu_items,menu);
        ListView menuView = findViewById(R.id.menuList);
        menuView.setAdapter(adapter);


        menuView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                String category = (String) adapterView.getItemAtPosition(i);
                MenuItem menu = (MenuItem) adapterView.getItemAtPosition(i);
                Log.d("testjson",' '+menu.getName());


                Intent intent = new Intent(MenuActivity.this, DetailsActivity.class);
                intent.putExtra("menu",menu);
                startActivity(intent);
            }
        });

//        Toast.makeText(this, menu.get(0), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void gotMenuError(String message) {
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show();
    }

}


