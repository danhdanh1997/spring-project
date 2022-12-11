package io.xuandanh.springframework.service;

import io.xuandanh.springframework.dto.CustomerDTO;
import io.xuandanh.springframework.model.Customer;
import io.xuandanh.springframework.payload.request.CustomerUpSertRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CustomerService {
    Page<CustomerDTO> getAllCustomer(String search, Pageable pageable);

    Customer createCustomer (CustomerUpSertRequest request);
}
