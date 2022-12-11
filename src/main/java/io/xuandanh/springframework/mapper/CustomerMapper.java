package io.xuandanh.springframework.mapper;

import com.google.common.collect.Lists;
import com.querydsl.core.util.ArrayUtils;
import io.xuandanh.springframework.dto.CustomerDTO;
import io.xuandanh.springframework.model.Customer;
import io.xuandanh.springframework.repository.CustomerRepository;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.Date;

@Mapper(
        componentModel = "spring",
        uses = {},
        imports = {ArrayUtils.class, Lists.class, StringUtils.class,
                CollectionUtils.class, Date.class}
)
public abstract class CustomerMapper {
    @Autowired
    @Lazy
    CustomerRepository customerRepository;

    @Mapping(target = "customerId", source = "id")
    @Mapping(target = "userName", source = "userName")
    @Mapping(target = "firstName", source = "firstName")
    @Mapping(target = "lastName", source = "lastName")
    public abstract CustomerDTO customerDTOFromCustomer(Customer customer);
}
