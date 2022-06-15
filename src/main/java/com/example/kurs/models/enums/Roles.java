package com.example.kurs.models.enums;

import org.springframework.security.core.GrantedAuthority;

public enum Roles implements GrantedAuthority {
    USER,
    ADMIN,
    SOMEROLE;

    @Override
    public String getAuthority() {
        return name();
    }
}
