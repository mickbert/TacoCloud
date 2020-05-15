package com.mibe.tacocloud.model;

import java.util.ArrayList;
import java.util.List;
import java.time.LocalDateTime;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class Taco {
    private long id;
    @NotNull
    @Size(min=5,message="Name must be at least 5 characters")
    private String name ="";
    @NotNull
    @Size(min=1,message="You must select at least one ingredient")
    private List<String> ingredients =new ArrayList<>();
    private LocalDateTime createdAt;

    public long getId() {return id;}
    public void setId(long aId) {
        id =aId;}
    public String getName() {return name;}
    public void setName(String aName) {
        name =aName;}
        
    public List<String> getIngredients() {return ingredients;}
    public void setIngredients(List<String> aIngList) {
        ingredients.addAll(aIngList);
    }

    public LocalDateTime getCreatedAt() {return createdAt;}
    public void setCreatedAt(LocalDateTime aCreationTimestamp) {
        createdAt =aCreationTimestamp;
    }
    public LocalDateTime setCreatedAtToNow() {
        return createdAt =LocalDateTime.now();
    }
}
