package com.atm.atmProj.controller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.atm.atmProj.model.Account;
import com.atm.atmProj.model.CardDetails;
import com.atm.atmProj.model.CustomerDetails;
import com.atm.atmProj.model.Error;
import com.atm.atmProj.service.AccountService;
import com.atm.atmProj.service.TransactionService;

@RestController
public class ATMController {

	@Autowired
	AccountService accountService;
	@Autowired
	TransactionService transactionService;

	@PostMapping("/check-balance")
	public Map<String, Object> checkBalance(@RequestBody CardDetails cardDetails) {
		Map map = new HashMap<String, Object>();
		try {

			float bal = accountService.getBalance(cardDetails.getAccountDetails().getAccountNumber());
			map.put("balance", bal);
			return map;
		}catch(NullPointerException e) {
			Error err = new Error();
			err.setErrorCode("err-001");
			err.setErrorMessage("user doesn't exist");
			map.put("error_details", err);
			return map;
		}
		catch (Exception e) {
			// TODO: handle exception
			Error err = new Error();
			err.setErrorCode("err-002");
			err.setErrorMessage(e.getMessage());
			map.put("error_details", err);
			return map;
		}
	}

	@PostMapping("/withdraw")
	public Map<String, Object> withdraw(@RequestBody CustomerDetails custDetails) {
		Map map = new HashMap<String, Object>();
		try {
			boolean isWithdraw = accountService.withdrawMoney(
					custDetails.getCardDetails().getAccountDetails().getAccountNumber(), custDetails.getWithdrawAmt());
			if (isWithdraw) {
				float bal = accountService
						.getBalance(custDetails.getCardDetails().getAccountDetails().getAccountNumber());
				map.put("balance", bal);
				map.put("message", "You withdrew rs" + custDetails.getWithdrawAmt());
			} else {
				Error e = new Error();
				e.setErrorCode("101");
				e.setErrorMessage("Insufficient Balance!");
				map.put("errorDetails", e);
			}
			return map;
		}
		catch(NullPointerException e) {
			Error err = new Error();
			err.setErrorCode("err-001");
			err.setErrorMessage("user doesn't exist");
			map.put("error_details", err);
			return map;
		}
		catch (Exception e) {
			// TODO: handle exception
			Error err = new Error();
			err.setErrorCode("err-002");
			err.setErrorMessage(e.getMessage());
			map.put("error_details", err);
			return map;
		}
	}

	@PostMapping("/transfer")
	public Map<String, Object> transferMoney(@RequestBody CustomerDetails custDetails) {

		Map map = new HashMap<String, Object>();
		try {
			boolean isTrafered = accountService.makeTransfer(
					custDetails.getCardDetails().getAccountDetails().getAccountNumber(),
					custDetails.getTransferAccDetails().getAccountNumber(), custDetails.getTransferAmt());
			if (isTrafered) {
				float bal = accountService
						.getBalance(custDetails.getCardDetails().getAccountDetails().getAccountNumber());

				map.put("balance", bal);
				map.put("message", "transfered rs."+custDetails.getTransferAmt()+"to the account number "+ custDetails.getTransferAccDetails().getAccountNumber());
			} else {
				Error e = new Error();
				e.setErrorCode("101");
				e.setErrorMessage("Insufficient Balance!");
				map.put("error details", e);
			}
			return map;
		} catch(NullPointerException e) {
			Error err = new Error();
			err.setErrorCode("err-001");
			err.setErrorMessage("user doesn't exist");
			map.put("error_details", err);
			return map;
		}
		catch (Exception e) {
			// TODO: handle exception
			Error err = new Error();
			err.setErrorCode("err-002");
			err.setErrorMessage(e.getMessage());
			map.put("error_details", err);
			return map;
		}
	}

	@PostMapping("/deposit")
	public Map<String, Object> depositMoney(@RequestBody CustomerDetails custDetails) {
		Map map = new HashMap<String, Object>();
		try {
			
			boolean deposited = accountService.depositMoney(
					custDetails.getCardDetails().getAccountDetails().getAccountNumber(), custDetails.getDepositAmt());
			float balance = accountService
					.getBalance(custDetails.getCardDetails().getAccountDetails().getAccountNumber());
			if (deposited) {
				map.put("message", "You deposited rs." + custDetails.getDepositAmt());
				map.put("balance", balance);
				map.put("accountNum", custDetails.getCardDetails().getAccountDetails().getAccountNumber());
			} else {
				Error e = new Error();
				e.setErrorCode("102");
				e.setErrorMessage("Error while depositing.");
				map.put("error details", e);
			}
			return map;
		} catch(NullPointerException e) {
			Error err = new Error();
			err.setErrorCode("err-001");
			err.setErrorMessage("user doesn't exist");
			map.put("error_details", err);
			return map;
		}
		catch (Exception e) {
			// TODO: handle exception
			Error err = new Error();
			err.setErrorCode("err-002");
			err.setErrorMessage(e.getMessage());
			map.put("error_details", err);
			return map;
		}
	}

}