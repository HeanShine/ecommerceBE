package com.example.ecommerce.repository;

import com.example.ecommerce.model.Category;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICategoryRepo extends CrudRepository<Category,Integer> {
}
