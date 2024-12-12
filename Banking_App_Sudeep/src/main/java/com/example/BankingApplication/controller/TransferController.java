package com.example.BankingApplication.controller;


import com.example.BankingApplication.DTO.TransferFundDto;
import com.example.BankingApplication.service.TransferFundService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/account")
public class TransferController {

    @Autowired
    private TransferFundService transferFundService;

    public TransferController(TransferFundService transferFundService){
        this.transferFundService=transferFundService;
    }

    @PostMapping("/transferfund")
    public ResponseEntity<String> transferFund(@RequestBody TransferFundDto transferFundDto){
        transferFundService.transferFunds(transferFundDto);
        return ResponseEntity.ok("Amount tranfered succefully");

    }
}
