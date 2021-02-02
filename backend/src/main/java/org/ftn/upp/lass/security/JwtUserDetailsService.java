package org.ftn.upp.lass.security;

import lombok.RequiredArgsConstructor;
import org.ftn.upp.lass.repository.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class JwtUserDetailsService implements UserDetailsService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    @Override
    public JwtUser loadUserByUsername(String username) throws UsernameNotFoundException {
        final var userOptional = this.userRepository.findUserByUsername(username);
        if (userOptional.isEmpty()) {
            throw new UsernameNotFoundException("No user found with username '" + username + "'");
        } else {
            final var user = userOptional.get();
            final List<GrantedAuthority> grantedAuthorities = Collections.singletonList(new SimpleGrantedAuthority(user.getRole().getMessage()));

            return new JwtUser(
                    user.getId(),
                    username,
                    this.passwordEncoder.encode(user.getPassword()),
                    true,
                    true,
                    true,
                    true,
                    grantedAuthorities);
        }
    }
}
