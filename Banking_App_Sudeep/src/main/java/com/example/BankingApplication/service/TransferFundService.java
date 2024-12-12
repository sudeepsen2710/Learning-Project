package com.example.BankingApplication.service;


import com.example.BankingApplication.DTO.TransferFundDto;
import org.springframework.stereotype.Service;


public interface TransferFundService {

    void transferFunds(TransferFundDto transferFundDto);
}
