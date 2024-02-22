package com.example.agirafirstproject.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
public class Address extends BaseModel {
    private int houseNumber;
    private String locality;
    private String City;
    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private GeoLocation geoLocation;
}
