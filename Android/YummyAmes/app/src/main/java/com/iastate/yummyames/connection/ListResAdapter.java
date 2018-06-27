package com.iastate.yummyames.connection;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.iastate.yummyames.R;
import com.iastate.yummyames.objects.Restaurant;

import java.util.ArrayList;

public class ListResAdapter extends ArrayAdapter<Restaurant>{

    private String name, address;
    private Restaurant[] restaurantArray;

    public ListResAdapter(Context context, ArrayList<Restaurant> restaurants)
    {
        super(context, R.layout.restaurant_list_item, restaurants);
        listToArray(restaurants);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View resView = inflater.inflate(R.layout.restaurant_list_item, parent, false);

        name = restaurantArray[position].getName();
        address = restaurantArray[position].getAddress();

        TextView resName = (TextView) resView.findViewById(R.id.restaurant_name);
        TextView resAddress = (TextView) resView.findViewById(R.id.restaurant_address);
        ImageView resIcon = (ImageView) resView.findViewById(R.id.restaurant_icon);
        resName.setText(name);
        resAddress.setText(address);
        resIcon.setImageResource(R.drawable.restaurant_icon);

        return resView;
    }

    private void listToArray(ArrayList<Restaurant> restaurants)
    {
        restaurantArray = new Restaurant[restaurants.size()];
        restaurants.toArray(restaurantArray);
    }


}
