package com.iastate.yummyames.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.iastate.yummyames.R;
import com.iastate.yummyames.connection.ListFoodAdapter;
import com.iastate.yummyames.connection.OrderHisAdapter;
import com.iastate.yummyames.objects.Order;
import com.iastate.yummyames.singletons.OrderListSingleton;

import java.util.ArrayList;
import java.util.List;

import static com.iastate.yummyames.R.id.list_food;
import static com.iastate.yummyames.R.id.list_order;

public class OrderHistoryActivity extends AppCompatActivity {

    private ArrayList<Order> orders;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_history);


        orders = OrderListSingleton.getInstance().getSpecificOrders();

        ListView list_order = (ListView) findViewById(R.id.list_order);
        OrderHisAdapter orderAdapter = new OrderHisAdapter(this, orders);
        list_order.setAdapter(orderAdapter);
        list_order.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });


    }
}
