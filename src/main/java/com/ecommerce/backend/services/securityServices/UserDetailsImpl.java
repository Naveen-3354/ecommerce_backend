package com.ecommerce.backend.services.securityServices;

import com.ecommerce.backend.models.entity.User;
import com.ecommerce.backend.models.securityModel.AuthUser;
import com.ecommerce.backend.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserDetailsImpl implements UserDetailsService {

    private final UserRepository repository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = repository.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException("user not found"));

        return AuthUser.builder()
                .email(user.getEmail())
                .password(user.getPassword())
                .role(user.getRoles().toString())
                .build();
    }
}
