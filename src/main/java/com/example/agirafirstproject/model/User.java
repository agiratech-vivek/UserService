package com.example.agirafirstproject.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
public class User extends BaseModel{
    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Name name;
    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Address address;
    private String contact;
    private String email;
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, mappedBy = "userList")
    private List<Role> roleList;
    private String password;
    @OneToMany(mappedBy = "user")
    private List<Order> orderList;
    @OneToOne
    private Cart cart;
}