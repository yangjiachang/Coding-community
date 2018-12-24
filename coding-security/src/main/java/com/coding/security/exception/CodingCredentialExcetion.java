package com.coding.security.exception;

import org.springframework.security.core.AuthenticationException;

public class CodingCredentialExcetion extends AuthenticationException {

    private static final long serialVersionUID = -920087729589688230L;

    public CodingCredentialExcetion(String message) {
        super(message);
    }
}
