package com.customerCare.controller;

import com.customerCare.model.Customer;
import com.customerCare.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/customer")
@CrossOrigin(origins = "*")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @PostMapping("/add")
    public Customer addCustomer(@RequestBody Customer customer) {
        try {
            return customerService.addCustomer(customer);
        } catch (Exception e) {
            return new Customer();
        }
    }

    @GetMapping("/get")
    public Customer getCustomer(@RequestParam int customerId) {
        try {
            return customerService.getCustomerById(customerId);
        } catch (Exception e) {
            return null;
        }
    }

    @GetMapping("/find")
    public List<Customer> findByFirstNameAndLastName(@RequestParam String firstName, @RequestParam String lastName) {
        try {
            List<Customer> customers = customerService.findByFullName(firstName, lastName);
            if (customerService.findByFullName(firstName, lastName).isEmpty()) {
                return Collections.emptyList();
            } else {
                return customers;
            }
        } catch (Exception e) {
            return Collections.emptyList();
        }
    }

    @GetMapping("/edit")
    public Customer editCustomer(@RequestBody Customer newCustomer, @RequestParam Long id) {
        try {
            return customerService.editCustomer(newCustomer, id);
        } catch (Exception e) {
            return new Customer();
        }
    }

}
