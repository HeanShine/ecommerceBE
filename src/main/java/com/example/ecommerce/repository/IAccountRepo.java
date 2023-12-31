package com.example.ecommerce.repository;

import com.example.ecommerce.model.Account;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IAccountRepo extends CrudRepository<Account, Integer> {
    Account findByUsername(String username);
}
