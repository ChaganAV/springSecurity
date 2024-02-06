package com.example.springSecurity.model;

import lombok.Data;

import javax.persistence.Entity;


public class JwtProperties {
    static final long EXPIRATION_TIME = 5000;
    static final String SECRET = "secret";
    static final String TOKEN_PREFIX = "Bearer";
    static final String HEADER_STRING = "Authorization";

}
