package com.rac.banking.system.data;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Inheritance;
import jakarta.persistence.Table;

/**
 * Credit Account Data Class
 */
@Entity
@Table(name="ACCOUNT")
@Inheritance
public class CreditAccount extends Account{

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -8193345030143817200L;
	
	/**
	 * Card Number
	 */
	@Column(name = "CARD_NO", length = 16)
	private String cardNo;
	
	/**
	 * Credit Limit
	 */
	@Column(name = "CREDIT_LIMIT")
	private BigDecimal creLim;

	/**
     * Default constructor.
     */
	public CreditAccount() {
		super();
	}
	
	/**
     * Construct a new credit account with the specified customer ID, balance,
     * account type, card number and credit limit.
     *
     * @param cusId the customer ID for the account
     * @param balance initial balance
     * @param accT account type
     * @param cardNo the card number for the account
     * @param creLim the credit limit for the account
     */
	public CreditAccount(long cusId, BigDecimal balance, String accT, String cardNo, BigDecimal creLim) {
		super(cusId, balance, accT);
		this.cardNo = cardNo;
		this.creLim = creLim;
	}

	/**
     * Return the card number of the account.
     *
     * @return the card number of the account
     */
	public String getCardNo() {
		return cardNo;
	}

	/**
     * Set the card number of the account.
     *
     * @param cardNo the card number of the account to set
     */
	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}

	/**
     * Return the credit limit of the account.
     *
     * @return the credit limit of the account
     */
	public BigDecimal getCreLim() {
		return creLim;
	}

	/**
     * Set the credit limit of the account.
     *
     * @param creLim the credit limit of the account to set
     */
	public void setCreLim(BigDecimal creLim) {
		this.creLim = creLim;
	}
}