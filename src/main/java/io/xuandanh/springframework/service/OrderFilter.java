package io.xuandanh.springframework.service;

import lombok.Getter;
import lombok.Setter;

import java.time.ZonedDateTime;
@Getter
@Setter
public class OrderFilter {
    Long orderId;
    String firstName;
    String lastName;
    String paymentMethod;
    String orderStatus;
    ZonedDateTime fromDate; // "2011-12-03T10:15:30+03:00"
    ZonedDateTime toDate;
}


