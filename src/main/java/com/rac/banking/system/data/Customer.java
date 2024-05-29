package com.rac.banking.system.data;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * Customer Data Class
 */
@Entity
@Table
public class Customer implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -2906833822036075568L;
	
	/**
	 * Customer ID. 
	 * Primary Key
	 */
	@Id
	@Column(name = "CUS_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected long cusId; 

	/**
	 * Customer's Name. 
	 * Non-nullable field.
	 */
	@Column(nullable=false)
	protected String name;
	
	/**
	 * Customer's Age. 
	 */
	@Column
	protected int age;
	
	/**
     * Default constructor.
     */
    public Customer() {
        
    }

    /**
     * Construct a new customer with the specified name and age.
     *
     * @param name the name of the customer
     * @param age the age of the customer
     */
    public Customer(String name, int age) {
        this.name = name;
        this.age = age;
    }

    /**
     * Return the unique identifier of the customer.
     *
     * @return the unique identifier of the customer
     */
    public long getCusId() {
        return cusId;
    }

    /**
     * Set the unique identifier of the customer.
     *
     * @param cusId the unique identifier of the customer to set
     */
    public void setCusId(long cusId) {
        this.cusId = cusId;
    }

    /**
     * Return the name of the customer.
     *
     * @return the name of the customer
     */
    public String getName() {
        return name;
    }

    /**
     * Set the name of the customer.
     *
     * @param name the name of the customer to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Return the age of the customer.
     *
     * @return the age of the customer
     */
    public int getAge() {
        return age;
    }

    /**
     * Set the age of the customer.
     *
     * @param age the age of the customer to set
     */
    public void setAge(int age) {
        this.age = age;
    }
}