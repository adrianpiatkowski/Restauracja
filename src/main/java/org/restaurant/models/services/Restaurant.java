package org.restaurant.models.services;

import org.restaurant.models.Meal;
import org.restaurant.models.MealDatabase;
import org.restaurant.models.OrderR;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Restaurant {

    private List<Meal> menu = new MealDatabase().getMeals();

    public boolean checkIfMealExist(String mealName){
        for (Meal meal : menu) {
            if (mealName.equals(meal.getName())){
                return true;
            }
        }
        return false;
    }

     public int calculateOrderPrice(OrderR orderR){
        int sum = 0;
         for (Meal meal: orderR.getMealList()
              ) { sum += meal.getPrice();

         }
        //todo implement
        return sum;
    }

    public Meal getMeal(String mealName){
        Meal mealMain = new Meal();
        MealDatabase mealDatabase = new MealDatabase();
        for (Meal meal: mealDatabase.getMeals()) {
            if (mealName.equals(meal.getName()) ) {
                 mealMain = meal;
            }

        }
        //todo implement
        return mealMain;
    }

    public List<Meal> getMenu() {
        return menu;
    }
}
