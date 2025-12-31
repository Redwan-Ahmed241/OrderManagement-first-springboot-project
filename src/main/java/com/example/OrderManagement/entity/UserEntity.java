package com.example.OrderManagement.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "users")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String name;

    private String phoneNumber;
    @OneToMany(mappedBy = "userEntity")
    private List<TransactionEntity> transactionEntities;

    public List<TransactionEntity> getTransactions() {
        return transactionEntities;
    }

    public void setTransactions(List<TransactionEntity> transactionEntities) {
        this.transactionEntities = transactionEntities;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
