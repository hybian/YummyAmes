package com.iastate.yummyames.activities;

import android.content.Intent;
import android.os.ResultReceiver;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.iastate.yummyames.R;
import com.iastate.yummyames.connection.ListFoodAdapter;
import com.iastate.yummyames.connection.ListResAdapter;
import com.iastate.yummyames.objects.Customer;
import com.iastate.yummyames.objects.Food;
import com.iastate.yummyames.objects.Restaurant;
import com.iastate.yummyames.singletons.FoodSingleton;
import com.iastate.yummyames.singletons.RestaurantSingleton;

import org.w3c.dom.Text;

import java.util.ArrayList;

import static com.iastate.yummyames.R.id.list_restaurant;

public class RestaurantPage extends AppCompatActivity {

    private Restaurant restaurant;
    private ArrayList<Food> foods;

    private TextView resName, resAddress, resPhone;

    private ListView list_food;
    private ListFoodAdapter foodAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_page);

        restaurant = RestaurantSingleton.getInstance().getSpecificRes();
        foods = FoodSingleton.getInstance().getFoodsByRes(restaurant);

        resName = (TextView) findViewById(R.id.res_name_res_page);
        resAddress = (TextView) findViewById(R.id.res_address_res_page);
        resPhone = (TextView) findViewById(R.id.res_phone_res_page);

        resName.setText(restaurant.getName());
        resAddress.setText(restaurant.getAddress());
        resPhone.setText(restaurant.getPhone());

        list_food = (ListView) findViewById(R.id.list_food);
        foodAdapter = new ListFoodAdapter(this, foods);
        list_food.setAdapter(foodAdapter);
        list_food.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                onFoodClick(position);
            }
        });


    }


    private void onFoodClick(int position)
    {
        FoodSingleton.getInstance().setSpecificFood(position);
        Intent intent = new Intent(RestaurantPage.this, FoodPage.class);
//        Bundle bundle = new Bundle();
//        bundle.putParcelable("RESTAURANT", restaurant);
//        bundle.putParcelable("FOOD", foods.get(position));
//        bundle.putParcelable("CUSTOMER", customer);
//        intent.putExtras(bundle);
        startActivity(intent);
    }
}
