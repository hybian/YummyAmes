package com.iastate.yummyames.objects;

import android.os.Parcel;
import android.os.Parcelable;

import static com.iastate.yummyames.R.id.address;
import static com.iastate.yummyames.R.id.phone;

public class Food implements Parcelable{

    private String name, price, type, from;

    public Food(String name, String price, String type, String from)
    {
        this.name = name;
        this.price = price;
        this.type = type;
        this.from = from;
    }

    public void setName(String newName) {
        this.name = newName;
    }
    public void setPrice(String newPrice){
        this.price = newPrice;
    }
    public void setType(String newType){
        this.type = newType;
    }
    public void setFrom(String newFrom){
        this.from = newFrom;
    }

    public String getName(){
        return name;
    }
    public String getPrice(){
        return price;
    }
    public String getType(){
        return type;
    }
    public String getFrom(){
        return from;
    }


    public Food(Parcel in) {
        super();
        readFromParcel(in);
    }

    public static final Parcelable.Creator<Food> CREATOR = new Parcelable.Creator<Food>() {
        public Food createFromParcel(Parcel in) {
            return new Food(in);
        }

        public Food[] newArray(int size) {
            return new Food[size];
        }

    };

    public void readFromParcel(Parcel in) {
        name = in.readString();
        price = in.readString();
        type = in.readString();
        from = in.readString();
    }
    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(price);
        dest.writeString(type);
        dest.writeString(from);
    }



}
