package com.rac.banking.system.data;

import java.io.Serializable;
import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Version;

/**
 * Account Data Class
 */
@Entity(name="ACCOUNT")
@Table
public class Account implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 2610079655631610863L;
	
	/**
	 * Account ID. 
	 * Primary Key
	 */
	@Id
	@Column(name = "ACC_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected long accId; 
	
	/**
	 * Customer ID
	 * Foreign Key to Customer Table
	 * Non-nullable field
	 * Many to one relationship
	 */
	@Column(name = "CUS_ID", nullable=false)
	protected long cusId;
	
	/** 
	 * Account balance. 
	 * Non-nullable field.
	 */
	@Column(nullable=false)
	protected BigDecimal balance;
	
	/**
	 * Account Type
	 * Non-nullable field.
	 */
	@Column(name = "ACC_T",nullable=false)
	protected String accT;
	
	/**
	 * Version for Optimistic Locking
	 */
	@Version
    private Long version;

	/**
     * Default constructor.
     */
	public Account() {
	}
	
	/**
     * Construct a new account with the specified customer ID and balance.
     *
     * @param cusId the customer ID for the account
     * @param balance initial balance
     * @param accT account type
     */
	public Account(long cusId, BigDecimal balance, String accT) {
		this.cusId = cusId;
		this.balance = balance;
		this.accT = accT.toUpperCase();
	}

	 /**
     * Return the unique identifier of the account.
     *
     * @return the unique identifier of the account
     */
	public long getAccId() {
		return accId;
	}

	 /**
     * Set the unique identifier of the account.
     *
     * @param accId the unique identifier of the account to set
     */
	public void setAccId(long accId) {
		this.accId = accId;
	}

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
		this.accT = accT;
	}
}