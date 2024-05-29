package com.rac.banking.system.data;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Customer Request Details.
 */
public class CustomerReq extends BaseReq{

	/** Customer's Name. */
	@JsonProperty("name")
	private String name;
	
	/** Customer's Age. */
	@JsonProperty("age")
	private int age;
	
	/**
     * Returns the name of the customer.
     *
     * @return the name of the customer
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the customer.
     *
     * @param name the name of the customer to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the age of the customer.
     *
     * @return the age of the customer
     */
    public int getAge() {
        return age;
    }

    /**
     * Sets the age of the customer.
     *
     * @param age the age of the customer to set
     */
    public void setAge(int age) {
        this.age = age;
    }
}