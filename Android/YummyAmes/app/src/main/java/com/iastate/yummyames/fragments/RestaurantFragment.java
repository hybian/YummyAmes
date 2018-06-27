package com.iastate.yummyames.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.iastate.yummyames.R;
import com.iastate.yummyames.activities.RestaurantPage;
import com.iastate.yummyames.connection.ListResAdapter;
import com.iastate.yummyames.objects.Customer;
import com.iastate.yummyames.objects.Food;
import com.iastate.yummyames.objects.Restaurant;
import com.iastate.yummyames.singletons.RestaurantSingleton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class RestaurantFragment extends Fragment{

    private ImageButton refresh;
    private ListView list_restaurant;
    private ListResAdapter resAdapter;

    private static final String RES_URL = "http://proj-309-sd-b-4.cs.iastate.edu/android/restaurant.php";

    public RestaurantFragment() {}


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view =  inflater.inflate(R.layout.fragment_restaurant, container, false);

        refresh = (ImageButton) view.findViewById(R.id.refresh);
        refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                refreshList();
                resAdapter = new ListResAdapter(getContext(), RestaurantSingleton.getInstance().getRestaurants());
                list_restaurant.setAdapter(resAdapter);
            }
        });

        list_restaurant = (ListView) view.findViewById(R.id.list_restaurant);
        resAdapter = new ListResAdapter(getContext(), RestaurantSingleton.getInstance().getRestaurants());
        list_restaurant.setAdapter(resAdapter);
        list_restaurant.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                onResClick(position);
            }
        });

        return view;
    }
    private void refreshList()
    {
        StringRequest stringRequest = new StringRequest(RES_URL,
                new Response.Listener<String>(){
                    @Override
                    public void onResponse(String response)
                    {
                        ArrayList<Restaurant> restaurants = new ArrayList<>();
                        JSONObject jsonObject = null;
                        try
                        {
                            jsonObject = new JSONArray(response).getJSONObject(0);
                            JSONArray restJsonArray = new JSONArray(response);
                            for(int i=0; i<restJsonArray.length(); i++)
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
                        Toast.makeText(getContext(), "error", Toast.LENGTH_LONG).show();
                    }
                });
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(stringRequest);
    }


    private void onResClick(int position)
    {
        RestaurantSingleton.getInstance().setSpecificRes(position);
        Intent intent = new Intent(getActivity(), RestaurantPage.class);
        startActivity(intent);
    }

}
