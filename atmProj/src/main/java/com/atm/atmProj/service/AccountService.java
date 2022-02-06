package com.atm.atmProj.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atm.atmProj.model.Account;
import com.atm.atmProj.model.AccountRepository;
import com.atm.atmProj.model.Transaction;
import com.atm.atmProj.model.TransactionRepository;

@Service
public class AccountService {
   
    @Autowired
    AccountRepository accountRepository;
    
    @Autowired
    TransactionRepository transactionRepository;


    public List<Account> getAllAccounts() {
        List<Account> accounts = new ArrayList<>();
        accountRepository.findAll().forEach(account -> accounts.add(account));
        return accounts;
    }


    public Account getAccountByAccountNo(int accNo) {
        Account account = accountRepository.findByAccountNumber(accNo);
        return account;
    }

   
    public void saveOrUpdate(Account account) {
        accountRepository.save(account);
    }

    
    public void delete(int acc_num) {
        accountRepository.deleteById(acc_num);
    }

    
    public boolean checkIfEnough(Account account, float amount) {
        float remaining = account.getAccountBalance() - amount;
        if (remaining >= 0) {
            return true;
        }
        return false;
    }

   
    public boolean makeTransfer(int originAccNo, int targetAccNo, float amount) {
        Account origAccount = getAccountByAccountNo(originAccNo);
        Account targetAccount = getAccountByAccountNo(targetAccNo);
        if (checkIfEnough(origAccount, amount)) {
            origAccount.setAccountBalance(origAccount.getAccountBalance() - amount);
            targetAccount.setAccountBalance(targetAccount.getAccountBalance() + amount);
            //update the two accounts after the transaction
            saveOrUpdate(origAccount);
            saveOrUpdate(targetAccount);
            //save transaction to database with current timestamp
            Transaction transaction = new Transaction();
            transaction.setAccountNo(originAccNo);
            transaction.setTime(new Timestamp(new Date().getTime()));
            transaction.setType("transfer from");
            transaction.setAmount(amount);
            transactionRepository.save(transaction);
            Transaction transaction2 = new Transaction();
            transaction2.setAccountNo(targetAccNo);
            transaction2.setTime(new Timestamp(new Date().getTime()));
            transaction2.setType("transfer to");
            transaction2.setAmount(amount);
            transactionRepository.save(transaction2);

            return true;
        } else {
            return false;
        }
    }

    
    public boolean withdrawMoney(int accountNo, float amount) {
        Account account = getAccountByAccountNo(accountNo);
        //check if account has sufficient balance
        if (checkIfEnough(account, amount)) {
            account.setAccountBalance(account.getAccountBalance() - amount);
            saveOrUpdate(account);
            //save transaction to database
            Transaction transaction = new Transaction();
            transaction.setAccountNo(accountNo);
            transaction.setTime(new Timestamp(new Date().getTime()));
            transaction.setType("withdraw");
            transaction.setAmount(amount);
            transactionRepository.save(transaction);
            return true;
        }
        return false;
    }

    

    public boolean depositMoney(int accountNo, float amount) {
        Account account = getAccountByAccountNo(accountNo);
        account.setAccountBalance(account.getAccountBalance() + amount);
        saveOrUpdate(account);
        // save transaction to database
        Transaction transaction = new Transaction();
        transaction.setAccountNo(accountNo);
        transaction.setTime(new Timestamp(new Date().getTime()));
        transaction.setType("deposit");
        transaction.setAmount(amount);
        transactionRepository.save(transaction);
        return true;
    }

    public float getBalance(int accountNum) {
        Account account = getAccountByAccountNo(accountNum);
        return account.getAccountBalance();
    }

}
