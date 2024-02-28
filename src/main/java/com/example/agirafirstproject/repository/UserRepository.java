package com.example.agirafirstproject.repository;

import com.example.agirafirstproject.model.User;
import com.example.agirafirstproject.repository.projection.UserProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID>, JpaSpecificationExecutor<User> {
    Page<User> findUserByAddress_CityAndDeletedEquals(String city, boolean deleted, Pageable pageable);

    @Query(value = "SELECT u from User u join Role r where r.name = :role")
    List<User> getUserByRole(String role);

    @Query(value = "SELECT * from user u join name n on u.name_id = n.id where n.first_name like :firstChar", nativeQuery = true)
    Slice<User> getUserByFirstLetter(String firstChar, Pageable pageable);

    @Query(value = "SELECT u.contact, a.city, n.first_name from user u join address a on u.address_id = a.id join name n on u.name_id = n.id", nativeQuery = true)
    List<UserProjection> getFirstNameCityContact(Pageable pageable);

    Optional<User> findUserByEmail(String email);

    Page<User> findAllByDeletedEquals(boolean deleted, Pageable pageable);

    Optional<User> findByIdAndDeletedEquals(UUID id, boolean b);
}