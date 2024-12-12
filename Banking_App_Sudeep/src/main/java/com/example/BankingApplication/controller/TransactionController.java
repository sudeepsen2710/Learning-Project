package com.example.BankingApplication.controller;


import com.example.BankingApplication.DTO.TransactionDto;
import com.example.BankingApplication.service.TransactionService;
import jakarta.persistence.GeneratedValue;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

    @Autowired
    TransactionService transactionService;

    @GetMapping("/{accountId}")
    public ResponseEntity<List<TransactionDto>> fetchAccountTransactions(@PathVariable Long accountId){
        List<TransactionDto> transactions=transactionService.getAccountTransactions(accountId);
       return ResponseEntity.ok(transactions);
    }
}
