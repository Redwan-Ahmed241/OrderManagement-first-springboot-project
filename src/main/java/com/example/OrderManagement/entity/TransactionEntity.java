package com.example.OrderManagement.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "transactions")
public class TransactionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private Double amount;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity userEntity;

    public UserEntity getUsers() {
        return userEntity;
    }

    public void setUsers(UserEntity userEntity) {
        this.userEntity = userEntity;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }
}
