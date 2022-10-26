package com.earts.earts.security.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

import com.earts.earts.security.provider.JwtProvider;

public class JwtManager implements AuthenticationManager{

    private final JwtProvider provider;

    @Autowired
    public JwtManager(JwtProvider provider){
        this.provider = provider;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        return this.provider.authenticate(authentication);
    }
    
}
