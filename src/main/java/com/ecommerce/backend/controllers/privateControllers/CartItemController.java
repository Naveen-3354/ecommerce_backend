package com.ecommerce.backend.controllers.privateControllers;

import com.ecommerce.backend.models.entity.CartItem;
import com.ecommerce.backend.services.privateServices.CartItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cartItem")
@RequiredArgsConstructor
public class CartItemController {

    public final CartItemService service;

    @GetMapping("/all")
    public List<CartItem> getAllCartItems(){
        return service.selectAll();
    }

    @GetMapping("/{id}")
    public Optional<CartItem> getById(@PathVariable long id){
        return service.selectById(id);
    }

    @PostMapping("/bulk")
    public String createManyCartItems(@RequestBody List<CartItem> cartItems){
        return service.insertManyCartItems(cartItems);
    }

    @PostMapping
    public String createCartItem(@RequestBody CartItem cartItem){
        return service.insertCartItem(cartItem);
    }

    @PutMapping("/{id}")
    public String updateCartItem(@PathVariable long id, @RequestBody CartItem cartItem){
        return service.updateCartItem(id, cartItem);
    }

    @DeleteMapping("/{id}")
    public String deleteCartItem(@PathVariable long id){
        return service.deleteCartItem(id);
    }

}
