package com.customerCare.controller;

import com.customerCare.dto.CustomerDto;
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
    public CustomerDto addCustomer(@RequestBody CustomerDto customer) {
        try {
            return customerService.addCustomer(customer);
        } catch (Exception exception) {
            exception.printStackTrace();
            return new CustomerDto();
        }
    }

    @GetMapping("/get")
    public CustomerDto getCustomer(@RequestParam int customerId) {
        try {
            return customerService.getCustomerDtoById(customerId);
        } catch (Exception exception) {
            exception.printStackTrace();
            return new CustomerDto();
        }
    }

    @GetMapping("/find")
    public List<CustomerDto> findCustomersByFirstNameAndLastName(@RequestParam String firstName, @RequestParam String lastName) {
        try {
            return customerService.findCustomerByFirstNameAndLastName(firstName, lastName);
        } catch (Exception exception) {
            exception.printStackTrace();
            return Collections.emptyList();
        }
    }

    @GetMapping("/edit")
    public CustomerDto editCustomer(@RequestBody CustomerDto newCustomer, @RequestParam Long id) {
        try {
            return customerService.editCustomer(newCustomer, id);
        } catch (Exception e) {
            return new CustomerDto();
        }
    }

}
