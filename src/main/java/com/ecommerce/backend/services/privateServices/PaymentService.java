package com.ecommerce.backend.services.privateServices;

import com.ecommerce.backend.models.entity.Payment;
import com.ecommerce.backend.repositories.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PaymentService {
    
    private final PaymentRepository repository;

    public String insertPayment(Payment payment) {
        payment.setCreatedOn(LocalDate.now());
        repository.save(payment);
        return "Payment added.";
    }

    public String insertManyPayments(List<Payment> paymentItems) {
        repository.saveAll(paymentItems);
        return "Payments added.";
    }

    public List<Payment> selectAll() {
        return repository.findAll();
    }

    public Optional<Payment> selectById(long id) {
        return repository.findById(id);
    }

    public String updatePayment(long id, Payment paymentItem) {
        Optional<Payment> paymentItemExist = repository.findById(id);
        if (paymentItemExist.isPresent()) {
            Payment existingPayment = paymentItemExist.get();
            repository.save(existingPayment);
            return "Payment Updated.";
        }
        return "paymentItem does not exist.";
    }

    public String deletePayment(long id) {
        repository.deleteById(id);
        return "Payment deleted.";
    }
}
