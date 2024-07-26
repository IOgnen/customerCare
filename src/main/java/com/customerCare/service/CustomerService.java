package com.customerCare.service;

import com.customerCare.dto.CustomerDto;
import com.customerCare.dto.OrderDto;
import com.customerCare.dto.PaymentDto;
import com.customerCare.dtoMapper.CustomerDtoMapper;
import com.customerCare.model.Customer;
import com.customerCare.repository.customerRepository.CustomerRepository;
import com.customerCare.repository.customerRepository.CustomerRepositoryCustom;
import com.customerCare.repository.orderRepository.OrderRepositoryCustom;
import com.customerCare.repository.paymentRepository.PaymentRepositoryCustom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private CustomerRepositoryCustom customerRepositoryCustom;
    @Autowired
    private OrderRepositoryCustom orderRepositoryCustom;
    @Autowired
    private PaymentRepositoryCustom paymentRepositoryCustom;

    public CustomerDto addCustomer(CustomerDto customerDto) {

        Customer customer = CustomerDtoMapper.customerDtoToCustomer(customerDto);
        customerRepository.save(customer);
        return CustomerDtoMapper.customerToCustomerDto(customer);

    }

    public CustomerDto getCustomerDtoById(long customerId) {

        CustomerDto customerDto = customerRepositoryCustom.getCustomerById(customerId);
        List<OrderDto> ordersDto = orderRepositoryCustom.getOrdersByCustomerId(customerId);
        customerDto.setOrders(ordersDto);
        List<PaymentDto> paymentsDto = paymentRepositoryCustom.getPaymentsByCustomerId(customerId);
        customerDto.setPayments(paymentsDto);

        return customerDto;
    }

    public List<CustomerDto> findCustomerByFirstNameAndLastName(String firstName, String lastName) {

        return customerRepositoryCustom.findCustomersByFirstNameAndLastName(firstName, lastName);
    }

    public CustomerDto editCustomer(CustomerDto customerDto, Long customerId) {

        Customer existingCustomer = new Customer();
        Optional<Customer> optionalCustomer = customerRepository.findById(customerId);

        if (optionalCustomer.isPresent()) {
            existingCustomer = optionalCustomer.get();
        }

        existingCustomer.setFirstName(customerDto.getFirstName());
        existingCustomer.setLastName(customerDto.getLastName());
        existingCustomer.setPhoneNumber(customerDto.getPhoneNumber());
        existingCustomer.setDescription(customerDto.getDescription());

        existingCustomer = customerRepository.save(existingCustomer);
        return CustomerDtoMapper.customerToCustomerDto(existingCustomer);

    }

}
