package com.ecommerce.backend.services.publicServices;

import com.ecommerce.backend.services.securityServices.JwtService;
import com.ecommerce.backend.models.entity.User;
import com.ecommerce.backend.models.request.Login;
import com.ecommerce.backend.models.request.Register;
import com.ecommerce.backend.services.privateServices.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtUtils;
    private final UserService userService;

    public String register(Register register) {
        var user = User.builder()
                .name(register.getName())
                .email(register.getEmail())
                .password(passwordEncoder.encode(register.getPassword()))
                .number(register.getNumber());
        userService.insertUser(user.build());
        return "User Added Successfully";
    }

    public ResponseEntity<? > authenticate(Login login) {
        Authentication authentication = null;
        try {
            authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(login.getEmail(), login.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
        }
        String token = jwtUtils.generateToken();
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.SET_COOKIE, createSetCookieHeader("token", token, "/", 60*24, true));
        return new ResponseEntity<>(authentication, headers,HttpStatus.OK);
    }

    private String createSetCookieHeader(String name, String value, String path, int maxAge, boolean httpOnly) {
        StringBuilder cookie = new StringBuilder();
        cookie.append(name).append("=").append(value).append(";");
        cookie.append("Path=").append(path).append(";");
        cookie.append("Max-Age=").append(maxAge).append(";");
        if (httpOnly) {
            cookie.append("HttpOnly;");
        }
        return cookie.toString();
    }
}
