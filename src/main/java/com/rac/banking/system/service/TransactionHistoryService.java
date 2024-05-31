package com.rac.banking.system.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.rac.banking.system.data.TransactionHistory;

public interface TransactionHistoryService extends BaseService<TransactionHistory> {
	/**
	 * Find account by account ID
	 * @param accId
	 * @return the transaction histories with the customer ID
	 */
	public List<TransactionHistory> findByAccId(long accId);
	
	/**
	 * Find account by account ID Pagination
	 * @param accId
	 * @return the page of transaction histories with the customer ID
	 */
	public Page<TransactionHistory> findAllByAccIdP(long accId, int pageNo);
}