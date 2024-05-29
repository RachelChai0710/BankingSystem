package com.rac.banking.system.rest;

import java.util.List;
import java.util.NoSuchElementException;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rac.banking.system.data.Account;
import com.rac.banking.system.data.TransactionHistory;
import com.rac.banking.system.data.TransactionReq;
import com.rac.banking.system.service.AccountService;
import com.rac.banking.system.service.TransactionHistoryService;

/**
 * Controller class for handling transaction-related HTTP requests.
 * Extends {@link BaseController} to inherit common functionality, 
 */
@RestController
@RequestMapping("/txn")
public class TransactionController extends BaseController {
	/** Account Service */
	@Autowired
	private AccountService accSrv;
	
	/** Transaction History Service */
	@Autowired
	private TransactionHistoryService txnSrv;
	
	/**
	 * Sets the logger for this controller.
	 */
	@Override
	protected void setLogger() {
		this.log = LoggerFactory.getLogger(TransactionController.class);
	}
	
	/**
	 * Handles HTTP GET request to retrieve account's transaction history by account ID.
	 * @param id The ID of the account to retrieve.
	 * @return ResponseEntity containing the list of transaction histories or an error message
	 * if not found.
	 */
	@GetMapping("/history/{accId}")
	public ResponseEntity<Object> getTxnHById(@PathVariable("accId") int accId){
		List<TransactionHistory> txnList = txnSrv.findByAccId((long)accId);
		if(CollectionUtils.isEmpty(txnList)) {
			return getRes("Cannot find account with id:" + accId, HttpStatus.NOT_FOUND, true);
		}
		return getRes(txnList, HttpStatus.OK, false);
	}
	
	/**
	 * Handles HTTP POST request to deposit to the account.
	 * @param accId the account id of the account to deposit
	 * @param req The request body containing the transaction data.
	 * @return ResponseEntity containing the updated account or an error message.
	 */
	@PostMapping("/deposit/{accId}")
	public ResponseEntity<Object> deposit(@PathVariable("accId") long accId, @RequestBody TransactionReq req){
		this.logReqNRes(req, REQ);
		Account savedAcc = null;
		try {
			savedAcc = accSrv.deposit(accId, req.getAmount(), false);
		} catch(NoSuchElementException  e) {
			this.log.error("Account is not found", e);
			return getRes(e.getMessage(), HttpStatus.NOT_FOUND, true);
		} catch(Exception e) {
			this.log.error("Error Occurred when processing", e);
			return getRes(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, true);
		}
		return getRes(savedAcc, HttpStatus.OK, false);
	}
	
	/**
	 * Handles HTTP POST request to withdraw to the account.
	 * @param accId the account id of the account to withdraw
	 * @param req The request body containing the transaction data.
	 * @return ResponseEntity containing the updated account or an error message.
	 */
	@PostMapping("/withdraw/{accId}")
	public ResponseEntity<Object> withdraw(@PathVariable("accId") long accId, @RequestBody TransactionReq req){
		this.logReqNRes(req, REQ);
		Account savedAcc = null;
		try {
			savedAcc = accSrv.withdraw(accId, req.getAmount(), false);
		} catch(NoSuchElementException  e) {
			this.log.error("Account is not found", e);
			return getRes(e.getMessage() + accId, HttpStatus.NOT_FOUND, true);
		} catch(Exception e) {
			this.log.error("Error Occurred when processing", e);
			return getRes(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, true);
		}
		return getRes(savedAcc, HttpStatus.OK, false);
	}
	
	/**
	 * Handles HTTP POST request to transfer to the account.
	 * @param accId the account id of the account to transfer
	 * @param req The request body containing the transaction data.
	 * @return ResponseEntity containing the updated account or an error message.
	 */
	@PostMapping("/transfer/{accId}")
	public ResponseEntity<Object> transfer(@PathVariable("accId") long accId, @RequestBody TransactionReq req){
		this.logReqNRes(req, REQ);
		Account savedAcc = null;
		try {
			savedAcc = accSrv.transfer(accId, req.getToAcc(), req.getAmount());
		} catch(NoSuchElementException  e) {
			this.log.error("Account is not found", e);
			return getRes(e.getMessage(), HttpStatus.NOT_FOUND, true);
		} catch(Exception e) {
			this.log.error("Error Occurred when processing", e);
			return getRes(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, true);
		}
		return getRes(savedAcc, HttpStatus.OK, false);
	}
}
