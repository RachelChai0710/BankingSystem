package com.rac.banking.system.rest;

import java.math.BigDecimal;
import java.util.List;
import java.util.NoSuchElementException;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
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
import com.rac.banking.system.data.AccountReq;
import com.rac.banking.system.data.CreditAccount;
import com.rac.banking.system.data.TransactionHistory;
import com.rac.banking.system.service.AccountService;
import com.rac.banking.system.service.TransactionHistoryService;
import com.rac.banking.system.service.imp.AccountServiceImp;

@RestController
@RequestMapping("/acc")
public class AccountController extends BaseController {	
	
	/** Account Service */
	@Autowired
	private AccountService accSrv;
	
	/** Transaction History Service */
	@Autowired
	private TransactionHistoryService txnSrv;
	
	/** Account Type - Saving */
	private final static String ACC_T_SVG = "SAVING";
	
	/** Account Type - Credit */
	private final static String ACC_T_CRD = "CREDIT";
	
	/** Account Types */
	private final static String[] ACC_T = {
			ACC_T_SVG, ACC_T_CRD
	};
	
	/**
	 * Sets the logger for this controller.
	 */
	@Override
	protected void setLogger() {
		this.log = LoggerFactory.getLogger(AccountController.class);
	}

	/**
	 * Handles HTTP GET request to retrieve a account by customer ID.
	 * @param id The ID of the customer to retrieve.
	 * @return ResponseEntity containing the accounts or an error message if not found.
	 */
	@GetMapping("/get/{cusId}")
	public ResponseEntity<Object> getAccountByCusId(@PathVariable("cusId") int cusId){
		List<Account> accList = accSrv.findByCusId((long)cusId);
		if(CollectionUtils.isEmpty(accList)) {
			return getRes("Cannot find any account with customer ID:", HttpStatus.NOT_FOUND, true);
		}
		return getRes(accList, HttpStatus.OK, false);
	}
	
	/**
	 * Handles HTTP GET request to retrieve account's balance by ID.
	 * @param id The ID of the account to retrieve.
	 * @return ResponseEntity containing the balance or an error message if not found.
	 */
	@GetMapping("/balance_inq/{id}")
	public ResponseEntity<Object> getBalanceById(@PathVariable("id") int id){
		Account acc;
		try {
			acc = accSrv.findById((long)id).orElseThrow(()-> new NoSuchElementException("Cannot find account with id:" + id));
		} catch (NoSuchElementException e){
			this.log.error("Account is not found", e);
			return getRes(e.getMessage(), HttpStatus.NOT_FOUND, true);
		} catch (Exception e){
			this.log.error("Error Occurred when processing", e);
			return getRes(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, true);
		}
		return getRes(acc.getBalance(), HttpStatus.OK, false);
	}
	
	/**
	 * Handles HTTP POST request to add a new account.
	 * @param req The request body containing the customer data.
	 * @return ResponseEntity containing the added customer or an error message.
	 */
	@PostMapping("/add")
	public ResponseEntity<Object> addAcc(@RequestBody AccountReq req){
		this.logReqNRes(req, REQ);
		if (req.getCusId() == 0) {
            return getRes("Customer ID is required", HttpStatus.BAD_REQUEST, true);
        }
		if (req.getAccT() == null || req.getAccT().isEmpty() || !ArrayUtils.contains(ACC_T, req.getAccT()) ) {
            return getRes("Account is required. Please enter a valid account type(SAVING/CREDIT)", HttpStatus.BAD_REQUEST, true);
        }
		
		Account savedAcc;
		try {
			Account acc = null;
			switch(req.getAccT()) {
				case ACC_T_SVG:
					if (req.getBalance() == null || req.getBalance().compareTo(BigDecimal.ZERO) <= 0 ) {
			            return getRes("Balance is required. Please enter a positive initial balance", HttpStatus.BAD_REQUEST, true);
			        }
					acc = new Account(req.getCusId(), req.getBalance(), req.getAccT());
					break;
				case ACC_T_CRD:
					if (req.getCardNo() == null || req.getCardNo().isEmpty() ) {
			            return getRes("Card number is required.", HttpStatus.BAD_REQUEST, true);
			        }
					if (req.getCardNo().length() != 16 || !StringUtils.isNumeric(req.getCardNo())) {
			            return getRes("Card number must be 16 digits.", HttpStatus.BAD_REQUEST, true);
			        }
					if (req.getCreLim() == null || req.getCreLim().compareTo(BigDecimal.ZERO) <= 0 ) {
			            return getRes("Credit limit is required. Please enter a positive credit limit", HttpStatus.BAD_REQUEST, true);
			        }
					// Default balance to credit limit
					acc = new CreditAccount(req.getCusId(), req.getCreLim(), req.getAccT(), req.getCardNo(), req.getCreLim());
					break;	
			}
			savedAcc = accSrv.add(acc);
			if(StringUtils.equals(req.getAccT(), ACC_T_SVG)) {
				txnSrv.add(new TransactionHistory(savedAcc.getAccId(), savedAcc.getBalance(), AccountServiceImp.TXN_T_D));
			}
		} catch (Exception e){
			this.log.error("Error Occurred when processing", e);
			return getRes(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, true);
		}
        return  getRes(savedAcc, HttpStatus.CREATED, false);
	}
}