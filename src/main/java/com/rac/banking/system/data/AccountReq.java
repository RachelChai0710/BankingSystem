package com.rac.banking.system.data;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Account Request Details.
 */
public class AccountReq extends BaseReq{
	/** Customer ID. */
	@JsonProperty("cusId")
	private long cusId;
	
	/** Balance. */
	@JsonProperty("balance")
	private BigDecimal balance;
	
	/** Account Type. */
	@JsonProperty("accT")
	private String accT;
	
	/** Card Number. */
	@JsonProperty("cardNo")
	private String cardNo;
	
	/** Credit Limit. */
	@JsonProperty("creLim")
	private BigDecimal creLim;
	
	/**
     * Return the customer ID for the account.
     *
     * @return the customer ID for the account
     */
	public long getCusId() {
		return cusId;
	}

	 /**
     * Set the customer ID for the account
     *
     * @param cusId the customer ID for the account to set
     */
	public void setCusId(long cusId) {
		this.cusId = cusId;
	}

	/**
     * Return the balance of the account.
     *
     * @return the balance of the account
     */
	public BigDecimal getBalance() {
		return balance;
	}

	/**
     * Set the balance of the account.
     *
     * @param balance the balance of the account to set
     */
	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}

	/**
     * Return the account type of the account.
     *
     * @return the account type of the account
     */
	public String getAccT() {
		return accT;
	}

	/**
     * Set the account type of the account.
     *
     * @param accT the account type of the account to set
     */
	public void setAccT(String accT) {
		this.accT = accT.toUpperCase();
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
