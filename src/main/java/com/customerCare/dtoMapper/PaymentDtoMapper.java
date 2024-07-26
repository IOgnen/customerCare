package com.customerCare.dtoMapper;
import com.customerCare.dto.CustomerDto;
import com.customerCare.dto.PaymentDto;
import com.customerCare.model.Customer;
import com.customerCare.model.Payment;

public class PaymentDtoMapper {

    public static Payment PaymentDtoToPayment(PaymentDto paymentDto) {

        Payment payment = new Payment();
        payment.setAmount(paymentDto.getAmount());

        return payment;
    }

    public static PaymentDto paymentToPaymentDto(Payment payment) {

        PaymentDto paymentDto = new PaymentDto();
        paymentDto.setId(payment.getId());
        paymentDto.setAmount(payment.getAmount());
        paymentDto.setCustomerId(payment.getCustomer().getId());

        return paymentDto;
    }

}
