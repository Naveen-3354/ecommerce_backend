package com.ecommerce.backend.controllers.publicControlers;

import com.ecommerce.backend.models.request.Login;
import com.ecommerce.backend.models.request.Register;
import com.ecommerce.backend.services.publicServices.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthService service;

    @GetMapping("/test")
    public String testApi(){
        return "TestApi works.";
    }

    @PostMapping("/register")
    public ResponseEntity<String> register( @Valid @RequestBody Register register) {
        return ResponseEntity.ok().body(service.register(register));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login( @Valid @RequestBody Login login) {
        return service.authenticate(login);
    }
}
