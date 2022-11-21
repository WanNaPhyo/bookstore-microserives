package com.example.payment.controller;

import com.example.payment.dao.AccountDao;
import com.example.payment.ds.Account;
import com.example.payment.ds.AccountInfo;
import com.example.payment.ds.AccountType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.net.URI;
import java.util.Optional;
import java.util.Random;

@RestController
@RequestMapping("/payment")
public class AccountController {
    @Autowired
    private AccountDao accountDao;

    /*
    customer-id  -account, owner - id, total amount
     */

    //curl -v -X POST -H "Content-Type: application/json" -d "100" localhost:9000/payment/checkout/1
    //curl -v -X POST -H "Content-Type: application/json" -d "100" "http://localhost:9000/payment/checkout/ThuzaNwe15324"

   // curl -v -X POST -H "Content-Type: application/json" -d '{"name":"Thuza Nwe","totalAmount":25.5,"accountNumber":"ThuzaNwe15324"}' localhost:9000/payment/checkout
    @Transactional
    @PostMapping(value = "/checkout",consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity checkout(

                                   @RequestBody AccountInfo accountInfo) {
        Optional<Account> accountOptional = accountDao.findAccountByAccountNumber(accountInfo.getAccountNumber());

        if (!accountOptional.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        Account ownerAccount = accountDao.findById(3).get();
        Account userAccount = accountOptional.get();
        if (accountInfo.getTotalAmount() > userAccount.getAmount()) {
            return ResponseEntity
                    .badRequest()
                    .body("Insufficient Amount!");

        } else {
            transferAmount(accountInfo.getTotalAmount(), ownerAccount, userAccount);
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .build();
        }

    }

    private void transferAmount(double totalAmount, Account ownerAccount, Account userAccount) {
        userAccount.setAmount(userAccount.getAmount() - (totalAmount));

        ownerAccount.setAmount(ownerAccount.getAmount() + (totalAmount));
    }

    public double toDouble(String account) {
        return Double.parseDouble(account);
    }
    /*private String name;

    private String accountNumber;
    private double amount;
    @Enumerated(EnumType.STRING)
    private AccountType accountType;

    */

    //curl -v -X POST -H "Content-Type: application/json" -d '{"name":"John Doe","amount":10000,"accountType":"CASH"}' localhost:9000/payment/register
    //curl -v -X POST -H "Content-Type: application/json" -d '{"name":"John Doe","amount":10000,"accountType":"CASH"}' localhost:9000/payment/register

    @PostMapping(value = "/register", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity register(@RequestBody Account account) {
        account.setAccountNumber(generateId(account.getName()));
        return ResponseEntity.ok(accountDao.save(account));


    }

    public String generateId(String name) {
        // Random random=new Random();
        return removeWhiteSpace(name) + (new Random().nextInt(10000) + 10000);
    }

    private static String removeWhiteSpace(String name) {
        String n1 = "";
        if (name.contains(" ")) {
            String[] names = name.split(" ");


            for (String s : names) {
                n1 += s;
            }
        } else
            n1 = name;
        return n1;
    }
}
