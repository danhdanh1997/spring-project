package io.xuandanh.springframework.controller;

import io.xuandanh.springframework.converter.CustomerConverter;
import io.xuandanh.springframework.dto.CustomerDTO;
import io.xuandanh.springframework.payload.request.CustomerUpSertRequest;
import io.xuandanh.springframework.payload.response.CustomerUpSertResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/customers")
public class CustomerController {
    private final CustomerConverter customerConverter;

    @GetMapping("/list")
    public Page<CustomerDTO> getAllCustomer(@RequestParam(required = false) String search, Pageable pageable) {
        return customerConverter.asResponses(search, pageable);
    }

    @PostMapping("/create")
    public ResponseEntity<CustomerUpSertResponse> createCustomer(@RequestBody CustomerUpSertRequest request) {
        CustomerUpSertResponse response = customerConverter.asResponse(request);
        return ResponseEntity.ok(response);
    }


    @GetMapping("/xuandanh")
    public String xuandanh() {
        return "xuan danh";
    }

    @GetMapping("/xuanthach")
    public String xuanthach() {
        return "xuan thach";
    }

    public void testGitStash() {
        System.out.println("test git stash");
    }
}
