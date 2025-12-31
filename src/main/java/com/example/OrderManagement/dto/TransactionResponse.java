package com.example.OrderManagement.dto;

public class TransactionResponse {
    private Integer id;
    private Double amount;
    private UserResponse userInfo;

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

    public UserResponse getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserResponse userInfo) {
        this.userInfo = userInfo;
    }
}
