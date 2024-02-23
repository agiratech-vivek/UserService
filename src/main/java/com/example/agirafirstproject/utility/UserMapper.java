package com.example.agirafirstproject.utility;

import com.example.agirafirstproject.dto.ModifyUserDto;
import com.example.agirafirstproject.dto.UserRequestDto;
import com.example.agirafirstproject.dto.UserResponseDto;
import com.example.agirafirstproject.model.Address;
import com.example.agirafirstproject.model.GeoLocation;
import com.example.agirafirstproject.model.Name;
import com.example.agirafirstproject.model.User;

public class UserMapper {
    public UserResponseDto userToUserResponseDto(User user) {
        UserResponseDto userResponseDto = new UserResponseDto();
        userResponseDto.setName(user.getName().getFirstName() + " " + user.getName().getLastName());
        userResponseDto.setContact(user.getContact());
        userResponseDto.setAddress(user.getAddress().getFullAddress());
        userResponseDto.setEmail(user.getEmail());
        return userResponseDto;
    }

    public User UserRequestDtoToUser(UserRequestDto userRequestDto) {
        User user = new User();
        String[] name = userRequestDto.getName().split(" ");
        user.setName(new Name(name[0].trim(), name[1].trim()));
        user.setContact(userRequestDto.getContact());
        user.setEmail(userRequestDto.getEmail());
        user.setRoleList(userRequestDto.getRoleList());
        user.setAddress(new Address(
                userRequestDto.getHouseNumber(),
                userRequestDto.getLocality(),
                userRequestDto.getCity(),
                new GeoLocation(userRequestDto.getLatitude(), userRequestDto.getLongitude())
        ));
        return user;
    }
    public User modifyUserDtoToUser(ModifyUserDto modifyUserDto){
        User user = new User();
        if(modifyUserDto.getName() != null){
            String[] name = modifyUserDto.getName().split(" ");
            user.setName(new Name(name[0].trim(), name[1].trim()));
        }
        if(modifyUserDto.getHouseNumber() != 0){
            user.getAddress().setHouseNumber(modifyUserDto.getHouseNumber());
        }
        if(modifyUserDto.getCity() != null){
            user.getAddress().setCity(modifyUserDto.getCity());
        }
        if(modifyUserDto.getLocality() != null){
            user.getAddress().setLocality(modifyUserDto.getLocality());
        }
        if(modifyUserDto.getLatitude() != null){
            user.getAddress().getGeoLocation().setLatitude(modifyUserDto.getLatitude());
        }
        if(modifyUserDto.getLongitude() != null){
            user.getAddress().getGeoLocation().setLongitude(modifyUserDto.getLongitude());
        }
        if(modifyUserDto.getContact() != null){
            user.setContact(modifyUserDto.getContact());
        }
        if(modifyUserDto.getEmail() != null){
            user.setEmail(modifyUserDto.getEmail());
        }
        return user;
    }
}
