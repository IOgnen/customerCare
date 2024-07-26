package com.customerCare.service;

import com.customerCare.dto.CustomerDto;
import com.customerCare.dto.OrderDto;
import com.customerCare.dto.ProductDto;
import com.customerCare.dtoMapper.CustomerDtoMapper;
import com.customerCare.dtoMapper.ProductDtoMapper;
import com.customerCare.model.Customer;
import com.customerCare.model.Order;
import com.customerCare.model.Product;
import com.customerCare.repository.customerRepository.CustomerRepository;
import com.customerCare.repository.orderRepository.OrderRepository;
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
    @Autowired
    CustomerRepository customerRepository;

    public OrderDto addOrder(List<ProductDto> productsDto, long customerId) {

        int totalPrice = 0;
        int pricePerProduct = 0;
        List<Product> products = ProductDtoMapper.productsDtoToProducts(productsDto);

        for(Product product : products) {
            pricePerProduct = product.getPrice()  * product.getAmount();
            totalPrice+=pricePerProduct;
        }

        Order order = new Order();
        LocalDate date = LocalDate.now();

        Optional<Customer> optionalCustomer = customerRepository.findById(customerId);
        if (optionalCustomer.isPresent()) {
            optionalCustomer.get().setDebt();
            customerRepository.save(optionalCustomer.get());
        }

        order.setDateOfOrder(date);
        order.setDateOfLastChange(date);
        order.setProducts(products);
        order.setTotalPrice(totalPrice);
        order.setCustomer(optionalCustomer.get());

        Order addedOrder = orderRepository.save(order);

        for(Product product : products) {
            product.setOrder(addedOrder);
        }

        productRepository.saveAll(products);

        OrderDto orderDto = new OrderDto();
        orderDto.setCustomerId(customerId);
        orderDto.setId(order.getId());
        orderDto.setProducts(productsDto);
        orderDto.setTotalPrice(order.getTotalPrice());
        orderDto.setDateOfOrder(order.getDateOfOrder());

        return orderDto;
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
            orderDto.setTotalPrice(order.getTotalPrice());

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
