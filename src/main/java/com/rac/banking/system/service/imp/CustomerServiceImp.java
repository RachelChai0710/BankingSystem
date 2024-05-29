package com.rac.banking.system.service.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rac.banking.system.data.Customer;
import com.rac.banking.system.repository.CustomerRepository;
import com.rac.banking.system.service.CustomerService;

@Service
@Transactional
public class CustomerServiceImp extends BaseServiceImp<Customer> implements CustomerService{
	
	/** Customer Repository */
	@Autowired
	CustomerRepository cusRepo;

	/**
	 * @return JpaRepository<Customer, Long> customer repository
	 */
	@Override
	public JpaRepository<Customer, Long> getRepo() {
		return cusRepo;
	}
}