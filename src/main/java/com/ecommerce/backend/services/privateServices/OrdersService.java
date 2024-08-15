package com.ecommerce.backend.services.privateServices;


import com.ecommerce.backend.models.entity.Orders;
import com.ecommerce.backend.repositories.OrdersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrdersService {

    public final OrdersRepository repository;
    
    public String insertOrders(Orders orders) {
        orders.setCreatedOn(LocalDate.now());
        repository.save(orders);
        return "Orders added.";
    }

    public String insertManyOrderss(List<Orders> orderss) {
        repository.saveAll(orderss);
        return "Orderss added.";
    }

    public List<Orders> selectAll() {
        return repository.findAll();
    }

    public Optional<Orders> selectById(long id) {
        return repository.findById(id);
    }

    public String updateOrders(long id, Orders orders) {
        Optional<Orders> ordersExist = repository.findById(id);
        if (ordersExist.isPresent()) {
            Orders existingOrders = ordersExist.get();
            repository.save(existingOrders);
            return "Orders Updated.";
        }
        return "orders does not exist.";
    }

    public String deleteOrders(long id) {
        repository.deleteById(id);
        return "Orders deleted.";
    }
}
