package com.customerCare.repository.customerRepository;

import com.customerCare.dto.CustomerDto;
import com.customerCare.model.Customer;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CustomerRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    public List<CustomerDto> findCustomersByFirstNameAndLastName (String firstName, String lastName) {

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<CustomerDto> criteriaQuery = criteriaBuilder.createQuery(CustomerDto.class);
        Root<Customer> root = criteriaQuery.from(Customer.class);

        Predicate predicate = criteriaBuilder.and(
                criteriaBuilder.equal(root.get("firstName"), firstName),
                criteriaBuilder.equal(root.get("lastName"), lastName));

        criteriaQuery.where(predicate);

        criteriaQuery.select(criteriaBuilder.construct(
                CustomerDto.class,
                root.get("id"),
                root.get("firstName"),
                root.get("lastName"),
                root.get("phoneNumber"),
                root.get("description"),
                root.get("dateAdded")
        ));

        TypedQuery<CustomerDto> query = entityManager.createQuery(criteriaQuery);
        return query.getResultList();
    }

    public CustomerDto getCustomerById(long id) {

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<CustomerDto> criteriaQuery = criteriaBuilder.createQuery(CustomerDto.class);
        Root<Customer> root = criteriaQuery.from(Customer.class);

        Predicate predicate = criteriaBuilder.and(
                criteriaBuilder.equal(root.get("id"), id));

        criteriaQuery.where(predicate);

        criteriaQuery.select(criteriaBuilder.construct(
                CustomerDto.class,
                root.get("id"),
                root.get("firstName"),
                root.get("lastName"),
                root.get("phoneNumber"),
                root.get("description"),
                root.get("dateAdded"),
                root.get("debt")
        ));

        TypedQuery<CustomerDto> query = entityManager.createQuery(criteriaQuery);
        return query.getSingleResult();
    }

}
