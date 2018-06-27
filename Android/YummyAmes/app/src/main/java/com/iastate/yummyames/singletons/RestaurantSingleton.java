package com.iastate.yummyames.singletons;


import com.iastate.yummyames.objects.Restaurant;

import java.util.ArrayList;


public class RestaurantSingleton {

    private ArrayList<Restaurant> restaurants;
    private int position;

    public static RestaurantSingleton myResSingleton = new RestaurantSingleton();

    private RestaurantSingleton() {}

    public static RestaurantSingleton getInstance()
    {
        return myResSingleton;
    }


    public void setRestaurants(ArrayList<Restaurant> restaurants) { this.restaurants = restaurants; }

    public void setSpecificRes(int position){ this.position = position; }

    public ArrayList<Restaurant> getRestaurants()
    {
        return restaurants;
    }

    public Restaurant getSpecificRes(){
        if (restaurants != null)
            return restaurants.get(position);
        return null;
    }

    public String getResNameById(String id){
        for(int i=0; i<restaurants.size(); i++){
            if(restaurants.get(i).getId().equals(id))
                return restaurants.get(i).getName();
        }
        return null;
    }

}
