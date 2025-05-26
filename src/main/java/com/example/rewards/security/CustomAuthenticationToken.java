package com.example.rewards.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.io.Serializable;
import java.util.List;

@Configuration
public class CustomAuthenticationToken  extends UsernamePasswordAuthenticationToken implements Serializable {
    private static final long serialVersionUID = 1L;

    public CustomAuthenticationToken(Object principal, Object credentials) {
        super(principal, credentials);
    }

    public CustomAuthenticationToken(Object principal, Object credentials, List<GrantedAuthority> authorities) {
        super(principal, credentials, authorities);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        CustomAuthenticationToken authRequest = new CustomAuthenticationToken(username, password);
        setDetails(authRequest);

        return this.getAuthenticationManager().authenticate(authRequest);
    }





}

