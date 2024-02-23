package com.example.agirafirstproject.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Address extends BaseModel {
    private int houseNumber;
    private String locality;
    private String city;
    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private GeoLocation geoLocation;

    public String getFullAddress(){
        return houseNumber + ", " + locality + ", " + city;
    }
}
