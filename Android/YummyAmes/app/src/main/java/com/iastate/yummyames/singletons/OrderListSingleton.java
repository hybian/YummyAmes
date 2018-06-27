package com.iastate.yummyames.singletons;


import com.iastate.yummyames.objects.Customer;
import com.iastate.yummyames.objects.Order;
import com.iastate.yummyames.objects.Restaurant;

import java.util.ArrayList;

public class OrderListSingleton {


    private ArrayList<Order> orders;

    public static OrderListSingleton myOrderSingleton = new OrderListSingleton();

    private OrderListSingleton() {}

    public static OrderListSingleton getInstance()
    {
        return myOrderSingleton;
    }

    public void setOrders(ArrayList<Order> orders){ this.orders = orders; }

    public ArrayList<Order> getOrders (){ return orders; }

    public ArrayList<Order> getSpecificOrders(){
        ArrayList<Order> mOrders = new ArrayList<>();
        Customer customer = CustomerSingleton.getInstance().getCustomer();
        for(int i=0; i<orders.size(); i++){
                if(orders.get(i).getCustomerName().equals(customer.getName()))
                    mOrders.add(orders.get(i));
        }
        return mOrders;
    }

}
