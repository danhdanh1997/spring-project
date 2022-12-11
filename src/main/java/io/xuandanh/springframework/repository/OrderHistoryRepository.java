package io.xuandanh.springframework.repository;

import io.xuandanh.springframework.model.OrderHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderHistoryRepository extends JpaRepository<OrderHistory, Long> {
}
