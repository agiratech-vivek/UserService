package com.example.agirafirstproject.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Name extends BaseModel {
    private String firstName;
    private String lastName;
}
