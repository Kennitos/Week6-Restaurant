package com.example.kenne.restaurant;

import android.content.Context;
import android.util.Log;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MenuRequest implements Response.Listener<JSONObject>, Response.ErrorListener{

    Context context;
    Callback activity;
    String url;

    public interface Callback {
        void gotMenu(ArrayList<MenuItem> menu);
        void gotMenuError(String message);
    }

    public MenuRequest(Context input_context, String input_url) {
        context = input_context;
        url = input_url;
    }

    @Override
    public void onErrorResponse(VolleyError error) {

    }

    @Override
    public void onResponse(JSONObject response) {
        JSONObject jsonObj = null;
        JSONArray values;
        ArrayList categories = new ArrayList();
        ArrayList names = new ArrayList();
        ArrayList<MenuItem> MenuItems = new ArrayList<>();
        try {
            jsonObj = new JSONObject((response.toString()));
            JSONArray itemsArray = response.getJSONArray("items");
            for (int i = 0; i < itemsArray.length(); i++) {
                JSONObject chooseObject = itemsArray.getJSONObject(i);

                int id = chooseObject.getInt("id");
                String cate = chooseObject.getString("category");
                String description = chooseObject.getString("description");
                int price = chooseObject.getInt("price");
                String img = chooseObject.getString("image_url");
                String name = chooseObject.getString("name");

                MenuItem newMenu = new MenuItem(name, description, img, price, cate);
                MenuItems.add(newMenu);


//                MenuItems.add(cate+description+price+img+name);
//                Log.d("testjson", ' ' + name + ' ' + cate + ' ' + id + ' ' + price + ' ' + img + ' ' + description);
                Log.d("testjson4", newMenu.getName());
            }
            activity.gotMenu(MenuItems);
        } catch (JSONException e) {
            e.printStackTrace();
//        ArrayAdapter adapter = new ArrayAdapter<String>(this, R.id.listview,categories);
        }

    }

    public void getMenu(Callback activity){
        this.activity = activity;
//        String url = "https://resto.mprog.nl/menu"; line 31 already assigned
        RequestQueue queue = Volley.newRequestQueue(context);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(url,null,this,this);
        queue.add(jsonObjectRequest);
    }
}
