package com.ecommerce.backend.controllers.publicControlers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v2/auth")
public class TestController {

    @GetMapping("/test")
    public String test(){
        return "test Api works..";
    }

}
