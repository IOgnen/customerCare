package com.customerCare.controller;
import com.customerCare.dto.OrderDto;
import com.customerCare.model.Order;
import com.customerCare.model.Product;
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
    public Order addOrder(@RequestBody List<Product> products, @RequestParam("id") long customerId) {
        try {
            return orderService.addOrder(products, customerId);
        } catch (Exception e) {
            return new Order();
        }
    }

}
