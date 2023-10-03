package com.enigma.tokonyadia.entity.role;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public enum ERole {
    ROLE_CUSTOMER,
    ROLE_ADMIN,
    ROLE_SELLER;


    public static ERole get(String value) {
        for (ERole erole : ERole.values()) {
            if (erole.name().equalsIgnoreCase(value)) return erole;
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "role not found");
    }
}
