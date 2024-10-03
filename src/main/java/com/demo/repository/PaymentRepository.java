package com.demo.repository;

import com.demo.entity.CreditCard;
import com.demo.entity.PaypalPayment;
import jakarta.enterprise.context.RequestScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.util.List;

@RequestScoped
public class PaymentRepository {

    @PersistenceContext(name = "jpa-unit")
    private EntityManager em;

    public void createCreditCardPayment(CreditCard payment) {
        em.persist(payment);
    }

    public void createPaypalPayment(PaypalPayment payment) {
        em.persist(payment);
    }

    public PaypalPayment findPaypalPayment(Long id) {
        return em.find(PaypalPayment.class, id);
    }

    public CreditCard findCreditCardPayment(Long id) {
        return em.find(CreditCard.class, id);
    }

    public List<PaypalPayment> readAllPaypalPayments() {
        return em.createNamedQuery("PaypalPayment.findAll", PaypalPayment.class).getResultList();
    }

    public List<CreditCard> readAllCreditCardPayments() {
        return em.createNamedQuery("CreditCardPayment.findAll", CreditCard.class).getResultList();
    }
}