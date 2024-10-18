package com.lambferret.project_a.security.core;

import org.springframework.security.core.GrantedAuthority;

import java.util.List;

public class CustomAuthority implements GrantedAuthority {

    public enum Role {
        ADMIN, USER
    }

    private final String role;

    public CustomAuthority(String role) {
        this.role = role.startsWith("ROLE_") ? role : "ROLE_" + role;
    }

    @Override
    public String getAuthority() {
        return role;
    }
}
