package com.example.ecommerce.repository;

import com.example.ecommerce.model.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IRoleRepo extends CrudRepository<Role, Integer> {
    Role findByName(String roleName);
}
