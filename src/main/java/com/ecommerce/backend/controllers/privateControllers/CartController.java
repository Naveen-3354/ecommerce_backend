package com.ecommerce.backend.controllers.privateControllers;

import com.ecommerce.backend.models.entity.Cart;
import com.ecommerce.backend.services.privateServices.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cart")
@RequiredArgsConstructor
public class CartController {

    public final CartService service;

    @GetMapping("/all")
    public List<Cart> getAllCarts(){
        return service.selectAll();
    }

    @GetMapping("/{id}")
    public Optional<Cart> getById(@PathVariable long id){
        return service.selectById(id);
    }

    @PostMapping("/bulk")
    public String createManyCarts(@RequestBody List<Cart> carts){
        return service.insertManyCarts(carts);
    }

    @PostMapping
    public String createCart(@RequestBody Cart cart){
        return service.insertCart(cart);
    }

    @PutMapping("/{id}")
    public String updateCart(@PathVariable long id, @RequestBody Cart cart){
        return service.updateCart(id, cart);
    }

    @DeleteMapping("/{id}")
    public String deleteCart(@PathVariable long id){
        return service.deleteCart(id);
    }

}
