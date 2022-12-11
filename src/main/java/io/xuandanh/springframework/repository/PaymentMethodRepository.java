package io.xuandanh.springframework.repository;

import io.xuandanh.springframework.model.PaymentMethod;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentMethodRepository extends JpaRepository<PaymentMethod, Long> {
}
