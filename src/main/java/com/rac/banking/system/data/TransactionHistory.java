package com.rac.banking.system.data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * Transaction History Data Class
 */
@Entity
@Table(name="TRANSACTION_HISTORY")
public class TransactionHistory implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -6171855148081154414L;

	/**
	 * Transaction History ID. 
	 * Primary Key
	 */
	@Id
	@Column(name = "TXN_H_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected long txnHId; 
	
	/**
	 * Created Date. 
	 * Non-nullable field.
	 */
	@Column(name = "CRT_DT", nullable=false, insertable = false, updatable = false, columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
	protected Date crtDt;
	
	/**
	 * Account ID
	 * Foreign Key to Account Table
	 * Non-nullable field
	 * Many to one relationship
	 */
	@Column(name = "ACC_ID", nullable=false)
	protected long accId;
	
	/**
	 * Receiver Account. 
	 */
	@Column(name = "TO_ACC")
	protected Long toAcc;
	
	/**
	 * Amount.
	 * Non-nullable field 
	 */
	@Column(nullable=false)
	protected BigDecimal amount;
	
	/**
	 * Transaction Type. 
	 * Non-nullable field
	 */
	@Column(name = "TXN_T", nullable=false)
	protected String txnT;

	/**
     * Default constructor.
     */
	public TransactionHistory() {
	}

	/**
     * Construct a new transaction history with the specified account ID,
     * source account, amount and transaction type.
     *
     * @param accId the account ID for the transaction history
     * @param toAcc receiver account
     * @param amount amount
     * @param txnT transaction type
     */
	public TransactionHistory(long accId, Long toAcc, BigDecimal amount, String txnT) {
		this.accId = accId;
		this.toAcc = toAcc;
		this.amount = amount;
		this.txnT = txnT;
	}
	
	/**
     * Construct a new transaction history with the specified account ID,
     * amount and transaction type.
     *
     * @param accId the account ID for the transaction history
     * @param amount amount
     * @param txnT transaction type
     */
	public TransactionHistory(long accId, BigDecimal amount, String txnT) {
		this(accId, null, amount, txnT);
	}

	/**
     * Return the unique identifier of the transaction history.
     *
     * @return the unique identifier of the transaction history
     */
	public long getTxnHId() {
		return txnHId;
	}

	/**
     * Set the unique identifier of the transaction history.
     *
     * @param txnHId the unique identifier of the transaction history to set
     */
	public void setTxnHId(long txnHId) {
		this.txnHId = txnHId;
	}

	/**
     * Return the created date of the transaction history.
     *
     * @return the created date of the transaction history
     */
	public Date getCrtDt() {
		return crtDt;
	}

	/**
     * Set the created date of the transaction history.
     *
     * @param crtDt the created date of the transaction history to set
     */
	public void setCrtDt(Date crtDt) {
		this.crtDt = crtDt;
	}

	/**
     * Return the account ID of the transaction history.
     *
     * @return the account ID of the transaction history
     */
	public long getAccId() {
		return accId;
	}

	/**
     * Set the account ID of the transaction history.
     *
     * @param accId the account ID of the transaction history to set
     */
	public void setAccId(long accId) {
		this.accId = accId;
	}

	/**
     * Return the receiver account of the transaction history.
     *
     * @return the receiver account of the transaction history
     */
	public Long getToAcc() {
		return toAcc;
	}

	/**
     * Set the receiver account of the transaction history.
     *
     * @param toAcc the receiver account of the transaction history to set
     */
	public void setToAcc(Long toAcc) {
		this.toAcc = toAcc;
	}

	/**
     * Return the amount of the transaction history.
     *
     * @return the amount of the transaction history
     */
	public BigDecimal getAmount() {
		return amount;
	}

	/**
     * Set the amount of the transaction history.
     *
     * @param amount the amount of the transaction history to set
     */
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	/**
     * Return the transaction type of the transaction history.
     *
     * @return the transaction type of the transaction history
     */
	public String getTxnT() {
		return txnT;
	}

	/**
     * Set the transaction type of the transaction history.
     *
     * @param txnT the transaction type of the transaction history to set
     */
	public void setTxnT(String txnT) {
		this.txnT = txnT;
	}
}