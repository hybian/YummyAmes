package com.iastate.yummyames.objects;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class Restaurant implements Parcelable{

    private String id, name, address, phone, email;
    private ArrayList<Food> menu;
    int size;

    public Restaurant(String id, String name, String address, String phone, String email)
    {
        this.id = id;
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.email = email;
        menu = new ArrayList<Food>();
    }

    public void setName(String newName) {
        this.name = newName;
    }
    public void setAddress(String newAddress){
        this.address = newAddress;
    }
    public void setPhone(String newPhone){
        this.phone = newPhone;
    }
    public void setEmail(String newEmail){
        this.email = newEmail;
    }
    public void setMenu(ArrayList<Food> menu)
    {
        this.menu = new ArrayList<Food>(menu);
    }
    public void addToMenu(Food newFood)
    {
        menu.add(newFood);
    }

    public String getId(){ return id; }
    public String getName(){
        return name;
    }
    public String getAddress(){
        return address;
    }
    public String getPhone(){
        return phone;
    }
    public String getEmail(){
        return email;
    }

    public ArrayList<Food> getMenu()
    {
        return menu;
    }


    public Restaurant(Parcel in) {
        super();
        readFromParcel(in);
    }

    public static final Parcelable.Creator<Restaurant> CREATOR = new Parcelable.Creator<Restaurant>() {
        public Restaurant createFromParcel(Parcel in) {
            return new Restaurant(in);
        }

        public Restaurant[] newArray(int size) {
            return new Restaurant[size];
        }

    };

    public void readFromParcel(Parcel in) {
        name = in.readString();
        address = in.readString();
        phone = in.readString();
        email = in.readString();
        //in.readList(menu, null);
        //in.readList(menu, getClass().getClassLoader());
        //in.readTypedList(menu, Food.CREATOR);
    }
    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(address);
        dest.writeString(phone);
        dest.writeString(email);
        //dest.writeList(menu);
    }



}
