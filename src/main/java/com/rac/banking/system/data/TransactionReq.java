package com.rac.banking.system.data;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TransactionReq extends BaseReq{
	/** Receiver Account. */
	@JsonProperty("toAcc")
	private Long toAcc;
	
	/** Amount. */
	@JsonProperty("amount")
	private BigDecimal amount;

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
}
