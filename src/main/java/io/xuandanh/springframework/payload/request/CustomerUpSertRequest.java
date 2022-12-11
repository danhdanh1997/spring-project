package io.xuandanh.springframework.payload.request;

import lombok.Data;

import javax.validation.constraints.NotNull;
@Data
public class CustomerUpSertRequest {
    @NotNull
    private String firstName;
    @NotNull
    private String lastName;
    @NotNull
    private String userName;
    @NotNull
    private Integer status;
}
