package io.xuandanh.springframework.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(of = {"customerId", "userName", "firstName", "lastName"})
public class CustomerDTO {
    private Long customerId;
    private String userName;
    private String firstName;
    private String lastName;
    private String email;
}
