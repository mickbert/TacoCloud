package com.mibe.tacocloud;

import java.util.ArrayList;
import java.util.List;
import java.time.LocalDateTime;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class Taco {
    private long m_id;
    @NotNull
    @Size(min=5,message="Name must be at least 5 characters")
    private String m_name="";
    @NotNull
    @Size(min=1,message="You must select at least one ingredient")
    private List<String> m_ingredients=new ArrayList<>();
    private LocalDateTime m_createdAt;

    public long getId() {return m_id;}
    public void setId(long aId) {m_id=aId;}
    public String getName() {return m_name;}
    public void setName(String aName) {m_name=aName;}
        
    public List<String> getIngredients() {return m_ingredients;}
    public void setIngredients(List<String> aIngList) {
        m_ingredients.addAll(aIngList);
    }

    public LocalDateTime getCreatedAt() {return m_createdAt;}
    public void SetCreatedAt(LocalDateTime aCreationTimestamp) {
        m_createdAt=aCreationTimestamp;
    }
    public LocalDateTime setCreatedAt() {
        return m_createdAt=LocalDateTime.now();
    }
}
