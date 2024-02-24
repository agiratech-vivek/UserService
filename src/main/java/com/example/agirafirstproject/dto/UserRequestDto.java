package com.example.agirafirstproject.dto;

import com.example.agirafirstproject.model.Role;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.*;
import java.util.List;

@Getter
@Setter
public class UserRequestDto {
    @NotNull(message = "Name cannot be null")
    @Size(min = 3, message = "Name not valid")
    private String name;
    private int houseNumber;
    @Size(min = 2, max = 50, message = "Locality not valid")
    private String locality;
    @Size(min = 2, max = 50, message = "City not valid")
    private String city;
    @NotNull(message = "latitude not valid")
    private String latitude;
    @NotNull(message = "Longitude not valid")
    private String longitude;
    @NotNull(message = "Contact cannot be null")
    @Size(min = 10, max = 10, message = "Contact not valid")
    private String contact;
    @NotNull(message = "Email cannot be null")
    @Email(message = "Email not valid")
    private String email;
    private List<Role> roleList;
    @NotNull(message = "Password is mandatory")
    private String password;
}
