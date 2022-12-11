package io.xuandanh.springframework.repository;

import io.xuandanh.springframework.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
}
