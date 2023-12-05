package com.example.ecommerce.controller;

import com.example.ecommerce.model.Account;
import com.example.ecommerce.model.dto.AccountToken;
import com.example.ecommerce.service.impl.AccountServiceImpl;
import com.example.ecommerce.service.impl.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
public class AccountController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private AccountServiceImpl accountService;

    @Autowired
    private TokenService tokenService;


    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Account account) {
        UsernamePasswordAuthenticationToken user = new UsernamePasswordAuthenticationToken(account.getUsername(), account.getPassword());
        Authentication authentication = authenticationManager // ma code nay dung de xac thuc user co ton tai trong database hay khong
                .authenticate(user);
        SecurityContextHolder.getContext().setAuthentication(authentication); // ma code nay dung de set thong tin user vao security context

        String token = tokenService.createToken(account.getUsername()); // ma code nay dung de tao token
        Account currentUser = accountService.findByUsername(account.getUsername()); // ma code nay dung de lay thong tin user tu database
        return ResponseEntity.ok(new AccountToken(currentUser.getId(), currentUser.getUsername(), token, currentUser.getRoles()));
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody Account account) {
        Iterable<Account> listAccount = accountService.findAll();
        for (Account account1 : listAccount) {
            if (account1.getUsername().equals(account.getUsername())) {
                return ResponseEntity.ok("Username already exists");
            }
        }
        accountService.register(account);
        return ResponseEntity.ok(account);
    }
}
