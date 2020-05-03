package com.mibe.tacocloud.web;

import com.mibe.tacocloud.Order;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/orders")
public class OrderController {
    @GetMapping("/current")
    public String orderForm(Model aModel) {
        aModel.addAttribute("order",new Order());
        return "orderForm";
    }
        
    @PostMapping
    public String processOrder(@Valid Order aOrder, Errors aResult) {
        if(aResult.hasErrors()) {
            System.err.printf("OrderController:26 %s%n",aResult);
            return "orderForm";
        }
        System.err.printf("process order form: %s%n",aOrder);
        return "redirect:/";
    }
}
