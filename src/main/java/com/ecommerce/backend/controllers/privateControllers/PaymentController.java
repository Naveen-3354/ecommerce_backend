package com.ecommerce.backend.controllers.privateControllers;

import com.ecommerce.backend.models.entity.Payment;
import com.ecommerce.backend.services.privateServices.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/payment")
@RequiredArgsConstructor
public class PaymentController {
    
    private final PaymentService service;

    @GetMapping("/all")
    public List<Payment> getAllPayments(){
        return service.selectAll();
    }

    @GetMapping("/{id}")
    public Optional<Payment> getById(@PathVariable long id){
        return service.selectById(id);
    }

    @PostMapping("/bulk")
    public String createManyPayments(@RequestBody List<Payment> payments){
        return service.insertManyPayments(payments);
    }

    @PostMapping
    public String createPayment(@RequestBody Payment payment){
        return service.insertPayment(payment);
    }

    @PutMapping("/{id}")
    public String updatePayment(@PathVariable long id, @RequestBody Payment payment){
        return service.updatePayment(id, payment);
    }

    @DeleteMapping("/{id}")
    public String deletePayment(@PathVariable long id){
        return service.deletePayment(id);
    }
}
