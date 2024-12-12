package com.example.BankingApplication.service;



import com.example.BankingApplication.DTO.AccountDTO;
import com.example.BankingApplication.entity.Account;
import com.example.BankingApplication.entity.Transaction;
import com.example.BankingApplication.exception.AccountException;
import com.example.BankingApplication.mapper.AccountMapper;
import com.example.BankingApplication.repository.AccountRepository;
import com.example.BankingApplication.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import static com.example.BankingApplication.constant.Constants.TRANSACTION_TYPE_DEPOSIT;
import static com.example.BankingApplication.constant.Constants.TRANSACTION_TYPE_WITHDRAW;


@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private TransactionRepository transactionReposity;

    @Override
    public AccountDTO createAccount(AccountDTO accountDTO) {
        Account account= AccountMapper.mapToAccount(accountDTO);
        Account savedAccount= accountRepository.save(account);
        return AccountMapper.mapToAccountDTO(savedAccount);
    }

    @Override
    public AccountDTO getAccountById(Long id) {
        Account account=accountRepository
                .findById(id).
                orElseThrow(()-> new AccountException("Account Does not exists"));
        return AccountMapper.mapToAccountDTO(account);
    }

    @Override
    public AccountDTO depositAmount(Long id,double amount) {
        Account account=accountRepository
                .findById(id).
                orElseThrow(()-> new AccountException("Account does not exists"));
        double total=account.getBalance() + amount;
        account.setBalance(total);
        Account savedAccount = accountRepository.save(account);
        Transaction transaction=new Transaction();
        transaction.setAccountId(account.getId());
        transaction.setAmount(amount);
        transaction.setTransactionType(TRANSACTION_TYPE_DEPOSIT);
        transaction.setTimestamp(LocalDateTime.now());
        transactionReposity.save(transaction);
        return AccountMapper.mapToAccountDTO(savedAccount);
    }

    @Override
    public AccountDTO withdrawAmount(Long id, double amount) {
        Account account=accountRepository
                .findById(id).
                orElseThrow(()-> new AccountException("Account Does not exists"));
        if(account.getBalance()<amount){
            throw new RuntimeException("Insufficient amount");
        }
        double total=account.getBalance() - amount;
        account.setBalance(total);
        Account savedAccount = accountRepository.save(account);
        Transaction transaction=new Transaction();
        transaction.setAccountId(account.getId());
        transaction.setAmount(amount);
        transaction.setTransactionType(TRANSACTION_TYPE_WITHDRAW);
        transaction.setTimestamp(LocalDateTime.now());
        transactionReposity.save(transaction);
        return AccountMapper.mapToAccountDTO(savedAccount);
    }

    @Override
    public List<AccountDTO> getAllAccounts() {
        List<Account> accounts=accountRepository.findAll();
       return accounts.stream().map(account -> AccountMapper.mapToAccountDTO(account)).collect(Collectors.toList());
    }

    @Override
    public void deleteAccount(Long id) {
        Account account=accountRepository
                .findById(id).
                orElseThrow(()-> new AccountException("Account Does not exists"));
        accountRepository.deleteById(id);
    }
}
