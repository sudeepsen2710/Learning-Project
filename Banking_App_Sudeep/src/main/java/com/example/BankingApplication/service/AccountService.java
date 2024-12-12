package com.example.BankingApplication.service;



import com.example.BankingApplication.DTO.AccountDTO;

import java.util.List;

public interface AccountService {

    AccountDTO createAccount(AccountDTO accountDTO);
    AccountDTO getAccountById(Long id);
    AccountDTO depositAmount(Long id,double amount);

    AccountDTO withdrawAmount(Long id,double amount);

    List<AccountDTO> getAllAccounts();

    void deleteAccount(Long id);

}
