package com.customerCare.controller;
import com.customerCare.dto.OrderDto;
import com.customerCare.dto.ProductDto;
import com.customerCare.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
@CrossOrigin(origins = "*")
public class OrderController {

    @Autowired
    OrderService orderService;

    @PostMapping("/add")
    public OrderDto addOrder(@RequestBody List<ProductDto> productsDto, @RequestParam("id") long customerId) {
        try {
            return orderService.addOrder(productsDto, customerId);
        } catch (Exception exception) {
            exception.printStackTrace();
            return new OrderDto();
        }
    }

}
