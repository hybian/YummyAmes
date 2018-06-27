package com.iastate.yummyames.singletons;

import com.iastate.yummyames.objects.Food;
import com.iastate.yummyames.objects.Restaurant;

import java.util.ArrayList;


public class FoodSingleton {

    private ArrayList<Food> foods;
    private ArrayList<Food> foodsBelongRes;
    private int position;

    public static FoodSingleton myFoodSingleton = new FoodSingleton();

    private FoodSingleton() {}

    public static FoodSingleton getInstance()
    {
        return myFoodSingleton;
    }


    public void setFoods(ArrayList<Food> foods)
    {
        this.foods = foods;
    }

    public void setSpecificFood(int position){ this.position = position; }

    public ArrayList<Food> getFoods()
    {
        return foods;
    }

    public ArrayList<Food> getFoodsByRes(Restaurant restaurant) {
        foodsBelongRes = new ArrayList<>();
        for(int i=0; i<foods.size(); i++)
        {
            Food food = foods.get(i);
            String from = food.getFrom();
            if(from.equals(restaurant.getName())){
                foodsBelongRes.add(food);
            }
        }
        return foodsBelongRes;
    }

    public Food getSpecificFood(){
        if (foodsBelongRes != null)
            return foodsBelongRes.get(position);
        return null;
    }
}
