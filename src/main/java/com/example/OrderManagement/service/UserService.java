package com.example.OrderManagement.service;

import com.example.OrderManagement.dto.UserDto;
import com.example.OrderManagement.dto.UserResponse;
import com.example.OrderManagement.entity.UserEntity;
import com.example.OrderManagement.repository.UserRepository;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.io.InvalidClassException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void createUser(UserDto userDto) throws InvalidClassException {
        if(userDto.getPhoneNumber() == null){
            throw new InvalidClassException("Phone number can't be null");
        }
        UserEntity userEntity = new UserEntity();
        userEntity.setName(userDto.getName());
        userEntity.setPhoneNumber(userDto.getPhoneNumber());

        userRepository.save(userEntity);
    }

    public List<UserResponse> getAllUsers(){
        List<UserEntity> userEntityList = userRepository.findAll();

        List<UserResponse> userResponses = new ArrayList<>();
        for(UserEntity user: userEntityList){
            UserResponse response = new UserResponse();

            response.setId(user.getId());
            response.setName(user.getName());
            response.setPhoneNumber(user.getPhoneNumber());

            userResponses.add(response);
        }
        return userResponses;
    }

    public UserResponse getUserById(Integer id) throws FileNotFoundException {
        Optional<UserEntity> users = userRepository.findById(id);

        if(users.isEmpty()){
            throw new FileNotFoundException("User not found.");
        }

        UserEntity entity = users.get();

        UserResponse response = new UserResponse();
        response.setId(entity.getId());
        response.setName(entity.getName());
        response.setPhoneNumber(entity.getPhoneNumber());
        return response;
    }

    public List<UserResponse> getUserByPhoneNumber(String phoneNumber){
        List<UserEntity> users = userRepository.findAllByPhoneNumber(phoneNumber);

        if(users.isEmpty()){
            return new ArrayList<UserResponse>();
        }

        return users.stream().map(user -> {
            UserResponse response = new UserResponse();
            response.setId(user.getId());
            response.setName(user.getName());
            response.setPhoneNumber(user.getPhoneNumber());
            return response;
        }).toList();
    }

    public void updateUser(Integer id, UserDto userDto) throws ChangeSetPersister.NotFoundException {
        UserEntity entity = userRepository.findById(id).orElseThrow(ChangeSetPersister.NotFoundException::new);
        entity.setName(userDto.getName());
        entity.setPhoneNumber(userDto.getPhoneNumber());

        userRepository.save(entity);
    }

    public void deleteUser(Integer id) throws ChangeSetPersister.NotFoundException {
        UserEntity entity = userRepository.findById(id).orElseThrow(ChangeSetPersister.NotFoundException::new);
        userRepository.delete(entity);
    }
}
