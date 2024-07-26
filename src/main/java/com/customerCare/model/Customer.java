package com.customerCare.model;

import com.customerCare.dto.PaymentDto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "customer")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String description;
    private LocalDate dateAdded;
    private Integer debt;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Order> orders;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Payment> payments;

    public void setDebt() {

        int totalDebt = 0;
        int totalPayed = 0;

        if (orders != null) {
            for (Order order : orders) {
                totalDebt += order.getTotalPrice();
            }
        }

        if (payments != null) {
            for (Payment payment : payments) {
                totalPayed += payment.getAmount();
            }
        }

        this.debt = totalDebt -  totalPayed;
    }

}
