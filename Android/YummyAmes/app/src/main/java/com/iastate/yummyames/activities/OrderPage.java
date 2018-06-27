package com.iastate.yummyames.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.iastate.yummyames.R;
import com.iastate.yummyames.connection.MySingleton;
import com.iastate.yummyames.objects.Customer;
import com.iastate.yummyames.objects.Food;
import com.iastate.yummyames.objects.Restaurant;
import com.iastate.yummyames.singletons.CustomerSingleton;
import com.iastate.yummyames.singletons.FoodSingleton;
import com.iastate.yummyames.singletons.RestaurantSingleton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class OrderPage extends AppCompatActivity {

    private Restaurant restaurant;
    private Food food;
    private Customer customer;

    private TextView order_title, customer_name, customer_phone, customer_email, customer_address,
                        food_name, res_name, res_phone, res_address, price;
    private Button continueBtn;

    private boolean resultFlag = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_page);

        order_title = (TextView) findViewById(R.id.order_title);
        customer_name = (TextView) findViewById(R.id.customer_name);
        customer_phone = (TextView) findViewById(R.id.customer_phone);
        customer_email = (TextView) findViewById(R.id.customer_email);
        customer_address = (TextView) findViewById(R.id.customer_address);
        food_name = (TextView) findViewById(R.id.food_name_order_page);
        res_name = (TextView) findViewById(R.id.res_name_order_page);
        res_phone = (TextView) findViewById(R.id.res_phone_order_page);
        res_address = (TextView) findViewById(R.id.res_address_order_page);
        price = (TextView) findViewById(R.id.food_price_order_page);
        continueBtn = (Button) findViewById(R.id.continueBtn);

        restaurant = RestaurantSingleton.getInstance().getSpecificRes();
        food = FoodSingleton.getInstance().getSpecificFood();
        customer = CustomerSingleton.getInstance().getCustomer();

        if(customer != null)
        {
            resultFlag = true;
            order_title.setText("Thank You for Your Order!");
            customer_name.setText("Your Name:  " + customer.getName());
            customer_phone.setText("Your Phone#:  " + customer.getPhone());
            customer_email.setText("Your E-mail:  " + customer.getEmail());
            customer_address.setText("Your Address:  " + customer.getAddress());
            food_name.setText("Food Name:  " + food.getName());
            res_name.setText("Restaurant Name:  " + restaurant.getName());
            res_phone.setText("Restaurant Phone#:  " + restaurant.getPhone());
            res_address.setText("Restaurant Address:  " + restaurant.getAddress());
            price.setText("Price Total: $" + food.getPrice());
        }
        else
        {
            resultFlag = false;
            order_title.setText("Please Login First!");
            customer_name.setText("");
            customer_phone.setText("");
            customer_email.setText("");
            customer_address.setText("");
            food_name.setText("");
            res_name.setText("");
            res_phone.setText("");
            res_address.setText("");
            price.setText("");
        }

        continueBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(resultFlag)
                {
                    Intent intent = new Intent(OrderPage.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }
                else
                {
                    Intent intent = new Intent(OrderPage.this, LoginActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        });



    }


}
