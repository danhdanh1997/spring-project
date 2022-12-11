package io.xuandanh.springframework.service.impl;

import io.xuandanh.springframework.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
@Slf4j
public class OrderServiceImpl implements OrderService {
   // private final OrderRepository orderRepository;

//    @Override
//    public List<OrderDTO> fetchAll(OrderFilter filterParam) {
//      return orderRepository.findAll(filterParam);
//    }

//    private OrderDTO createDTO(Order order) {
//        return OrderDTO.builder()
//                .orderId(order.getId())
//                .createdAt(order.getCreatedAt())
//                .customerNames(order.getCustomer().getCustomerNames())
//                .amount(order.getPayment().getAmount())
//                .paymentMethod(order.getPayment().getId() + "")
//                .build();
//    }
}
