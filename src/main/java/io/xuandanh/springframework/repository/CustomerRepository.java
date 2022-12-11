package io.xuandanh.springframework.repository;

import io.xuandanh.springframework.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface CustomerRepository extends JpaRepository<Customer, Long>, QuerydslPredicateExecutor<Customer> {
}
