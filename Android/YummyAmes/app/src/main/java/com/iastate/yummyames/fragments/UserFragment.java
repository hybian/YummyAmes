package com.iastate.yummyames.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.iastate.yummyames.R;
import com.iastate.yummyames.activities.LoginActivity;
import com.iastate.yummyames.activities.MainActivity;
import com.iastate.yummyames.activities.OrderHistoryActivity;
import com.iastate.yummyames.activities.QRscanActivity;
import com.iastate.yummyames.connection.MySingleton;
import com.iastate.yummyames.objects.Customer;
import com.iastate.yummyames.objects.Order;
import com.iastate.yummyames.objects.Restaurant;
import com.iastate.yummyames.singletons.CustomerSingleton;
import com.iastate.yummyames.singletons.OrderListSingleton;
import com.iastate.yummyames.singletons.RestaurantSingleton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static com.iastate.yummyames.R.id.add;
import static com.iastate.yummyames.R.id.username;


public class UserFragment extends Fragment {

    private static String order_history_url = "http://proj-309-sd-b-4.cs.iastate.edu/android/order_history.php";


    private Button loginBtn, QRbtn;
    private TextView tv_name, tv_address, tv_phone, tv_email, tv_order;
    private ImageView image;
    private int[] hitImage = {R.drawable.user};

    private String name, address, phone, email;


    public UserFragment() {}


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view =  inflater.inflate(R.layout.fragment_user, container, false);

        loginBtn = (Button) view.findViewById(R.id.login);
        QRbtn = (Button) view.findViewById(R.id.QRbtn);
        tv_name = (TextView) view.findViewById(R.id.name);
        tv_address = (TextView) view.findViewById(R.id.address);
        tv_phone = (TextView) view.findViewById(R.id.phone);
        tv_email = (TextView) view.findViewById(R.id.email);
        tv_order = (TextView) view.findViewById(R.id.order);
        image = (ImageView) view.findViewById(R.id.image);
        image.setImageResource(hitImage[0]);

        orderHis();
        setTexts();


        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                startActivityForResult(intent, LoginActivity.RESULT_OK);
            }
        });

        QRbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), QRscanActivity.class);
                startActivity(intent);
            }
        });

        tv_order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(CustomerSingleton.getInstance().getCustomer() != null) {
                    Intent intent = new Intent(getActivity(), OrderHistoryActivity.class);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(getContext(), "Please Login First!", Toast.LENGTH_SHORT).show();
                }

            }
        });


        return view;
    }


    private void setTexts()
    {
        if(CustomerSingleton.getInstance().getCustomer() != null)
        {
            name = CustomerSingleton.getInstance().getCustomer().getName();
            address = CustomerSingleton.getInstance().getCustomer().getAddress();
            phone = CustomerSingleton.getInstance().getCustomer().getPhone();
            email = CustomerSingleton.getInstance().getCustomer().getEmail();

            tv_name.setText("Welcome " + name + "!");
            tv_address.setText("address: " + address);
            tv_phone.setText("phone# : " + phone);
            tv_email.setText("e-mail: " + email);
        }
        else
        {
            tv_name.setText("Please Login First!");
            tv_address.setText("");
            tv_phone.setText("");
            tv_email.setText("");
        }

    }

    private void orderHis()
    {
        StringRequest stringRequest = new StringRequest(order_history_url,
                new Response.Listener<String>(){

                    ArrayList<Order> orders = new ArrayList<>();
                    @Override
                    public void onResponse(String response)
                    {
                        JSONObject jsonObject = null;
                        try
                        {
                            jsonObject = new JSONArray(response).getJSONObject(0);
                            JSONArray orderJsonArray = new JSONArray(response);
                            for(int i=0; i<orderJsonArray.length(); i++)
                            {
                                JSONObject jo = orderJsonArray.getJSONObject(i);
                                String cusName = jo.getString("o_cusname");
                                String cusAddress = jo.getString("o_address");
                                String cusPhone = jo.getString("o_cusphone");
                                String id = jo.getString("o_id");
                                String resId = jo.getString("o_res");
                                String situation = jo.getString("situation");
                                String price = jo.getString("o_price");
                                orders.add(new Order(id, price, cusName, cusPhone, cusAddress, resId, situation));
                            }
                            OrderListSingleton.getInstance().setOrders(orders);
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




}
