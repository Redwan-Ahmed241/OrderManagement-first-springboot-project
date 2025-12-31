package com.example.OrderManagement.repository;

import com.example.OrderManagement.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {
    List<UserEntity> findAllByPhoneNumber(String number);


//    Optional<Users> findByPhoneNumber(String number);

    @Query(value = "SELECT use FROM UserEntity use WHERE use.phoneNumber = :number")
    List<UserEntity> getAllByPhoneNumber(String number);
}
