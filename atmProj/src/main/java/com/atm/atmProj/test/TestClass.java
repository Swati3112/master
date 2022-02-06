package com.atm.atmProj.test;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.atm.atmProj.model.Account;
import com.atm.atmProj.model.AccountRepository;
import com.atm.atmProj.service.AccountService;

public class TestClass {
	@Test
	void checkIfEnough1() {
		Account acc= new Account();
		AccountService as= new AccountService();
		acc.setAccountBalance(1000);
		float bal= 2000;
		assertEquals(false, as.checkIfEnough(acc, bal));
	}
	@Test
	void checkIfEnough2() {
		Account acc= new Account();
		AccountService as= new AccountService();
		acc.setAccountBalance(1000);
		float bal= 200;
		assertEquals(true, as.checkIfEnough(acc, bal));
	}
	
	
}
