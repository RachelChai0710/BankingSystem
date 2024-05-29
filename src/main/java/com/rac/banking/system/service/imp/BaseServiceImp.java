package com.rac.banking.system.service.imp;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rac.banking.system.service.BaseService;

@Service
@Transactional
public abstract class BaseServiceImp<T> implements BaseService<T>{
	
	/**
	 * Get JPA Repository
	 * @return The specific JPA Repository
	 */
	public abstract JpaRepository<T, Long> getRepo();
	
	@Override
	public T add(T objAdd) {
		return getRepo().save(objAdd);
	}

	@Override
	public T update(T objUpd) {
		return getRepo().save(objUpd);
	}

	@Override
	public Optional<T> findById(Long id) {
		return getRepo().findById(id);
	}

	@Override
	public List<T> findAll() {
		return getRepo().findAll();
	}
}