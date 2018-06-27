package com.iastate.yummyames.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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


public class FoodPage extends AppCompatActivity {

    private static String order_url = "http://proj-309-sd-b-4.cs.iastate.edu/android/order.php";

    private Food food;
    private Restaurant restaurant;

    private Button orderBtn;
    private TextView tv_foodName, tv_foodType, tv_foodPrice, tv_resName, tv_resAddress, tv_resPhone;

    private AlertDialog.Builder builder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_page);

        restaurant = RestaurantSingleton.getInstance().getSpecificRes();
        food = FoodSingleton.getInstance().getSpecificFood();

        tv_foodName = (TextView) findViewById(R.id.food_name_food_page);
        tv_foodType = (TextView) findViewById(R.id.food_type_food_page);
        tv_foodPrice = (TextView) findViewById(R.id.food_price_food_page);
        tv_resName = (TextView) findViewById(R.id.res_name_food_page);
        tv_resAddress = (TextView) findViewById(R.id.res_address_food_page);
        tv_resPhone = (TextView) findViewById(R.id.res_phone_food_page);
        orderBtn = (Button) findViewById(R.id.orderBtn);

        tv_foodName.setText(food.getName());
        tv_foodType.setText(food.getType() + " Style");
        tv_foodPrice.setText("$" + food.getPrice());
        tv_resName.setText(restaurant.getName());
        tv_resAddress.setText(restaurant.getAddress());
        tv_resPhone.setText(restaurant.getPhone());


        orderBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                order();
            }
        });
    }


    private void order()
    {
        if(CustomerSingleton.getInstance().getCustomer() != null) {
            Toast.makeText(this, "Sending Order...", Toast.LENGTH_LONG).show();
            sendOrder();
        }
        Intent intent = new Intent(FoodPage.this, OrderPage.class);
        startActivity(intent);
    }


    private void sendOrder(){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, order_url,
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response)
                    {
                        try{
                            JSONArray jsonArray = new JSONArray(response);
                            JSONObject jsonObject = jsonArray.getJSONObject(0);
                            String code = jsonObject.getString("code");
                            String message = jsonObject.getString("message");
                            builder.setTitle("Server Response...");
                            builder.setMessage(message);
                            displayAlert(code);
                        }catch (JSONException e){
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }
        ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> parms = new HashMap<String, String>();
                parms.put("o_price", food.getPrice());
                parms.put("o_cusname", CustomerSingleton.getInstance().getCustomer().getName());
                parms.put("o_cusphone", CustomerSingleton.getInstance().getCustomer().getPhone());
                parms.put("o_address", CustomerSingleton.getInstance().getCustomer().getAddress());
                parms.put("r_name", restaurant.getName());
                return parms;
            }
        };

        MySingleton.getInstance(FoodPage.this).addToRequestqueue(stringRequest);
    }

    public void displayAlert(final String code){

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                if(code.equals("order_success")){
                    Toast.makeText(getApplicationContext(), code, Toast.LENGTH_SHORT).show();
                }
                else if(code.equals("order_failed")){
                    Toast.makeText(getApplicationContext(), code, Toast.LENGTH_SHORT).show();
                }
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

}
