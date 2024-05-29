package com.rac.banking.system.rest;

import java.util.List;
import java.util.NoSuchElementException;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rac.banking.system.data.Customer;
import com.rac.banking.system.data.CustomerReq;
import com.rac.banking.system.service.BaseService;
import com.rac.banking.system.service.CustomerService;

/**
 * Controller class for handling customer-related HTTP requests.
 * Extends {@link BaseController} to inherit common functionality, 
 */
@RestController
@RequestMapping("/cus")
public class CustomerController extends BaseController{	
	
	/** Customer Service */
	@Autowired
	private CustomerService cusSrv;

	@Override
	protected void setLogger() {
		this.log = LoggerFactory.getLogger(CustomerController.class);
	}
	
	/**
	 * Handles HTTP GET request to retrieve all customers.
	 * @return ResponseEntity containing the list of customers or an error message.
	 */
	@GetMapping("/getAll")
	public ResponseEntity<Object> getAllCustomers(){
		this.logReqNRes("{}", REQ);
		List<Customer> customers = cusSrv.findAll();
		if(null == customers || CollectionUtils.isEmpty(customers)) {			
			return getRes("No customer in database", HttpStatus.NOT_FOUND, true);
		}
		
		return getRes(customers, HttpStatus.OK, false);
	}

	/**
	 * Handles HTTP GET request to retrieve a customer by ID.
	 * @param id The ID of the customer to retrieve.
	 * @return ResponseEntity containing the customer or an error message if not found.
	 */
	@GetMapping("/get/{id}")
	public ResponseEntity<Object> getCustomerById(@PathVariable("id") int id){
		Customer cus;
		try {
			cus = cusSrv.findById((long)id).orElseThrow(()-> new NoSuchElementException("Cannot find customer with id:" + id));
		} catch(NoSuchElementException e){
			this.log.error("Customer is not found", e);
			return getRes(e.getMessage(), HttpStatus.NOT_FOUND, true);
		} catch (Exception e){
			this.log.error("Error Occurred when processing", e);
			return getRes(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, true);
		}

		return getRes(cus, HttpStatus.OK, false);
	}
	
	/**
	 * Handles HTTP PUT request to update a customer.
	 * @param id The ID of the customer to update.
	 * @param req The request body containing the updated customer data.
	 * @return ResponseEntity containing the updated customer or an error message.
	 */
	@PutMapping("/upd/{id}")
	public ResponseEntity<Object> updCus(@PathVariable("id") int id, @RequestBody CustomerReq req){
		this.logReqNRes(req, REQ);
		Customer cus;
		try {
			cus = cusSrv.findById((long)id).orElseThrow(()-> new NoSuchElementException("Cannot find customer with id:" + id));
			if(StringUtils.isNotBlank(req.getName())) {
				cus.setName(req.getName());
			}
			if(0 != req.getAge()) {
				cus.setAge(req.getAge());
			}
			cusSrv.update(cus);
		} catch (NoSuchElementException e){
			this.log.error("Customer is not found", e);
			return getRes(e.getMessage(), HttpStatus.NOT_FOUND, true);
		} catch (Exception e){
			this.log.error("Error Occurred when processing", e);
			return getRes(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, true);
		}
		return  getRes(cus, HttpStatus.OK, false);
	}
	
	/**
	 * Handles HTTP POST request to add a new customer.
	 * @param req The request body containing the customer data.
	 * @return ResponseEntity containing the added customer or an error message.
	 */
	@PostMapping("/add")
	public ResponseEntity<Object> addCus(@RequestBody CustomerReq req){
		this.logReqNRes(req, REQ);
		if (req.getName() == null || req.getName().isEmpty()) {
            return getRes("Name is required", HttpStatus.BAD_REQUEST, true);
        }
		
		Customer savedCustomer;
		try {
			savedCustomer = cusSrv.add(new Customer(req.getName(), req.getAge()));
		} catch (Exception e){
			this.log.error("Error Occurred when processing", e);
			return getRes(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, true);
		}
        return  getRes(savedCustomer, HttpStatus.CREATED, false);
	}
}