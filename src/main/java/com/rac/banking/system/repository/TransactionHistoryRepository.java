package com.rac.banking.system.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.rac.banking.system.data.TransactionHistory;

/**
 * Transaction History Repository
 */
public interface TransactionHistoryRepository extends JpaRepository<TransactionHistory, Long> {
	@Query(value = "SELECT DISTINCT txn FROM TransactionHistory txn WHERE txn.accId = :accId OR txn.toAcc = :accId")
    List<TransactionHistory> findByAccId(long accId);
	
	@Query(value = "SELECT txn FROM TransactionHistory txn WHERE txn.accId = :accId OR txn.toAcc = :accId")
	Page<TransactionHistory>  findByAccIdPage(long accId, Pageable pageable); 	
}
