package com.customerCare.dto;
import com.customerCare.model.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto {

    private long id;
    private List<Product> products;
    private int totalPrice;
    private String customerFirstName;
    private String customerLastName;

}
