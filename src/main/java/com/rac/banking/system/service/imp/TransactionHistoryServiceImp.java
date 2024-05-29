package com.rac.banking.system.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rac.banking.system.data.TransactionHistory;
import com.rac.banking.system.repository.TransactionHistoryRepository;
import com.rac.banking.system.service.TransactionHistoryService;

@Service
@Transactional
public class TransactionHistoryServiceImp extends BaseServiceImp<TransactionHistory> implements TransactionHistoryService{

	/** Transaction History Repository */
	@Autowired
	TransactionHistoryRepository txnHisRepo;
	
	@Override
	public JpaRepository<TransactionHistory, Long> getRepo() {
		return txnHisRepo;
	}

	@Override
	public List<TransactionHistory> findByAccId(long accId) {
		return txnHisRepo.findByAccId(accId);
	}
}