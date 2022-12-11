package io.xuandanh.springframework.service.impl;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import io.xuandanh.springframework.dto.CustomerDTO;
import io.xuandanh.springframework.mapper.CustomerMapper;
import io.xuandanh.springframework.model.Customer;
import io.xuandanh.springframework.model.QCustomer;
import io.xuandanh.springframework.payload.request.CustomerUpSertRequest;
import io.xuandanh.springframework.repository.CustomerRepository;
import io.xuandanh.springframework.service.CustomerService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaContext;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import java.util.Collections;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService {
    private final CustomerMapper customerMapper;

    private final CustomerRepository customerRepository;

    private final EntityManager em;

    public CustomerServiceImpl (JpaContext jpaContext, CustomerMapper customerMapper, CustomerRepository customerRepository) {
        this.em = jpaContext.getEntityManagerByManagedType(Customer.class);
        this.customerMapper = customerMapper;
        this.customerRepository = customerRepository;
    }

    @Override
    public Page<CustomerDTO> getAllCustomer(String search, Pageable pageable) {
        QCustomer qCustomer = QCustomer.customer;
        BooleanExpression expression = qCustomer.status.eq(Customer.ACTIVE);
        if (StringUtils.hasLength(search)){
            expression = expression.and(qCustomer.userName.likeIgnoreCase("%" + search + "%"));
        }
        var customerList = new JPAQuery<Customer>(em)
                .select(qCustomer)
                .from(qCustomer)
                .where(expression)
                .limit(pageable.getPageSize())
                .offset(pageable.getOffset())
                .fetch();
        var customers = customerList.stream().map(customer -> {
            return customerMapper.customerDTOFromCustomer(customer);
        }).collect(Collectors.toList());
        return new PageImpl<>(customers, pageable, customers.size());
    }

    @Override
    public Customer createCustomer(CustomerUpSertRequest request) {
        Customer customer = new Customer()
                .setFirstName(request.getFirstName())
                .setLastName(request.getLastName())
                .setUserName(request.getUserName())
                .setStatus(request.getStatus());
        return customerRepository.save(customer);
    }
}
