package com.example.agirafirstproject.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import java.util.List;

@Getter
@Setter
@Entity
public class Cart extends BaseModel{
    @OneToOne
    private User user;
    @ManyToMany
    private List<Product> productList;
}
