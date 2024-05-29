package com.rac.banking.system.service;

import java.math.BigDecimal;
import java.util.List;
import com.rac.banking.system.data.Account;

public interface AccountService extends BaseService<Account>{
	
	/**
	 * Find account by customer ID
	 * @param cusId
	 * @return the account with the customer ID
	 */
	public List<Account> findByCusId(long cusId);
	
	/**
	 * Deposit to account
	 * @param accId the account ID
	 * @param amount the amount to deposit
	 * @param isTransfer indicator to show whether is called from transfer transaction
	 * @return the updated account
	 * @throws Exception
	 */
	public Account deposit(long accId, BigDecimal amount, boolean isTransfer)
			throws Exception;
	
	/**
	 * Withdraw from account
	 * @param accId the account ID
	 * @param amount the amount to withdraw
	 * @param isTransfer indicator to show whether is called from transfer transaction
	 * @return the updated account
	 * @throws Exception
	 */
	public Account withdraw(long accId, BigDecimal amount, boolean isTransfer) 
			throws Exception;
	
	/**
	 * Transfer fund
	 * @param srcAccId the source account id
	 * @param toAccId the receiver account id
	 * @param amount the amount to transfer
	 * @return the updated account
	 * @throws Exception
	 */
	public Account transfer(long srcAccId, long toAccId, BigDecimal amount)
			throws Exception;

}