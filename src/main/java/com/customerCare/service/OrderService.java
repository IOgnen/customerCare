package com.customerCare.service;

import com.customerCare.dto.OrderDto;
import com.customerCare.model.Customer;
import com.customerCare.model.Order;
import com.customerCare.model.Product;
import com.customerCare.repository.OrderRepository;
import com.customerCare.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    @Autowired
    CustomerService customerService;
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    ProductRepository productRepository;

    public Order addOrder(List<Product> products, long customerId) {

        int totalPrice = 0;
        int pricePerProduct = 0;

        for(Product product : products) {
            pricePerProduct = product.getPrice()  * product.getAmount();
            totalPrice+=pricePerProduct;
        }

        Order order = new Order();
        LocalDate date = LocalDate.now();
        Customer customer = customerService.getCustomerById(customerId);

        order.setDateOfOrder(date);
        order.setDateOfLastChange(date);
        order.setProducts(products);
        order.setTotalPrice(totalPrice);
        order.setCustomer(customer);

        Order addedOrder = orderRepository.save(order);

        for(Product product : products) {
            product.setOrder(addedOrder);
        }

        productRepository.saveAll(products);

        return addedOrder;

    }

    public OrderDto editOrder(Order newOrder, long orderId) {

        Optional<Order> existingOrder = orderRepository.findById(orderId);

        if (existingOrder.isPresent()) {

            LocalDate dateOfLastChange = LocalDate.now();

            Order order = existingOrder.get();

            order.setProducts(newOrder.getProducts());
            order.setDateOfLastChange(dateOfLastChange);
            order.setProducts(newOrder.getProducts());
            order.setTotalPrice(getTotalPrice(newOrder.getProducts()));

            OrderDto orderDto = new OrderDto();
            orderDto.setId(order.getId());
            orderDto.setProducts(order.getProducts());
            orderDto.setTotalPrice(order.getTotalPrice());
            orderDto.setCustomerFirstName(order.getCustomer().getFirstName());
            orderDto.setCustomerLastName(order.getCustomer().getLastName());

            return orderDto;
        } else {
            return new OrderDto();
        }

    }

    private Integer getTotalPrice(List<Product> products) {

        int totalPrice = 0;

        for(Product product : products) {

            totalPrice+=product.getPrice();

        }

        return totalPrice;
    }

}
