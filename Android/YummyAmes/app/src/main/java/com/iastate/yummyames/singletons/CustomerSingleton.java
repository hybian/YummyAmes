package com.iastate.yummyames.singletons;


import com.iastate.yummyames.objects.Customer;

public class CustomerSingleton {

    private Customer customer;

    public static CustomerSingleton myCustomerSingleton = new CustomerSingleton();

    private CustomerSingleton() {}

    public static CustomerSingleton getInstance()
    {
        return myCustomerSingleton;
    }

    public void setCustomer(Customer customer) { this.customer=customer; }

    public Customer getCustomer(){ return customer; }

}
