package com.customerCare.repository.paymentRepository;
import com.customerCare.dto.PaymentDto;
import com.customerCare.model.Payment;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PaymentRepositoryCustom {

    @PersistenceContext
    EntityManager entityManager;

    public List<PaymentDto> getPaymentsByCustomerId(long id) {

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<PaymentDto> criteriaQuery = criteriaBuilder.createQuery(PaymentDto.class);
        Root<Payment> root = criteriaQuery.from(Payment.class);

        criteriaQuery.where(criteriaBuilder.equal(root.get("customer").get("id"), id));

        criteriaQuery.select(criteriaBuilder.construct(
                PaymentDto.class,
                root.get("id"),
                root.get("amount"),
                root.get("dateOfPayment")
        ));

        TypedQuery<PaymentDto> query = entityManager.createQuery(criteriaQuery);
        return query.getResultList();
    }

}
