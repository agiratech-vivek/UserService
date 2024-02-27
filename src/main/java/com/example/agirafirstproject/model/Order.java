package com.example.agirafirstproject.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Getter
@Setter
@Entity
public class Order extends BaseModel{
    @ManyToOne
    private User user;
    @OneToOne
    private Cart cart;
    private double totalPrice;
}