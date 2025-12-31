package com.example.OrderManagement.dto;

import org.antlr.v4.runtime.misc.NotNull;

public class UserDto {
    private String name;
    private String phoneNumber;

    public UserDto(String name, String phoneNumber) {
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

    public String getName() {
        return name;
    }

    public UserDto setName(String name) {
        this.name = name;
        return this;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public UserDto setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }
}
