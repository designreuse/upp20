package org.ftn.upp.lass.security;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.ArrayList;

public class JwtTokenDetailsUtil {

    private JwtTokenDetailsUtil() { }

    public static boolean isAnonymousUserAuthenticationToken() {
        return SecurityContextHolder.getContext().getAuthentication() instanceof AnonymousAuthenticationToken;
    }

    public static Long getCurrentUserId() {
        if (isAnonymousUserAuthenticationToken()) {
            throw new IllegalArgumentException("Anonymous user does not have an ID.");
        }

        return ((JwtUser) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal())
                .getId();
    }

    public static String getCurrentUserUsername() {
        if (isAnonymousUserAuthenticationToken()) {
            throw new IllegalArgumentException("Anonymous user does not have an username.");
        }

        return ((JwtUser) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal())
                .getUsername();
    }

    public static String getCurrentUserRole() {
        if (isAnonymousUserAuthenticationToken()) {
            throw new IllegalArgumentException("Anonymous user does not have a role.");
        }

        return new ArrayList<>(((JwtUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal())
                .getAuthorities())
                .get(0)
                .getAuthority();
    }
}