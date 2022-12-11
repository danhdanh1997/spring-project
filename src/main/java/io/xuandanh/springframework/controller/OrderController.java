package io.xuandanh.springframework.controller;

import io.xuandanh.springframework.service.OrderService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
    private final OrderService orderService;

    public OrderController (OrderService orderService) {
        this.orderService = orderService;
    }

//    @GetMapping("/search")
//    public List<OrderDTO> search(OrderFilter orderFilter) {
//        return orderService.fetchAll(orderFilter);
//    }

}
