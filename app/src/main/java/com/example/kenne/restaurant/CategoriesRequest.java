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




public class CategoriesRequest implements Response.Listener<JSONObject>, Response.ErrorListener{

    Context context;
    Callback activity;

    public interface Callback {
        void gotCategories(ArrayList<String> categories);
        void gotCategoriesError(String message);
    }

    public CategoriesRequest(Context input_context) {
        context = input_context;
    }

    @Override
    public void onErrorResponse(VolleyError error) {

    }

//    @Override
//    public void onResponse(JSONObject response) {
//        JSONObject jsonObj = null;
//        JSONArray values;
//        ArrayList categories = new ArrayList();
//        ArrayList names = new ArrayList();
//        try {
//            jsonObj = new JSONObject((response.toString()));
//            JSONArray itemsArray = response.getJSONArray("items");
//            for (int i = 0; i < itemsArray.length(); i++) {
//                JSONObject chooseObject = itemsArray.getJSONObject(i);
//                int id = chooseObject.getInt("id");
//                String cate = chooseObject.getString("category");
//
//                String description = chooseObject.getString("description");
//                double price = chooseObject.getDouble("price");
//                String img = chooseObject.getString("image_url");
//                String name = chooseObject.getString("name");
//
//                Log.d("testjson", ' ' + name + ' ' + cate + ' ' + id + ' ' + price + ' ' + img + ' ' + description);
//
//            }
//        } catch (JSONException e) {
//            e.printStackTrace();
////        ArrayAdapter adapter = new ArrayAdapter<String>(this, R.id.listview,categories);
//        }
//    }

    @Override
    public void onResponse(JSONObject response) {
        JSONObject jsonObj = null;
        JSONArray values;
        ArrayList categories = new ArrayList();
        ArrayList names = new ArrayList();
        try {
//            jsonObj = new JSONObject((response.toString()));
            JSONArray categoriesArray = response.getJSONArray("categories");
            Log.d("testjson"," "+categoriesArray);
            for (int i = 0; i < categoriesArray.length(); i++) {
                String category = categoriesArray.getString(i);
                categories.add(category);
                Log.d("testjson", ' '+category+categories);

            }
            activity.gotCategories(categories);
        } catch (JSONException e) {
            e.printStackTrace();
//        ArrayAdapter adapter = new ArrayAdapter<String>(this, R.id.listview,categories);
        }


    }

    public void getCategories(Callback activity){
        this.activity = activity;
        String url = "https://resto.mprog.nl/categories";
        RequestQueue queue = Volley.newRequestQueue(context);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(url,null,this,this);
        queue.add(jsonObjectRequest);
    }
}
