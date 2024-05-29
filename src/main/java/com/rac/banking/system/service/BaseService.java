package com.rac.banking.system.service;

import java.util.List;
import java.util.Optional;

public interface BaseService<T> {
	
	/**
	 * Add object to database
	 * @param objAdd The object to be added
	 * @return The object that was added
	 */
	public T add(T objAdd);
	
	/**
	 * Updates the specified object in the database.
	 * @param objUpd The object to be updated
	 * @return The object that was updated
	 */
	public T update(T objUpd);
	
	/**
	 * Find entity by id
	 * @param id object id
	 * @return the entity with the specified id, or null if no entity is found
	 */
	public Optional<T> findById(Long id);
	
	/**
	 * Find all entities
	 * @return all entities
	 */
	public List<T> findAll();
}