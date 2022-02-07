package com.accubits.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.accubits.model.Category;

@Transactional
@Repository
public interface CategoryDao extends CrudRepository<Category, Long> {

}
