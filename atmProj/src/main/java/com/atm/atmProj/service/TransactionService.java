package com.atm.atmProj.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atm.atmProj.model.Transaction;
import com.atm.atmProj.model.TransactionRepository;

@Service
public class TransactionService {
    @Autowired
    TransactionRepository transactionRepository;

    /**
     * Function to save a transaction to the repository
     * @param transaction
     */
    public void save(Transaction transaction) {
        transactionRepository.save(transaction);
    }

    public Transaction getTransaction() {
        Transaction t = transactionRepository.findTopByOrderByTransactionIdDesc();
        return t;
    }
}