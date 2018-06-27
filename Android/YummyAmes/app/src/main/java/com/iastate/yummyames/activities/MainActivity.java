package com.iastate.yummyames.activities;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.iastate.yummyames.R;
import com.iastate.yummyames.fragments.HomeFragment;
import com.iastate.yummyames.fragments.NearFragment;
import com.iastate.yummyames.fragments.RestaurantFragment;
import com.iastate.yummyames.fragments.UserFragment;
import com.iastate.yummyames.objects.Food;
import com.iastate.yummyames.objects.Restaurant;
import com.iastate.yummyames.singletons.FoodSingleton;
import com.iastate.yummyames.singletons.RestaurantSingleton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    private static final String RES_URL = "http://proj-309-sd-b-4.cs.iastate.edu/android/restaurant.php";
    private static final String FOOD_URL = "http://proj-309-sd-b-4.cs.iastate.edu/android/food.php";

    private HomeFragment home;
    private NearFragment near;
    private RestaurantFragment restaurant;
    private UserFragment user;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction transaction = fragmentManager.beginTransaction();

            switch (item.getItemId()) {
                case R.id.navigation_home:
                    transaction.replace(R.id.content, home).commit();
                    return true;
                case R.id.navigation_restaurant:
                    transaction.replace(R.id.content, restaurant).commit();
                    return true;
                case R.id.navigation_near:
                    transaction.replace(R.id.content, near).commit();
                    return true;
                case R.id.navigation_user:
                    transaction.replace(R.id.content, user).commit();
                    return true;
            }
            return false;
        }

    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        home = new HomeFragment();
        near = new NearFragment();
        restaurant = new RestaurantFragment();
        user = new UserFragment();

        getRestaurants();
        getFoods();


        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.content, home).commit();

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }



    private void getRestaurants()
    {
        StringRequest stringRequest = new StringRequest(RES_URL,
                new Response.Listener<String>(){
                    ArrayList<Restaurant> restaurants = new ArrayList<>();
                    @Override
                    public void onResponse(String response)
                    {
                        JSONObject jsonObject = null;
                        try
                        {
                            jsonObject = new JSONArray(response).getJSONObject(0);
                            JSONArray restJsonArray = new JSONArray(response);
                            int size = restJsonArray.length();
                            for(int i=0; i<size; i++)
                            {
                                JSONObject jo = restJsonArray.getJSONObject(i);
                                String id = jo.getString("r_id");
                                String name = jo.getString("r_name");
                                String address = jo.getString("r_address");
                                String phone = jo.getString("r_phone");
                                String email = jo.getString("r_email");
                                restaurants.add(new Restaurant(id, name, address, phone, email));
                            }
                            RestaurantSingleton.getInstance().setRestaurants(restaurants);
                        }catch (JSONException e){
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error){
                        Toast.makeText(getApplicationContext(), "Check your internet connection", Toast.LENGTH_LONG).show();
                    }
                });
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(stringRequest);
    }

    private void getFoods()
    {
        StringRequest stringRequest = new StringRequest(FOOD_URL,
                new Response.Listener<String>(){
                    ArrayList<Food> foods = new ArrayList<>();
                    @Override
                    public void onResponse(String response)
                    {
                        JSONObject jsonObject = null;
                        try
                        {
                            jsonObject = new JSONArray(response).getJSONObject(0);
                            JSONArray foodJsonArray = new JSONArray(response);
                            foods = new ArrayList<Food>();

                            for(int i=0; i<foodJsonArray.length(); i++)
                            {
                                JSONObject jo = foodJsonArray.getJSONObject(i);
                                String name = jo.getString("f_name");
                                String price = jo.getString("f_price");
                                String type = jo.getString("f_type");
                                String from = jo.getString("f_from");
                                foods.add(new Food(name, price, type, from));
                            }
                            FoodSingleton.getInstance().setFoods(foods);
                        }catch (JSONException e){
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error){
                        Toast.makeText(getApplicationContext(), "Check your internet connection", Toast.LENGTH_LONG).show();
                    }
                });
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(stringRequest);
    }


}
