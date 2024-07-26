package com.customerCare.controller;

import com.customerCare.dto.OrderDto;
import com.customerCare.dto.PaymentDto;
import com.customerCare.dto.ProductDto;
import com.customerCare.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/payment")
@CrossOrigin(origins = "*")
public class PaymentController {

    @Autowired
    PaymentService paymentService;

    @PostMapping("/add")
    public PaymentDto addPayment(@RequestBody PaymentDto paymentDto, @RequestParam("id") long customerId) {
        try {
            return paymentService.addPayment(paymentDto, customerId);
        } catch (Exception exception) {
            exception.printStackTrace();
            return new PaymentDto();
        }
    }

}
