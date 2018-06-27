package com.iastate.yummyames.objects;

public class Order {

    private String id;
    private String price;
    private String customerName;
    private String customerPhone;
    private String customerAddress;
    private String resId;
    private String situation;

    public Order(String id, String price, String customerName, String customerPhone, String customerAddress,
                 String resId, String situation){
        this.id = id;
        this.price = price;
        this.customerName = customerName;
        this.customerPhone = customerPhone;
        this.customerAddress = customerAddress;
        this.resId = resId;
        this.situation = situation;
    }

    public String getOrderId(){ return id; }

    public String getPrice(){ return price; }

    public String getCustomerName(){ return customerName; }

    public String getCustomerPhone(){ return customerPhone; }

    public String getCustomerAddress(){ return customerAddress; }

    public String getResId(){ return resId; }

    public String getSituation(){ return situation; }


}
