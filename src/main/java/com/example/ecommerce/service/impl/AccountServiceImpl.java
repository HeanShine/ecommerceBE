package com.example.ecommerce.service.impl;

import com.example.ecommerce.model.Account;
import com.example.ecommerce.model.Role;
import com.example.ecommerce.repository.IAccountRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Service
public class AccountServiceImpl implements UserDetailsService {

    @Autowired
    IAccountRepo iAccountRepo;

    @Autowired
    RoleServiceImpl roleServiceImpl;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Account findByUsername(String username) {
        Account account = iAccountRepo.findByUsername(username);
        return account;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account = findByUsername(username);
        if (account != null) {
            return new User(username, account.getPassword(), account.getRoles());
        }
        throw new UsernameNotFoundException("User '" + username + "' not found");
    }

    public void register(Account account) {
        iAccountRepo.save(account);
        Account principal = iAccountRepo.findByUsername(account.getUsername());
        List<Role> roles = new ArrayList<>();
        roles.add(roleServiceImpl.findByName("ROLE_USER"));
        principal.setRoles(roles);
        principal.setPassword(passwordEncoder.encode(account.getPassword()));
        iAccountRepo.save(principal);
    }

    public Iterable<Account> findAll() {
        return iAccountRepo.findAll();
    }
}
