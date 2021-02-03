package org.ftn.upp.lass.security;

import lombok.Data;

import java.io.Serializable;

@Data
public class JwtResponse implements Serializable {

    private static String message = "Authentication successful.";
    private final String token;
}