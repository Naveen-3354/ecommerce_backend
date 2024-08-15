package com.ecommerce.backend.services.privateServices;

import com.ecommerce.backend.models.entity.CartItem;
import com.ecommerce.backend.repositories.CartItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CartItemService {

    public final CartItemRepository repository;

    public String insertCartItem(CartItem cartItem) {
        cartItem.setCreatedOn(LocalDate.now());
        repository.save(cartItem);
        return "CartItem added.";
    }

    public String insertManyCartItems(List<CartItem> cartItems) {
        repository.saveAll(cartItems);
        return "CartItems added.";
    }

    public List<CartItem> selectAll() {
        return repository.findAll();
    }

    public Optional<CartItem> selectById(long id) {
        return repository.findById(id);
    }

    public String updateCartItem(long id, CartItem cartItem) {
        Optional<CartItem> cartItemExist = repository.findById(id);
        if (cartItemExist.isPresent()) {
            CartItem existingCartItem = cartItemExist.get();
            repository.save(existingCartItem);
            return "CartItem Updated.";
        }
        return "cartItem does not exist.";
    }

    public String deleteCartItem(long id) {
        repository.deleteById(id);
        return "CartItem deleted.";
    }
}
