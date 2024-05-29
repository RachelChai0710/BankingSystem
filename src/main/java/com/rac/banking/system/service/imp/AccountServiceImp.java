package com.rac.banking.system.service.imp;

import java.math.BigDecimal;
import java.util.List;
import java.util.NoSuchElementException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rac.banking.system.data.Account;
import com.rac.banking.system.data.TransactionHistory;
import com.rac.banking.system.repository.AccountRepository;
import com.rac.banking.system.service.AccountService;
import com.rac.banking.system.service.TransactionHistoryService;

@Service
@Transactional
public class AccountServiceImp extends BaseServiceImp<Account> implements AccountService{
	
	/** Account Repository */
	@Autowired
	private AccountRepository accRepo;
	
	/** Transaction History Service */
	@Autowired
	private TransactionHistoryService txnSrv;
	
	/** Transaction Type - Deposit */
	public final static String TXN_T_D = "DEPOSIT";
	
	/** Transaction Type - Transfer */
	public final static String TXN_T_T = "TRANSFER";
	
	/** Transaction Type - Withdraw */
	public final static String TXN_T_W = "WITHDRAW";
	
	/** Max retry count */
	private final static int MAX_RETRY = 0;

	private Logger logger = LoggerFactory.getLogger(AccountServiceImp.class);

	@Override
	public JpaRepository<Account, Long> getRepo() {
		return accRepo;
	}

	@Override
	public List<Account> findByCusId(long cusId) {
		return accRepo.findByCusId(cusId);
	}

	@Override
	public Account deposit(long accId, BigDecimal amount, boolean isTransfer) throws Exception{
		Account savedAcc = null;
		try {
			Account acc= addBalance(accId, amount);
			savedAcc = this.update(acc);
		} catch (ObjectOptimisticLockingFailureException e){
			for(int i = 1; i <= MAX_RETRY; i++) {
				logger.info("Retring for "+ i + " time(s)");
				savedAcc = this.update(addBalance(accId, amount));
				if(null != savedAcc) {
					break;
				}
			}
			if(null == savedAcc) {
				throw new RuntimeException("Hit Maximun Retry Count");
			}
		} catch(Exception e) {
			throw e;
		}
		if(!isTransfer) {
			addTxnH(savedAcc.getAccId(), null, amount, TXN_T_D);
		}
		return savedAcc;
	}

	@Override
	public Account withdraw(long accId, BigDecimal amount, boolean isTransfer) throws Exception  {
		Account savedAcc = null;
		try {
			savedAcc = this.update(subtractBalance(accId, amount));
		} catch (ObjectOptimisticLockingFailureException e){
			for(int i = 0; i < MAX_RETRY; i++) {
				logger.info("Retring for "+ i + "time(s)");
				savedAcc = this.update(subtractBalance(accId, amount));
				if(null != savedAcc) {
					break;
				}
			}
			if(null == savedAcc) {
				throw new RuntimeException("Hit Maximun Retry Count");
			}
		} catch(Exception e) {
			throw e;
		}
		if(!isTransfer) {
			addTxnH(savedAcc.getAccId(), null, amount, TXN_T_W);
		}
		return savedAcc;
	}

	@Override
	public Account transfer(long srcAccId, long toAccId, BigDecimal amount) throws Exception {
		Account savedAcc = withdraw(srcAccId, amount, true);
		deposit(toAccId, amount, true);
		addTxnH(savedAcc.getAccId(), toAccId, amount, TXN_T_T);
		return savedAcc;
	}

	/**
	 * Add Transaction History
	 * @param accId
	 * @param srcAcc
	 * @param amount
	 * @param txnT
	 */
	protected void addTxnH(long accId, Long srcAcc, BigDecimal amount, String txnT) {
		txnSrv.add(new TransactionHistory(accId, srcAcc, amount, txnT));
	}
	
	/**
	 * Calculate the balance for the respective account
	 * for deposit transaction
	 * @param accId
	 * @param amount
	 * @return updated account
	 */
	protected Account addBalance(long accId, BigDecimal amount) {
		Account acc = this.findById((long)accId).orElseThrow(()-> new NoSuchElementException("Cannot find account with id:" + accId));
		acc.setBalance(acc.getBalance().add(amount));
		return acc;
	}
	
	/**
	 * Calculate the balance for the respective account
	 * for withdrawal transaction
	 * @param accId
	 * @param amount
	 * @return updated account
	 */
	protected Account subtractBalance(long accId, BigDecimal amount) {
		Account acc = this.findById((long)accId).orElseThrow(()-> new NoSuchElementException("Cannot find account with id:" + accId));
		
		BigDecimal balance = acc.getBalance();
		if(balance.compareTo(amount) < 0) {
			throw new IllegalArgumentException("Insufficient balance");
		}
		acc.setBalance(balance.subtract(amount));
		return acc;
	}
}