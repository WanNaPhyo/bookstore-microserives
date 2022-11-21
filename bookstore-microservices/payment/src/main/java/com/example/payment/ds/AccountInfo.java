package com.example.payment.ds;

import lombok.Data;

import javax.persistence.Entity;

@Data
public class AccountInfo {
    private String name;
    private double totalAmount;
    private String accountNumber;

}
