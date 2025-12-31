package com.example.OrderManagement.service;

import com.example.OrderManagement.dto.TransactionDto;
import com.example.OrderManagement.dto.TransactionResponse;
import com.example.OrderManagement.dto.UserResponse;
import com.example.OrderManagement.entity.TransactionEntity;
import com.example.OrderManagement.entity.UserEntity;
import com.example.OrderManagement.repository.TransactionRepository;
import com.example.OrderManagement.repository.UserRepository;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.io.InvalidClassException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TransactionService {
    private final TransactionRepository transactionRepository;
    private final UserRepository userRepository;

    public TransactionService(TransactionRepository transactionRepository, UserRepository userRepository) {
        this.transactionRepository = transactionRepository;
        this.userRepository = userRepository;
    }

    public void createTransaction(TransactionDto transactionDto) throws InvalidClassException, FileNotFoundException {
        if (transactionDto.getAmount() == null) {
            throw new InvalidClassException("Amount can't be null");
        }
        if (transactionDto.getAmount() <= 0) {
            throw new InvalidClassException("Amount must be greater than zero");
        }

        Optional<UserEntity> user = userRepository.findById(transactionDto.getUserId());
        if (user.isEmpty()) {
            throw new FileNotFoundException("User not found");
        }

        TransactionEntity transactionEntity = new TransactionEntity();
        transactionEntity.setAmount(transactionDto.getAmount());
        transactionEntity.setUsers(user.get());

        transactionRepository.save(transactionEntity);
    }

    public List<TransactionResponse> getAllTransactions() {
        List<TransactionEntity> transactionEntityList = transactionRepository.findAll();

        List<TransactionResponse> transactionResponses = new ArrayList<>();
        for (TransactionEntity transaction : transactionEntityList) {
            TransactionResponse response = new TransactionResponse();

            response.setId(transaction.getId());
            response.setAmount(transaction.getAmount());

            UserResponse userResponse = new UserResponse();
            userResponse.setId(transaction.getUsers().getId());
            userResponse.setName(transaction.getUsers().getName());
            userResponse.setPhoneNumber(transaction.getUsers().getPhoneNumber());
            response.setUserInfo(userResponse);

            transactionResponses.add(response);
        }
        return transactionResponses;
    }

    public TransactionResponse getTransactionById(Integer id) throws FileNotFoundException {
        Optional<TransactionEntity> transaction = transactionRepository.findById(id);

        if (transaction.isEmpty()) {
            throw new FileNotFoundException("Transaction not found");
        }

        TransactionEntity entity = transaction.get();

        TransactionResponse response = new TransactionResponse();
        response.setId(entity.getId());
        response.setAmount(entity.getAmount());

        UserResponse userResponse = new UserResponse();
        userResponse.setId(entity.getUsers().getId());
        userResponse.setName(entity.getUsers().getName());
        userResponse.setPhoneNumber(entity.getUsers().getPhoneNumber());
        response.setUserInfo(userResponse);

        return response;
    }

    public List<TransactionResponse> getTransactionsByUserId(Integer userId) {
        List<TransactionEntity> transactions = transactionRepository.findAllByUserEntityId(userId);

        if (transactions.isEmpty()) {
            return new ArrayList<TransactionResponse>();
        }

        return transactions.stream().map(transaction -> {
            TransactionResponse response = new TransactionResponse();
            response.setId(transaction.getId());
            response.setAmount(transaction.getAmount());

            UserResponse userResponse = new UserResponse();
            userResponse.setId(transaction.getUsers().getId());
            userResponse.setName(transaction.getUsers().getName());
            userResponse.setPhoneNumber(transaction.getUsers().getPhoneNumber());
            response.setUserInfo(userResponse);

            return response;
        }).toList();
    }

    public void updateTransaction(Integer id, TransactionDto transactionDto)
            throws ChangeSetPersister.NotFoundException, InvalidClassException, FileNotFoundException {
        TransactionEntity entity = transactionRepository.findById(id)
                .orElseThrow(ChangeSetPersister.NotFoundException::new);

        if (transactionDto.getAmount() == null) {
            throw new InvalidClassException("Amount can't be null");
        }
        if (transactionDto.getAmount() <= 0) {
            throw new InvalidClassException("Amount must be greater than zero");
        }

        entity.setAmount(transactionDto.getAmount());

        if (transactionDto.getUserId() != null && !transactionDto.getUserId().equals(entity.getUsers().getId())) {
            Optional<UserEntity> user = userRepository.findById(transactionDto.getUserId());
            if (user.isEmpty()) {
                throw new FileNotFoundException("User not found");
            }
            entity.setUsers(user.get());
        }

        transactionRepository.save(entity);
    }

    public void deleteTransaction(Integer id) throws ChangeSetPersister.NotFoundException {
        TransactionEntity entity = transactionRepository.findById(id)
                .orElseThrow(ChangeSetPersister.NotFoundException::new);
        transactionRepository.delete(entity);
    }
}
