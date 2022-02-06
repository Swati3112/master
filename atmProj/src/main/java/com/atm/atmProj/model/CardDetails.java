package com.atm.atmProj.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "card_details")
public class CardDetails {
	@Id
	private int id;
	@Column
	private String cardNo;
	@Column
	private String expiryDate;
	@Column
	private String cardType;
	
	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id", referencedColumnName = "customerId")
	private Account accountDetails;
	
	public String getCardNo() {
		return cardNo;
	}
	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}
	public String getExpiryDate() {
		return expiryDate;
	}
	public void setExpiryDate(String expiryDate) {
		this.expiryDate = expiryDate;
	}
	public String getCardType() {
		return cardType;
	}
	public void setCardType(String cardType) {
		this.cardType = cardType;
	}
	public Account getAccountDetails() {
		return accountDetails;
	}
	public void setAccountDetails(Account accountDetails) {
		this.accountDetails = accountDetails;
	}
	
	
}
