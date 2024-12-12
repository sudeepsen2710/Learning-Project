package com.example.BankingApplication.service;


import com.example.BankingApplication.DTO.TransactionDto;

import java.util.List;

public interface TransactionService {

    List<TransactionDto> getAccountTransactions(Long accountId);

}
