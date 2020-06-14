package org.restaurant.models.services.dao;

import org.restaurant.models.Meal;
import org.restaurant.models.MealDatabase;
import org.restaurant.models.OrderR;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;


@Service
public class OrderDaoImpl implements OrderDao {

    private MealDatabase mealDatabase;

    private OrderDao repository;

    private List<OrderR> orderList;

    public OrderDaoImpl() {
        orderList = new ArrayList<>();
    }

    @Override
    public List<OrderR> getAllOrders() {
        return orderList;
    }

    @Override
    public void addOrder(OrderR order) {
        orderList.add(order);
    }

    @Override
    public void removeOrder(OrderR order) {
        for (OrderR order1 : orderList) {
            if (order1.equals(order)){
                orderList.remove(order);
                break;
            }
        }
    }





    @Override
    public void addMealToOrder(OrderR order, Meal meal) {


        List<Meal> ourMeals =  order.getMealList();
        ourMeals.add(meal);
        order.setMealList( ourMeals);
    }

    @Override
    public void removeMealFromOrder(OrderR order, Meal meal) {

        List<Meal> listaMealsow =  order.getMealList();

        for (Meal meal2: listaMealsow){
            if (meal2.getName().equals(meal.getName())){
                listaMealsow.remove(meal2);
                break;
            }
        }

        order.setMealList(listaMealsow);




    }

    @Override
    public boolean checkIfMealAppearInOurOrder(OrderR order, Meal meal){


        for (Meal meal1:order.getMealList()
             ) {if (meal1.getName().equals(meal.getName())){
                 return true;

             }

        }
        return false;


    }
}
