package com.customerCare.service;
import com.customerCare.dto.PaymentDto;
import com.customerCare.dtoMapper.PaymentDtoMapper;
import com.customerCare.model.Customer;
import com.customerCare.model.Payment;
import com.customerCare.repository.paymentRepository.PaymentRepository;
import com.customerCare.repository.customerRepository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;
    @Autowired
    private CustomerRepository customerRepository;

    public PaymentDto addPayment(PaymentDto paymentDto, long customerId) {

        Payment payment = PaymentDtoMapper.PaymentDtoToPayment(paymentDto);

        Customer customer = new Customer();
        Optional<Customer> optionalCustomer = customerRepository.findById(customerId);

        if (optionalCustomer.isPresent()) {
            customer = optionalCustomer.get();
        }
        payment.setCustomer(customer);

        LocalDate localDate = LocalDate.now();
        payment.setDateOfPayment(localDate);

        Payment savedPayment = paymentRepository.save(payment);

        return PaymentDtoMapper.paymentToPaymentDto(savedPayment);
    }

}
