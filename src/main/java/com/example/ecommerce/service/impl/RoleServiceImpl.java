package com.example.ecommerce.service.impl;

import com.example.ecommerce.model.Role;
import com.example.ecommerce.repository.IRoleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl {
    @Autowired
    IRoleRepo roleRepo;

    public Role findByName(String name) {
        return roleRepo.findByName(name);
    }
}
