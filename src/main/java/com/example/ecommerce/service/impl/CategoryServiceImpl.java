package com.example.ecommerce.service.impl;


import com.example.ecommerce.model.Category;
import com.example.ecommerce.repository.ICategoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl {
    @Autowired
    ICategoryRepo categoryRepo;
    public List<Category> findAll() {
        return (List<Category>) categoryRepo.findAll();
    }
}
