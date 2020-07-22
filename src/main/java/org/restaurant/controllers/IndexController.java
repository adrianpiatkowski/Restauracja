package org.restaurant.controllers;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
@Controller
public class IndexController {
    @GetMapping("/") public String indexGet(Model model){
        model.addAttribute("welcome", "Welcome to our restaurant.");
        return "views/index";
    }
    @GetMapping("/sales") public String sales(Model model){
        model.addAttribute("sales", "If you hire Adrian Piatkowski, all dishes are free for you!!! ;)");
        return "views/sales";
    }
}
