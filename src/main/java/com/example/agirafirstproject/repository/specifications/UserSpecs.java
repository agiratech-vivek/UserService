package com.example.agirafirstproject.repository.specifications;

import com.example.agirafirstproject.model.Address;
import com.example.agirafirstproject.model.User;

import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;

public class UserSpecs {

    public static Specification<User> hasUserEmail(String email){
        return (root, query, criteriaBuilder) ->
            criteriaBuilder.equal(root.get("user").get("email"), email);
    }

    public static Specification<User> hasUserContact(String contact){
        return ((root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("user").get("contact"), contact));
    }

    public static Specification<User> hasUserCity(String city){
        return ((root, query, criteriaBuilder) -> {
                Join<User, Address> userAddressJoin = root.join("address", JoinType.INNER);
                return criteriaBuilder.equal(userAddressJoin.get("city"), city);
        });
    }
}
