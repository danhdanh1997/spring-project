package io.xuandanh.springframework.repository;

import io.xuandanh.springframework.model.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
}
