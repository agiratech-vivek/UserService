package com.example.agirafirstproject.dto;

import com.example.agirafirstproject.model.Address;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserResponseDto {
    private String name;
    private String address;
    private String contact;
    private String email;
}
