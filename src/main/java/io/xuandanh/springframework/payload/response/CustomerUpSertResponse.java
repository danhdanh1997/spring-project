package io.xuandanh.springframework.payload.response;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class CustomerUpSertResponse {
    private Long customerId;
    private String firstName;
    private String lastName;
    private String userName;
    private Integer status;
}
