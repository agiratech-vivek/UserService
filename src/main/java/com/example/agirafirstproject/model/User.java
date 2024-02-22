package com.example.agirafirstproject.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

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
}