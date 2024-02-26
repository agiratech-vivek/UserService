package com.example.agirafirstproject.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import java.util.List;

@Getter
@Setter
@Entity
public class Role extends BaseModel {
    private String name;
    @ManyToMany
    private List<User> userList;
}
