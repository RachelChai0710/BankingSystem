package com.rac.banking.system.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.rac.banking.system.data.Account;

/**
 * Account Repository
 */
public interface AccountRepository extends JpaRepository<Account, Long> {
	@Query(value = "SELECT a FROM ACCOUNT a WHERE a.cusId = :cusId")
    List<Account> findByCusId(long cusId);
}