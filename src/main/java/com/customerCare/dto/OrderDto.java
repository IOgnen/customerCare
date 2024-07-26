package com.customerCare.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderDto {

    private long id;
    private LocalDate dateOfOrder;
    private LocalDate dateOfLastChange;
    private int totalPrice;
    private List<ProductDto> products;
    private long customerId;

    public OrderDto(long id, LocalDate dateOfOrder, LocalDate dateOfLastChange, int totalPrice) {
        this.id = id;
        this.dateOfOrder = dateOfOrder;
        this.dateOfLastChange = dateOfLastChange;
        this.totalPrice = totalPrice;
    }

}
