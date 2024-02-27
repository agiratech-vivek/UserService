package com.example.agirafirstproject.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import java.util.List;

@Getter
@Setter
@Entity
public class Product extends BaseModel{
    private String title;
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Category category;
    private double price;
    private String description;
    @ManyToMany(mappedBy = "productList")
    private List<Cart> cartList;
}
