package com.example.agirafirstproject.dto;

import com.example.agirafirstproject.model.Address;
import com.example.agirafirstproject.model.Name;
import com.example.agirafirstproject.model.Role;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import java.util.List;

@Getter
@Setter
public class ModifyUserDto {
    @Min(value = 2, message = "Name should be at least 3 characters long")
    private String name;
    private int houseNumber;
    @Min(value = 2, message = "Locality should be at least 3 characters long")
    private String locality;
    @Min(value = 3, message = "City invalid")
    private String city;
    @Min(value = 2, message = "Latitude invalid")
    private String latitude;
    @Min(value = 2, message = "Longitude invalid")
    private String longitude;
    @Size(min = 10, max = 10, message = "Contact invalid")
    private String contact;
    @Email(message = "Email invalid")
    private String email;
}
