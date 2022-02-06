package com.atm.atmProj.model;

import org.springframework.data.repository.CrudRepository;

public interface AccountRepository extends CrudRepository<Account, Integer> {
	public Account findByAccountNumber(int accNo);
	public Account save(Account acc);

}
