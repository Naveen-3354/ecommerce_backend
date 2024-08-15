package com.ecommerce.backend.services.privateServices;

import com.ecommerce.backend.models.entity.Cart;
import com.ecommerce.backend.repositories.CartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CartService {

    public final CartRepository repository;

    public String insertCart(Cart cart) {
        cart.setCreatedOn(LocalDate.now());
        repository.save(cart);
        return "Cart added.";
    }

    public String insertManyCarts(List<Cart> cartItems) {
        repository.saveAll(cartItems);
        return "Carts added.";
    }

    public List<Cart> selectAll() {
        return repository.findAll();
    }

    public Optional<Cart> selectById(long id) {
        return repository.findById(id);
    }

    public String updateCart(long id, Cart cartItem) {
        Optional<Cart> cartItemExist = repository.findById(id);
        if (cartItemExist.isPresent()) {
            Cart existingCart = cartItemExist.get();
            repository.save(existingCart);
            return "Cart Updated.";
        }
        return "cartItem does not exist.";
    }

    public String deleteCart(long id) {
        repository.deleteById(id);
        return "Cart deleted.";
    }
}
