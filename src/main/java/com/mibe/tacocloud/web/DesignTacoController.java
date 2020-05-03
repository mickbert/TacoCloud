package com.mibe.tacocloud.web;

import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.mibe.tacocloud.Taco;
import com.mibe.tacocloud.Order;
import com.mibe.tacocloud.data.IngredientRepository;
import com.mibe.tacocloud.data.TacoRepository;
import com.mibe.tacocloud.Ingredient;
import com.mibe.tacocloud.Ingredient.Type;

@Controller
@RequestMapping("/design")
@SessionAttributes("order")
public class DesignTacoController {

    private final IngredientRepository m_ingredientRepo;
    private TacoRepository m_tacoRepository;

    public DesignTacoController(IngredientRepository aIngRepo,
                                TacoRepository aTacoRepo) {
        m_ingredientRepo=aIngRepo;
        m_tacoRepository=aTacoRepo;
    }

    @ModelAttribute(name="order")
    public Order order() {return new Order();}
    @ModelAttribute(name="taco")
    public Taco taco() {return new Taco();}
    
    @GetMapping
    public String showDesignForm(Model aModel) {
        System.out.println("get design form");
        List<Ingredient> ingList=new ArrayList<>();
        for(Ingredient ing:m_ingredientRepo.findAll())
            ingList.add(ing);
                
        Type typeList[]=Ingredient.Type.values();
        for(Type type:typeList ) {
            aModel.addAttribute(type.toString()
                                .toLowerCase(),
                                filterByType(ingList,type) );
        }
        aModel.addAttribute("designTaco",new Taco());
                
        return "design";
    }

    public static List<Ingredient> filterByType(List<Ingredient> aList,
                                                Ingredient.Type aType) {
        return aList.stream().filter(i->i.type()==aType)
            .collect(Collectors.toList());
    }
        
    @PostMapping
    public String processDesign(@Valid Taco aDesign,Errors aResult,
                                @ModelAttribute Order anOrder) {
        if(aResult.hasErrors()) {
            System.err.printf("DesignTacoController.java:59: %s%n",aResult);
            return "design";
        }
        if(m_tacoRepository.save(aDesign)==null) {
            return "design";
        }
        return "redirect:/orders/current";
    }
} // DesignTacoController
