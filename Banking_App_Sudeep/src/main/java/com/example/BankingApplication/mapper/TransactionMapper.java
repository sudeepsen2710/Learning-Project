package com.example.BankingApplication.mapper;


import com.example.BankingApplication.DTO.TransactionDto;
import com.example.BankingApplication.entity.Transaction;

public class TransactionMapper {

    public static Object convertEntityToDto;

    public static TransactionDto convertEntityToDto(Transaction transaction){
        return new TransactionDto(
                transaction.getId(),
                transaction.getAccountId(),
                transaction.getAmount(),
                transaction.getTransactionType(),
                transaction.getTimestamp()
        );

    }
}
