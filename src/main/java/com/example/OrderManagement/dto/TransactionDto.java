package com.example.OrderManagement.dto;

public class TransactionDto {
    private Double amount;
    private Integer userId;

    public TransactionDto(Double amount, Integer userId) {
        this.amount = amount;
        this.userId = userId;
    }

    public Double getAmount() {
        return amount;
    }

    public TransactionDto setAmount(Double amount) {
        this.amount = amount;
        return this;
    }

    public Integer getUserId() {
        return userId;
    }

    public TransactionDto setUserId(Integer userId) {
        this.userId = userId;
        return this;
    }
}
