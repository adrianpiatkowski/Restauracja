package org.restaurant.controllers;
import lombok.RequiredArgsConstructor;
import org.restaurant.models.OrderR;
import org.restaurant.models.services.Restaurant;
import org.restaurant.models.services.dao.OrderDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.restaurant.models.UserRequest;
@Controller
@RequiredArgsConstructor
public class OrderController {
    @Autowired
    private OrderDao orderDao;
    @Autowired
    private Restaurant restaurant;
    private OrderR ourOrderR;
    @GetMapping("/order") public String orderGet(Model model){
        model.addAttribute("welcome", "Please place order and wait for preparing it");
        model.addAttribute("menu", "Our Menu: ");
        model.addAttribute("mealList", restaurant.getMenu() );
        orderDao.addOrder(new OrderR());
        ourOrderR = orderDao.getAllOrders().get(orderDao.getAllOrders().size() - 1);
        model.addAttribute("userRequest", new UserRequest());
        if (ourOrderR.getMealList().size()>0){
            model.addAttribute("currentOrder", "Your Current Order: " + ourOrderR.getMealList().toString());
        }
        return "views/order";
    }
    @RequestMapping(params = "addOrder", method = RequestMethod.POST) public String orderAdd(
            @ModelAttribute("userRequest") UserRequest userRequest, Model model){
        model.addAttribute("welcome", "Please place order and wait for preparing it");
        model.addAttribute("menu", "Our Menu:" );
        model.addAttribute("mealList", restaurant.getMenu());
        if (ourOrderR.getOrderOwner()==null && !userRequest.getName().equals("")){
            ourOrderR.setOrderOwner(userRequest.getName());
        }
        if (ourOrderR.getOrderOwner()!=null){
            model.addAttribute("userName", "OrderR placing by: " + ourOrderR.getOrderOwner());
        }
        if (restaurant.checkIfMealExist(userRequest.getMealName())){
            orderDao.addMealToOrder(ourOrderR, restaurant.getMeal(userRequest.getMealName()));
        }
        if (ourOrderR.getMealList().size()>0){
            model.addAttribute("currentOrder", "Your Current OrderR: " + ourOrderR.getMealList().toString());
        }
        return "views/order";
    }
    @RequestMapping(params = "removeOrder", method = RequestMethod.POST) public String orderRemove(
            @ModelAttribute("userRequest") UserRequest userRequest, Model model){
        model.addAttribute("welcome", "Please place order and wait for preparing it");
        model.addAttribute("menu", "Our Menu: ");
        model.addAttribute("mealList", restaurant.getMenu());
        if (ourOrderR.getOrderOwner()==null && !userRequest.getName().equals("")){
            ourOrderR.setOrderOwner(userRequest.getName());
        }
        if (ourOrderR.getOrderOwner()!=null){
            model.addAttribute("userName", "Order placing by: " + ourOrderR.getOrderOwner());
        }
        if (orderDao.checkIfMealAppearInOurOrder(ourOrderR, restaurant.getMeal(userRequest.getMealName()))){
            orderDao.removeMealFromOrder(ourOrderR, restaurant.getMeal(userRequest.getMealName()));
        }
        if (ourOrderR.getMealList().size()>0){
            model.addAttribute("currentOrder", "Your Current Order: " + ourOrderR.getMealList().toString());
        }
        return "views/order";
    }
    @RequestMapping(params = "placeOrder", method = RequestMethod.POST) public String orderPlace(
            @ModelAttribute("userRequest") UserRequest userRequest, Model model){
        model.addAttribute("thanks", "Thank you for placing your order.");
        if (ourOrderR.getMealList().size()>0){
            model.addAttribute("currentOrder",   ourOrderR.getOrderOwner() + " your order is: " +
                    ourOrderR.getMealList().toString());
            model.addAttribute("message", "Order Total: $" +
                    restaurant.calculateOrderPrice(ourOrderR));
        } else {
            model.addAttribute("message", "Order has been cancelled..");
        }
        return "views/orderConfirmation";
    }
}
