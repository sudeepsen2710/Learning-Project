package com.example.BankingApplication.DTO;

public record TransferFundDto(Long fromAccountId,
                              Long toAccountId,
                              double amount)
{

}
