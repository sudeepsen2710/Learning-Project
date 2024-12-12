package com.example.BankingApplication.service;


import com.example.BankingApplication.DTO.TransferFundDto;
import com.example.BankingApplication.entity.Account;
import com.example.BankingApplication.entity.Transaction;
import com.example.BankingApplication.repository.AccountRepository;
import com.example.BankingApplication.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

import static com.example.BankingApplication.constant.Constants.TRANSACTION_TYPE_TRANSFER;

@Service
public class TransferFundImpl implements TransferFundService{

    @Autowired
    private TransactionRepository transactionRepository;
    private AccountRepository accountRepository;

    public TransferFundImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public void transferFunds(TransferFundDto transferFundDto) {
      Account fromAccount= accountRepository.findById(transferFundDto.fromAccountId())
              .orElseThrow(()-> new RuntimeException("Account does not exists."));

      Account toAccount=accountRepository.findById(transferFundDto.toAccountId())
              .orElseThrow(()-> new RuntimeException("Account does not exists."));

      //debit amount from account object
        fromAccount.setBalance(fromAccount.getBalance()-transferFundDto.amount());

      //credit amount to account object
        toAccount.setBalance(toAccount.getBalance()+transferFundDto.amount());

        accountRepository.save(fromAccount);
        accountRepository.save(toAccount);
        Transaction transaction=new Transaction();
        transaction.setAccountId(transferFundDto.fromAccountId());
        transaction.setAmount(transferFundDto.amount());
        transaction.setTransactionType(TRANSACTION_TYPE_TRANSFER);
        transaction.setTimestamp(LocalDateTime.now());
        transactionRepository.save(transaction);
    }
}
