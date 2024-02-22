package com.example.agirafirstproject.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;

@Getter
@Setter
@Entity
public class GeoLocation extends BaseModel {
    private String latitude;
    private String longitude;
}
