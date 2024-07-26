package com.customerCare.dto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDto {

    private long id;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String description;
    private LocalDate dateAdded;
    private Integer debt;
    private List<OrderDto> orders;
    private List<PaymentDto> payments;

    public CustomerDto(long id, String firstName, String lastName, String phoneNumber, String description,
                       LocalDate dateAdded, Integer debt) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.description = description;
        this.dateAdded = dateAdded;
        this.debt = debt;
    }

    public CustomerDto(long id, String firstName, String lastName, String phoneNumber, String description, LocalDate dateAdded) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.description = description;
        this.dateAdded = dateAdded;
    }

}
