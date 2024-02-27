package com.example.agirafirstproject.repository;

import com.example.agirafirstproject.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findUserByAddress_City(String city);

    @Query(value = "SELECT u from User u join Role r where r.name = :role")
    List<User> getUserByRole(String role);

    @Query(value = "SELECT * from user u join name n on u.name_id = n.id where n.first_name like :firstChar", nativeQuery = true)
    List<User> getUserByFirstLetter(String firstChar);
}