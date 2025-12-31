package com.example.OrderManagement.controller;

import com.example.OrderManagement.dto.TransactionDto;
import com.example.OrderManagement.dto.TransactionResponse;
import com.example.OrderManagement.service.TransactionService;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.FileNotFoundException;
import java.io.InvalidClassException;
import java.util.List;

@RestController
@RequestMapping("transactions")
public class TransactionsController {
    private final TransactionService transactionService;

    public TransactionsController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping
    public ResponseEntity<Void> createTransaction(@RequestBody TransactionDto transactionDto)
            throws InvalidClassException, FileNotFoundException {
        transactionService.createTransaction(transactionDto);
        return ResponseEntity.ok().build();
    }

    @GetMapping("list")
    public ResponseEntity<List<TransactionResponse>> getAllTransactions() {
        var list = transactionService.getAllTransactions();
        return ResponseEntity.ok(list);
    }

    @GetMapping("{id}")
    public ResponseEntity<TransactionResponse> getTransactionById(@PathVariable Integer id)
            throws FileNotFoundException {
        return ResponseEntity.ok(transactionService.getTransactionById(id));
    }

    @GetMapping("user/{userId}")
    public ResponseEntity<List<TransactionResponse>> getTransactionsByUserId(@PathVariable Integer userId) {
        return ResponseEntity.ok(transactionService.getTransactionsByUserId(userId));
    }

    @PutMapping("update/{id}")
    public ResponseEntity<Void> updateTransaction(@PathVariable Integer id, @RequestBody TransactionDto transactionDto)
            throws ChangeSetPersister.NotFoundException, InvalidClassException, FileNotFoundException {
        transactionService.updateTransaction(id, transactionDto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteTransaction(@PathVariable Integer id)
            throws ChangeSetPersister.NotFoundException {
        transactionService.deleteTransaction(id);
        return ResponseEntity.ok().build();
    }
}
