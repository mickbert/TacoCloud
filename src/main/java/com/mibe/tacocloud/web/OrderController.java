package com.mibe.tacocloud.web;

import com.mibe.tacocloud.Order;
import com.mibe.tacocloud.data.OrderRepository;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

@Controller
@RequestMapping("/orders")
@SessionAttributes("order")
public class OrderController {
    OrderRepository orderRepo;

    public OrderController(OrderRepository anOrderRepo) {
        orderRepo=anOrderRepo;
    }
    
    @GetMapping("/current")
    public String orderForm(Model aModel) {
        aModel.addAttribute("order",new Order());
        return "orderForm";
    }
        
    @PostMapping
    public String processOrder(@Valid Order aOrder, Errors aResult,
                               SessionStatus aSessionStatus) {
        if(aResult.hasErrors()) {
            System.err.printf("OrderController:26 %s%n",aResult);
            return "orderForm";
        }
        orderRepo.save(aOrder);
        aSessionStatus.setComplete();
        
        System.err.printf("process order form: %s%n",aOrder);
        return "redirect:/";
    }
}
