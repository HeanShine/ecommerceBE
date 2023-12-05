package com.example.ecommerce.model.dto;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;


@Data
@AllArgsConstructor
public class AccountToken {
    private int id;
    private String username;
    private String token;
    private Collection<? extends GrantedAuthority> roles;
}
