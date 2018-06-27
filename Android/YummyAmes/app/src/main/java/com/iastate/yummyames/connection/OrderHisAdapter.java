package com.iastate.yummyames.connection;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.iastate.yummyames.R;
import com.iastate.yummyames.objects.Order;
import com.iastate.yummyames.singletons.RestaurantSingleton;

import java.util.ArrayList;


public class OrderHisAdapter extends ArrayAdapter<Order> {

    private Order[] orderArray;

    public OrderHisAdapter(Context context, ArrayList<Order> orders)
    {
        super(context, R.layout.order_list_item, orders);
        listToArray(orders);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View orderView = inflater.inflate(R.layout.order_list_item, parent, false);

        TextView price = (TextView) orderView.findViewById(R.id.order_price);
        TextView id = (TextView) orderView.findViewById(R.id.order_id);
        TextView resName = (TextView) orderView.findViewById(R.id.res_name);
        TextView situation = (TextView) orderView.findViewById(R.id.situation);

        id.setText("Order Id: " + orderArray[position].getOrderId());
        resName.setText("Restaurant Name: " + RestaurantSingleton.getInstance().getResNameById(orderArray[position].getResId()));
        price.setText("Total Price: $" + orderArray[position].getPrice());
        if(orderArray[position].getSituation().equals("1"))
            situation.setText("Delivered");
        else
            situation.setText("On the way");

        return orderView;
    }


    private void listToArray(ArrayList<Order> orders)
    {
        orderArray = new Order[orders.size()];
        orders.toArray(orderArray);
    }

}




