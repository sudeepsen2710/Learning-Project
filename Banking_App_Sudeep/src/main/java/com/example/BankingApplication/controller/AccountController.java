package com.example.BankingApplication.controller;


import com.example.BankingApplication.DTO.AccountDTO;
import com.example.BankingApplication.service.AccountService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/account")
public class AccountController {

    private AccountService accountService;

    public AccountController(AccountService accountService){
        this.accountService=accountService;
    }

    @PostMapping("/createaccount")
    public ResponseEntity<AccountDTO> addAAccount(@RequestBody AccountDTO accountDTO){
            return new ResponseEntity<>(accountService.createAccount(accountDTO), HttpStatus.CREATED);
    }

    @GetMapping("/getaccount/{id}")
    public ResponseEntity<AccountDTO> getAccount(@PathVariable Long id){
        return new ResponseEntity<>(accountService.getAccountById(id),HttpStatus.OK);
    }

    @PutMapping("/deposit/{id}")
    public ResponseEntity<AccountDTO> depositAmount( @PathVariable  Long id, @RequestBody Map<String ,Double> request){
        Double amount=request.get("amount");
    AccountDTO accountDTO=  accountService.depositAmount(id,amount);
    return  ResponseEntity.ok(accountDTO);
    }

    @PutMapping("/withdraw/{id}")
    public ResponseEntity<AccountDTO> withdrawAmount( @PathVariable  Long id, @RequestBody Map<String ,Double> request){
        Double amount=request.get("amount");
        AccountDTO accountDTO=  accountService.withdrawAmount(id,amount);
        return  ResponseEntity.ok(accountDTO);
    }

    @GetMapping("/allaccounts")
    public ResponseEntity<List<AccountDTO>> getAllAccount(){
        List<AccountDTO> account= accountService.getAllAccounts();
        return ResponseEntity.ok(account);
    }

    @DeleteMapping("/deleteaccount/{id}")
    public ResponseEntity<String > deleteAccount(@PathVariable Long id) {
       accountService.deleteAccount(id);
       return ResponseEntity.ok("Account no "+id+" is deleted successfully...!");
    }
}
