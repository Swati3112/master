package com.atm.atmProj.model;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface TransactionRepository extends CrudRepository<Transaction, Integer> {

	Transaction save(Transaction transaction);

	public List<Transaction> findAll();

	public List<Transaction> findByAccountNo(int accountNo);

	public Transaction findTopByOrderByTransactionIdDesc();

}
