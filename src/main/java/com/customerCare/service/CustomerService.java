package com.customerCare.service;

import com.customerCare.model.Customer;
import com.customerCare.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    public Customer addCustomer(Customer customer) {
        customer.setDateAdded(LocalDate.now());
        customerRepository.save(customer);
        return customer;
    }

    public Customer getCustomerById(long id) {
        Optional<Customer> customer = customerRepository.findById(id);
        return customer.orElse(null);
    }

    public List<Customer>  findByFullName(String firstName, String lastName) {
        return customerRepository.findByFirstNameAndLastName(firstName, lastName);
    }

    public Customer editCustomer(Customer newCustomer, Long id) {

        Customer existingCustomer = getCustomerById(id);

        existingCustomer.setFirstName(newCustomer.getFirstName());
        existingCustomer.setLastName(newCustomer.getLastName());
        existingCustomer.setPhoneNumber(newCustomer.getPhoneNumber());
        existingCustomer.setDescription(newCustomer.getDescription());

        customerRepository.save(existingCustomer);

        return existingCustomer;
    }

}
