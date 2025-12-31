package com.example.OrderManagement.controller;

import com.example.OrderManagement.dto.UserDto;
import com.example.OrderManagement.dto.UserResponse;
import com.example.OrderManagement.service.UserService;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.FileNotFoundException;
import java.io.InvalidClassException;
import java.util.List;

@RestController
@RequestMapping("users")
public class UsersController {
    private final UserService userService;

    public UsersController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<Void> createUsers(@RequestBody UserDto userDto) throws InvalidClassException {
        userService.createUser(userDto);
        return ResponseEntity.ok().build();
    }

    @GetMapping("list")
    public ResponseEntity<List<UserResponse>> getAllUser(){
        var list = userService.getAllUsers();
        return ResponseEntity.ok(list);
    }

    @GetMapping("{id}")
    public ResponseEntity<UserResponse> getIUserById(@PathVariable Integer id) throws FileNotFoundException {
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @GetMapping("phone-number")
    public ResponseEntity<List<UserResponse>> getUsersByPhoneNumber(@RequestParam String number)  {
        return ResponseEntity.ok(userService.getUserByPhoneNumber(number));
    }

    @PutMapping("update/{id}")
    public ResponseEntity<List<Void>> updateUserInfo(@PathVariable Integer id, @RequestBody UserDto userDto) throws ChangeSetPersister.NotFoundException {
        userService.updateUser(id, userDto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Integer id) throws ChangeSetPersister.NotFoundException {
        userService.deleteUser(id);
        return ResponseEntity.ok().build();
    }

}
