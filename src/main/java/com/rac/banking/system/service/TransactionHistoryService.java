package com.rac.banking.system.service;

import java.util.List;

import com.rac.banking.system.data.TransactionHistory;

public interface TransactionHistoryService extends BaseService<TransactionHistory> {
	/**
	 * Find account by account ID
	 * @param accId
	 * @return the transaction histories with the customer ID
	 */
	public List<TransactionHistory> findByAccId(long accId);
}