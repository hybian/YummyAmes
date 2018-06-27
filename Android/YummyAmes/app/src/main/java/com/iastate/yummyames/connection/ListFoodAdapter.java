package com.iastate.yummyames.connection;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.iastate.yummyames.R;
import com.iastate.yummyames.objects.Food;
import java.util.ArrayList;


public class ListFoodAdapter extends ArrayAdapter<Food>{

    private String name, price, type, from;
    private Food[] foodArray;

    public ListFoodAdapter(Context context, ArrayList<Food> foods)
    {
       super(context, R.layout.food_list_item, foods);
        listToArray(foods);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View foodView = inflater.inflate(R.layout.food_list_item, parent, false);

        name = foodArray[position].getName();
        price = foodArray[position].getPrice();
        type = foodArray[position].getType();
        from = foodArray[position].getFrom();

        TextView foodName = (TextView) foodView.findViewById(R.id.food_name);
        TextView foodPrice = (TextView) foodView.findViewById(R.id.food_price);
        TextView foodType = (TextView) foodView.findViewById(R.id.food_type);
        TextView foodFrom = (TextView) foodView.findViewById(R.id.food_from);
        ImageView resIcon = (ImageView) foodView.findViewById(R.id.food_icon);
        foodName.setText(name);
        foodPrice.setText("$" + price);
        foodType.setText(type);
        foodFrom.setText(from);
        resIcon.setImageResource(R.drawable.foo_icon);

        return foodView;
    }

    private void listToArray(ArrayList<Food> foods)
    {
        foodArray = new Food[foods.size()];
        foods.toArray(foodArray);
    }


}
