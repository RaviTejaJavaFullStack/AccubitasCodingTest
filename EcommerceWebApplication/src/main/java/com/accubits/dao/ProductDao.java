package com.accubits.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.accubits.model.Product;

@Transactional
@Repository
public interface ProductDao extends CrudRepository<Product, Long> {

}
