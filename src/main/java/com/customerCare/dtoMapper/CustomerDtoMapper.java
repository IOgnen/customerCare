package com.customerCare.dtoMapper;
import com.customerCare.dto.CustomerDto;;
import com.customerCare.model.Customer;

import java.time.LocalDate;

public class CustomerDtoMapper {

    public static Customer customerDtoToCustomer(CustomerDto customerDto) {

        Customer customer = new Customer();
        customer.setFirstName(customerDto.getFirstName());
        customer.setLastName(customerDto.getLastName());
        customer.setDescription(customerDto.getDescription());
        customer.setPhoneNumber(customerDto.getPhoneNumber());
        customer.setDateAdded(LocalDate.now());

        return customer;
    }

    public static CustomerDto customerToCustomerDto(Customer customer) {

        CustomerDto customerDto = new CustomerDto();
        customerDto.setId(customer.getId());
        customerDto.setFirstName(customer.getFirstName());
        customerDto.setLastName(customer.getLastName());
        customerDto.setPhoneNumber(customer.getPhoneNumber());
        customerDto.setDescription(customer.getDescription());

        return customerDto;
    }

}
