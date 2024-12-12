package com.example.BankingApplication.service;


import com.example.BankingApplication.DTO.TransactionDto;
import com.example.BankingApplication.entity.Transaction;
import com.example.BankingApplication.mapper.TransactionMapper;
import com.example.BankingApplication.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TransactionServiceImpl implements TransactionService {
    @Autowired
    TransactionRepository transactionRepository;



    @Override
    public List<TransactionDto> getAccountTransactions(Long accountId) {
        List<Transaction> transactions=transactionRepository.findByAccountIdOrderByTimestampDesc(accountId);
        return  transactions.stream().map(transaction -> TransactionMapper.convertEntityToDto(transaction)).collect(Collectors.toList());

    }
}
