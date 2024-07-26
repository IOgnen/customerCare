package com.customerCare.repository.orderRepository;

import com.customerCare.dto.OrderDto;
import com.customerCare.model.Order;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class OrderRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    public List<OrderDto> getOrdersByCustomerId(long id) {

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<OrderDto> criteriaQuery = criteriaBuilder.createQuery(OrderDto.class);
        Root<Order> root = criteriaQuery.from(Order.class);

        criteriaQuery.where(criteriaBuilder.equal(root.get("customer").get("id"), id));

        criteriaQuery.select(criteriaBuilder.construct(
                OrderDto.class,
                root.get("id"),
                root.get("dateOfOrder"),
                root.get("dateOfLastChange"),
                root.get("totalPrice")
        ));

        TypedQuery<OrderDto> query = entityManager.createQuery(criteriaQuery);
        return query.getResultList();
    }

}
