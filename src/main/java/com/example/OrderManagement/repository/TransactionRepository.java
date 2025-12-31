package com.example.OrderManagement.repository;

import com.example.OrderManagement.entity.TransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionRepository extends JpaRepository<TransactionEntity, Integer> {
    List<TransactionEntity> findAllByUserEntityId(Integer userId);

    List<TransactionEntity> findAllByAmountGreaterThan(Double amount);
}
