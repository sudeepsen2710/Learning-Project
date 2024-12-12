package com.example.BankingApplication.DTO;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountDTO {
    private Long id;
    private String accountHolderName;
    private double balance;
}
