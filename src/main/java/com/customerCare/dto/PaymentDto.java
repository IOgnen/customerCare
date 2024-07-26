package com.customerCare.dto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PaymentDto {

    private Long id;
    private int amount;
    private LocalDate dateOfPayment;
    private Long customerId;

    public PaymentDto(Long id, int amount, LocalDate dateOfPayment) {
        this.id = id;
        this.amount = amount;
        this.dateOfPayment = dateOfPayment;
    }

}
