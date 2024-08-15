package com.ecommerce.backend.controllers.privateControllers;

import com.ecommerce.backend.models.entity.Orders;
import com.ecommerce.backend.services.privateServices.OrdersService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrdersController {

    public final OrdersService service;

    @GetMapping("/all")
    public List<Orders> getAllOrderss(){
        return service.selectAll();
    }

    @GetMapping("/{id}")
    public Optional<Orders> getById(@PathVariable long id){
        return service.selectById(id);
    }

    @PostMapping("/bulk")
    public String createManyOrderss(@RequestBody List<Orders> orderss){
        return service.insertManyOrderss(orderss);
    }

    @PostMapping
    public String createOrders(@RequestBody Orders orders){
        return service.insertOrders(orders);
    }

    @PutMapping("/{id}")
    public String updateOrders(@PathVariable long id, @RequestBody Orders orders){
        return service.updateOrders(id, orders);
    }

    @DeleteMapping("/{id}")
    public String deleteOrders(@PathVariable long id){
        return service.deleteOrders(id);
    }

}
