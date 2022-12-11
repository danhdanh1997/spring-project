package io.xuandanh.springframework.converter;

import io.xuandanh.springframework.dto.CustomerDTO;
import io.xuandanh.springframework.model.Customer;
import io.xuandanh.springframework.payload.request.CustomerUpSertRequest;
import io.xuandanh.springframework.payload.response.CustomerUpSertResponse;
import io.xuandanh.springframework.service.CustomerService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
@Component
public class CustomerConverter {
    private final CustomerService customerService;

    public CustomerConverter(CustomerService customerService) {
        this.customerService = customerService;
    }

    public CustomerUpSertResponse asResponse (CustomerUpSertRequest request) {
        Customer customer = customerService.createCustomer(request);
        return new CustomerUpSertResponse()
                .setCustomerId(customer.getId())
                .setFirstName(customer.getFirstName())
                .setLastName(customer.getLastName())
                .setUserName(customer.getUserName())
                .setStatus(customer.getStatus());
    }

    public Page<CustomerDTO> asResponses(String search, Pageable pageable) {
        return customerService.getAllCustomer(search, pageable);
    }
}
