package io.xuandanh.springframework.repository;

import io.xuandanh.springframework.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;


public interface OrderRepository extends JpaRepository<Order, Long>,
        QuerydslPredicateExecutor<Order>, CustomOrderRepository {

}
