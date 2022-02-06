package com.atm.atmProj.model;

import javax.persistence.Id;

public class CustomerDetails {
	
	private CardDetails cardDetails;
	private float withdrawAmt;
	private float depositAmt;
	private float transferAmt;
	private Account transferAccDetails;
	public CardDetails getCardDetails() {
		return cardDetails;
	}
	public void setCardDetails(CardDetails cardDetails) {
		this.cardDetails = cardDetails;
	}
	public float getWithdrawAmt() {
		return withdrawAmt;
	}
	public void setWithdrawAmt(float withdrawAmt) {
		this.withdrawAmt = withdrawAmt;
	}
	public float getDepositAmt() {
		return depositAmt;
	}
	public void setDepositAmt(float depositAmt) {
		this.depositAmt = depositAmt;
	}
	public float getTransferAmt() {
		return transferAmt;
	}
	public void setTransferAmt(float transferAmt) {
		this.transferAmt = transferAmt;
	}
	public Account getTransferAccDetails() {
		return transferAccDetails;
	}
	public void setTransferAccDetails(Account transferAccDetails) {
		this.transferAccDetails = transferAccDetails;
	}
	
	
}
