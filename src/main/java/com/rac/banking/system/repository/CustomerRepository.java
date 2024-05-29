package com.rac.banking.system.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rac.banking.system.data.Customer;

/**
 * Customer Repository
 */
public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
